FROM openjdk:8-jdk-alpine
MAINTAINER islemchatti
COPY target/*.jar tpAchatProject-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.0-SNAPSHOT.jar"]
