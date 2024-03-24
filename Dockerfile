FROM openjdk:17 as builder
WORKDIR /workspace
COPY . .
RUN ./gradlew build  # 또는 적절한 빌드 명령어 실행
ARG JAR_FILE=build/libs/realEstate-0.0.1-SNAPSHOT.jar

FROM openjdk:17
COPY --from=builder /workspace/${JAR_FILE} app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./app.jar"]
