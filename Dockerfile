FROM openjdk:17

# 작업 디렉토리를 설정합니다.
WORKDIR /app

# 빌드 파일을 컨테이너에 복사합니다.
# Gradle 빌드 시 생성되는 JAR 파일의 경로를 확인하고, 해당 경로를 사용하세요.
COPY build/libs/*SNAPSHOT.jar app.jar

# 애플리케이션을 실행합니다.
ENTRYPOINT ["java","-Xmx2g","-jar","app.jar"]