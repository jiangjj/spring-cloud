FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ADD ./wait-for-it.sh /wait-for-it.sh
ADD ./target/service-discovery-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]