
FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ./target/easy-stock-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "app.jar"]
