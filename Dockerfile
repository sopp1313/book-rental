FROM openjdk:17-jdk-slim

WORKDIR /app

COPY gradle gradle
COPY gradlew build.gradle ./
COPY src src

RUN chmod +x gradlew
RUN ./gradlew build -x test

EXPOSE 8080

CMD ["./gradlew", "bootRun"]