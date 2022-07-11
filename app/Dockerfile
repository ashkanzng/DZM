FROM openjdk:17
VOLUME /tmp
COPY target/*.jar app.jar
COPY *.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]