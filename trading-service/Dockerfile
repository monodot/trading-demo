# Build the application first using Maven
FROM docker.io/library/maven:3.8-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn install

# Inject the JAR file into a new container to keep the file small
FROM docker.io/library/eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/trading-service-*.jar /app/app.jar

#RUN curl -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.19.0/opentelemetry-javaagent.jar -o /app/opentelemetry-javaagent.jar

EXPOSE 8080
LABEL org.opencontainers.image.source https://github.com/monodot/trading-demo

#ENTRYPOINT ["java","-javaagent:/app/opentelemetry-javaagent.jar","-jar","/app/app.jar"]
ENTRYPOINT ["java","-jar","/app/app.jar"]
