package com.example.backend.domain.member;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "member_agreement")
public class MemberAgreement {
    
    @EmbeddedId
    private MemberAgreementId id;

    @MapsId("memberUuid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_uuid", nullable = false, columnDefinition = "uuid")
    private Member member;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamptz")
    private OffsetDateTime createdAt;

    @Column(name = "is_agreed_over14_terms", nullable = false)
    private boolean isAgreedOver14Terms = false;

    @Column(name = "over14_terms_agreed_at", nullable = false, columnDefinition = "timestamptz")
    private OffsetDateTime over14TermsAgreedAt = OffsetDateTime.now();

    @Column(name = "is_agreed_terms_of_service", nullable = false)
    private boolean isAgreedTermsOfService = false;

    @Column(name = "terms_of_service_agreed_at", nullable = false, columnDefinition = "timestamptz")
    private OffsetDateTime termsOfServiceAgreedAt = OffsetDateTime.now();

    @Column(name = "is_agreed_privacy_policy", nullable = false)
    private boolean isAgreedPrivacyPolicy = false;

    @Column(name = "privacy_policy_agreed_at", nullable = false, columnDefinition = "timestamptz")
    private OffsetDateTime privacyPolicyAgreedAt = OffsetDateTime.now();

    @Column(name = "is_agreed_marketing_information", nullable = false)
    private boolean isAgreedMarketingInformation = false;

    @Column(name = "marketing_information_agreed_at", nullable = false, columnDefinition = "timestamptz")
    private OffsetDateTime marketingInformationAgreedAt = OffsetDateTime.now();

    protected MemberAgreement() {}
    
    public static MemberAgreement of(Member member) {
        var now = OffsetDateTime.now();
        var id = new MemberAgreementId(extractUuid(member), now);
        var m = new MemberAgreement();

        m.id = id;
        m.member = member;
        m.over14TermsAgreedAt = now;
        m.termsOfServiceAgreedAt = now;
        m.privacyPolicyAgreedAt = now;
        m.marketingInformationAgreedAt = now;
        return m;
    }

    private static java.util.UUID extractUuid(Member m) {
        try {
            var f = Member.class.getDeclaredField("uuid");
            f.setAccessible(true);
            return (java.util.UUID) f.get(m);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read member uuid", e);
        }
  }
}
