FROM openjdk:17.0-jdk
ADD ../target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]