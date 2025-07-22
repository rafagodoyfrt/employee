# Etapa 2: imagem final com Java 17
FROM eclipse-temurin:17-jdk
COPY ./target/employee-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]