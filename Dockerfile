FROM adoptopenjdk:11-jre-hotspot
COPY "target/notification-service.jar" notification-service.jar
ENTRYPOINT ["java", "-jar", "notification-service.jar"]