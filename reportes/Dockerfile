FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} reportes.jar
ENTRYPOINT ["java", "-jar", "/reportes.jar"]