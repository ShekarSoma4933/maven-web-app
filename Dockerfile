FROM openjdk:8-jre-alpine
EXPOSE 9090
COPY ./target/maven-web-application.jar/ /usr/app
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","maven-web-application.jar"]