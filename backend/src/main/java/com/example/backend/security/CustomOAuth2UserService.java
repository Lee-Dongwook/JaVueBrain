package com.example.backend.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

import static java.util.Collections.singleton;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = null, name = null, picture = null;

        switch (registrationId) {
            case "google" -> {
                email = (String) attributes.get("email");
                name = (String) attributes.getOrDefault("name", email);
                picture = (String) attributes.get("picture");
            }
            case "kakao" -> {
                Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
                if (account != null) {
                    email = (String) account.get("email");
                    Map<String, Object> profile = (Map<String, Object>) account.get("profile");
                    if (profile != null) {
                        name = (String) profile.get("nickname");
                        picture = (String) profile.get("profile_image_url");
                    }
                }
                if (name == null)
                    name = "kakao_user";
            }
            case "naver" -> {
                Map<String, Object> resp = (Map<String, Object>) attributes.get("response");
                if (resp != null) {
                    email = (String) resp.get("email");
                    name = (String) resp.getOrDefault("name", email);
                    picture = (String) resp.get("profile_image");
                }
            }
            default -> {
            }
        }
        
        // TODO: 여기서 email 없으면 회원가입 불가 정책/에러 처리
        // TODO: DB에 upsert (findByEmail -> 없으면 생성, 있으면 최신 프로필로 업데이트)
        String nameAttributeKey = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        return new DefaultOAuth2User(
            singleton(() -> "ROLE_USER"),
            attributes,
            nameAttributeKey
        );
    }
}
