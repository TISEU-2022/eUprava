FROM maven:3.8.5-openjdk-11 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src

<<<<<<< HEAD
<<<<<<< HEAD
FROM openjdk:11
=======
RUN --mount=type=cache,target=/root/.m2 mvn install -DskipTests
>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad

RUN cp ./target/sluzba-zaposljavanja-docker.jar application.jar

FROM bellsoft/liberica-openjdk-alpine-musl:11 as second_stage
WORKDIR /app
COPY --from=build /app/application.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

<<<<<<< HEAD
=======
#FROM openjdk:11

#target/sluzba-zaposljavanja-docker.jar sluzba-zaposljavanja-docker.jar

FROM maven:3.8.1-openjdk-11 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn install -DskipTests

RUN cp target/*.jar application.jar

FROM bellsoft/liberica-openjdk-alpine-musl:11 as second_stage
WORKDIR /app
COPY --from=build /app/application.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM bellsoft/liberica-openjdk-alpine-musl:11
WORKDIR /app

EXPOSE 3033

=======
FROM bellsoft/liberica-openjdk-alpine-musl:11
WORKDIR /app

>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad
COPY --from=second_stage /app/dependencies/ ./
COPY --from=second_stage /app/snapshot-dependencies/ ./
COPY --from=second_stage /app/spring-boot-loader/ ./
COPY --from=second_stage /app/application/ ./

ENTRYPOINT ["java"]
<<<<<<< HEAD
CMD ["org.springframework.boot.loader.JarLauncher"]
>>>>>>> zaposljavanje-init
ENTRYPOINT ["java","-jar", "sluzba-zaposljavanja-docker.jar"]
=======
CMD ["org.springframework.boot.loader.JarLauncher"]
>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad
