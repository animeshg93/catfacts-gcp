# syntax=docker/dockerfile:1.7

FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /workspace

# Copy build system first for better layer caching.
COPY gradle /workspace/gradle
COPY gradlew /workspace/gradlew
COPY settings.gradle build.gradle /workspace/

RUN chmod +x /workspace/gradlew && /workspace/gradlew --no-daemon -v

COPY src /workspace/src
RUN /workspace/gradlew --no-daemon clean bootJar

FROM gcr.io/distroless/java21-debian12:nonroot
WORKDIR /app

COPY --from=build /workspace/build/libs/app.jar /app/app.jar

ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=cloudrun
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75 -XX:InitialRAMPercentage=30 -XX:+UseG1GC -Dfile.encoding=UTF-8"

EXPOSE 8080
USER nonroot
ENTRYPOINT ["java","-jar","/app/app.jar"]
