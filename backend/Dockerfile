FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package 

FROM jboss/wildfly:21.0.0.Final
COPY --from=build app/servicio/target/mercadeoucab-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/