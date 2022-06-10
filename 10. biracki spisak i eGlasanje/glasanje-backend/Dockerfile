FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:13-jdk-alpine
COPY --from=build /home/app/target/glasanje-backend-0.0.1-SNAPSHOT.jar glasanje-backend-0.0.1-SNAPSHOT.jar
EXPOSE 10002
ENTRYPOINT ["java","-jar","glasanje-backend-0.0.1-SNAPSHOT.jar"]