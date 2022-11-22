#./mvnw package && java -jar target/Application-0.0.1-SNAPSHOT.jar

FROM openjdk:17-oracle
EXPOSE 8082
ARG JAR_FILE=target/Application-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#docker build -t Application:0.0.1 .