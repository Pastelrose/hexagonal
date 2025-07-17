# 베이스 이미지
FROM openjdk:21-jdk

# 작업 디렉토리 설정
WORKDIR /hexagonal

# jar 파일 복사
COPY build/libs/hexagonal-0.0.1-SNAPSHOT.jar hexagonal.jar

# 실행 명령어
ENTRYPOINT ["java", "-jar", "hexagonal.jar"]