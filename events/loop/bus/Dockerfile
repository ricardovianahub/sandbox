FROM openjdk:11
EXPOSE 8090
ARG JAR_FILE
COPY ${JAR_FILE} /bus.jar
ENTRYPOINT ["java","-jar","/bus.jar"]
