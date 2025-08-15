# JaVueBrain

## 애플리케이션 실행 (bootRun)

./gradlew bootRun
SPRING_PROFILES_ACTIVE=local ./gradlew bootRun # 프로필 지정 실행

## JAR 빌드

./gradlew bootJar
java -jar build/libs/<프로젝트명>-<버전>.jar --spring.profiles.active=local

## 테스트 실행

./gradlew test
./gradlew test --tests "com.example.backend.service.\*"

## Gradle Wrapper 생성 (없을 때)

gradle wrapper --gradle-version <버전>

## 클린 빌드

./gradlew clean build

## 의존성 캐시 새로 받기

./gradlew --refresh-dependencies

## Gradle 데몬 중지

./gradlew --stop

## (문제 있을 때) 캐시 삭제 후 빌드

rm -rf ~/.gradle/caches build .gradle
./gradlew clean build

## JAR 빌드

./gradlew clean bootJar

## 서버에서 실행

java -jar build/libs/backend-0.0.1-SNAPSHOT.jar \
 --spring.profiles.active=prod \
 --server.port=8080
