FROM openjdk:17 as builder
WORKDIR /workspace
COPY . .
RUN apt-get update && \
    apt-get install -y --no-install-recommends wget unzip && \
    wget https://services.gradle.org/distributions/gradle-7.0-bin.zip && \
    unzip -d /opt gradle-7.0-bin.zip

ENV PATH="${PATH}:/opt/gradle-7.0/bin"

RUN gradle build

FROM openjdk:17
COPY --from=builder /workspace/build/libs/realEstate-0.0.1-SNAPSHOT.jar app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./app.jar"]
