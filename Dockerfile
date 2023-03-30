FROM eclipse-temurin:17 AS build

WORKDIR /backend

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY ./src ./src

RUN ./mvnw clean install

FROM eclipse-temurin:17 AS run

WORKDIR /backend

COPY --from=build /backend/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
