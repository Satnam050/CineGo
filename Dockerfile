FROM eclipse-temurin:21-jdk-ubi10-minimal
WORKDIR /app
COPY target/cinego-v1.jar /app/cinego-v1.jar
ENTRYPOINT ["java", "-jar", "cinego-v1.jar"]