FROM openjdk:14
ARG JAR_FILE
COPY ${JAR_FILE} /improvekataben.jar
EXPOSE 8200
ENTRYPOINT ["java","-jar","/improvekataben.jar"]
