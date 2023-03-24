FROM openjdk:8-jdk-alpine
MAINTAINER islem
COPY target/tpAchatProject-1.0-SNAPSHOT.jar tpAchatProject-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.0-SNAPSHOT.jar"]
