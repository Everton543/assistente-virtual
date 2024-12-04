FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y \
    openjdk-21-jdk \
    maven \
    wget \
    curl \
    git \
    && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/Virtual-0.0.1-SNAPSHOT.jar app.jar
COPY ../etc/secrets/.env .env
ENTRYPOINT [ "java", "-jar", "app.jar" ]
