FROM openjdk:8-jre-alpine
EXPOSE 9090
RUN mkdir -p /usr/app
COPY ./target/*-maven-web-app.jar/ /usr/app
WORKDIR /usr/app
#ENTRYPOINT ["java","-jar","maven-web-application.jar"]
CMD java -jar *-maven-web-app.jar