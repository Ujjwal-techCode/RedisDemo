FROM eclipse-temurin:26-jre
LABEL authors="Ujjwal"
WORKDIR /app
COPY target/RedisDemo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app.jar"]