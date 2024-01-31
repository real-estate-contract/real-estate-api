FROM openjdk:17
ARG JAR_FILE=build/libs/realEstate-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Seoul
ENV AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
ENV AWS_SECRET_KEY=${AWS_SECRET_KEY}

ENTRYPOINT ["java", "-jar", "./app.jar"]
