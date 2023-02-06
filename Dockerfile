FROM openjdk:8-jdk-slim
RUN mkdir /app
COPY ./target/schedule-assesment-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java","-jar","schedule-assesment-0.0.1-SNAPSHOT.jar"]