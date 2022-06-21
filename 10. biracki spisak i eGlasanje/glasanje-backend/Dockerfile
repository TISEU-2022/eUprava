#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml install
#
#FROM openjdk:13-jdk-alpine
#COPY --from=build /home/app/target/glasanje-backend-0.0.1-SNAPSHOT.jar glasanje-backend-0.0.1-SNAPSHOT.jar
#EXPOSE 10002
#ENTRYPOINT ["java","-jar","glasanje-backend-0.0.1-SNAPSHOT.jar"]

FROM maven:3.8.5-openjdk-11 as build
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

COPY --from=second_stage /app/dependencies/ ./
COPY --from=second_stage /app/snapshot-dependencies/ ./
COPY --from=second_stage /app/spring-boot-loader/ ./
COPY --from=second_stage /app/application/ ./

ENTRYPOINT ["java"]
CMD ["org.springframework.boot.loader.JarLauncher"]