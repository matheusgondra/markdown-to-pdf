FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -q -DskipTests clean package

FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd -r -u 1001 spring

USER 1001

COPY --from=build /app/target/md2pdf-*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-XX:MaxRAMPercentage=75", "-XX:+UseG1GC", "-jar", "app.jar" ]