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
