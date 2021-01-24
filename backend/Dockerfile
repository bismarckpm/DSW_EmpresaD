FROM maven:3.6.3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn package

FROM jboss/wildfly:21.0.0.Final
COPY --from=build app/target/mercadeoucab-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/