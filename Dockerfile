# Build stage
FROM maven:3.9.9-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM amazoncorretto:21-alpine3.19
WORKDIR /app
COPY --from=build /app/target/basic-crud.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
