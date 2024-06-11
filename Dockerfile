## Importing JDK and copying required files
#FROM openjdk:21-jdk AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src src
#
## Copy Maven wrapper
#COPY mvnw .
#COPY .mvn .mvn
#
## Set execution permission for the Maven wrapper
#RUN chmod +x ./mvnw
#RUN ./mvnw clean package -DskipTests
#
## Stage 2: Create the final Docker image using OpenJDK 21
#FROM openjdk:21-jdk
#VOLUME /tmp
#
## Copy the JAR from the build stage
#COPY --from=build /app/target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#EXPOSE 8080

FROM eclipse-temurin:21

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]