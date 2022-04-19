########Maven build stage########
FROM maven:3.8.5-openjdk-17 as maven_build
WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN --mount=type=cache,target=/root/.m2  mvn clean package -Dmaven.test.skip

RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf ../izrada-dokumenata-0.0.1-SNAPSHOT.jar

########JRE run stage########
FROM openjdk:17
WORKDIR /app

ARG DOCKER_PACKAGING_DIR=/app/target/docker-packaging
COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/BOOT-INF/lib /app/lib
COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/BOOT-INF/classes /app/classes
COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/META-INF /app/META-INF

CMD java -cp .:classes:lib/* \
         -Djava.security.egd=file:/dev/./urandom \
         foo.bar.IzradaDokumenataApplication.java

ENTRYPOINT ["java", "-jar", "izrada.dokumenata-0.0.1-SNAPSHOT.jar"]

EXPOSE 11001