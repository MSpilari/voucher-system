FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

RUN chmod +x mvnw

COPY src ./src
RUN ./mvnw clean package -DskipTests

ENTRYPOINT ["java","-jar","target/voucher_api-0.0.1-SNAPSHOT.jar"]