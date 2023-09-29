FROM openjdk:17

WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src src
RUN ./mvnw install

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
