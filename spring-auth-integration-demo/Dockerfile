 # syntax=docker/dockerfile:experimental
FROM gradle:7-jdk17 as cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle.kts /home/gradle/code/
COPY gradle.properties /home/gradle/cache_home
WORKDIR /home/gradle/code
RUN --mount=type=cache,target=/home/gradle/cache_home gradle clean build -i --stacktrace -x bootJar


FROM gradle:7-jdk17 as builder
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY . /app
WORKDIR /app
RUN gradle bootJar --stacktrace

RUN cp build/libs/spring-auth-integration-demo-0.0.1-SNAPSHOT.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/application/ ./
EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["org.springframework.boot.loader.JarLauncher"]
