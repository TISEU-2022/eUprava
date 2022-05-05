#FROM maven:3.8.2-jdk-11
#WORKDIR /SluzbaZaposljavanja
#COPY . .
#RUN mvn clean install
#CMD mvn spring-boot:run

FROM openjdk:11

EXPOSE 3033

target/sluzba-zaposljavanja-docker.jar sluzba-zaposljavanja-docker.jar

ENTRYPOINT ["java","-jar", "sluzba-zaposljavanja-docker.jar"]