FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} web-1.0.0.jar
ENTRYPOINT ["java","-jar","/processing-1.0.0-SNAPSHOT.jar"]