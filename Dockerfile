FROM adoptopenjdk:11-jre-hotspot
ADD target/bookstore*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]