FROM openjdk:17

ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

WORKDIR euprava-izrada-app

COPY target/izrada-dokumenata-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java" , "-jar" , "izrada-dokumenata-0.0.1-SNAPSHOT.jar"]

EXPOSE 11001

