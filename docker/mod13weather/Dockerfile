FROM openjdk:14
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} /mod13weather.jar
ENTRYPOINT ["java","-jar","/mod13weather.jar", "--spring.profiles.active=live"]
