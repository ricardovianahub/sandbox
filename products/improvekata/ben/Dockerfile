FROM openjdk:14
ARG JAR_FILE
RUN ./gradlew bootJar --no-daemon
COPY ${JAR_FILE} /improvekataben.jar
EXPOSE 8200
ENTRYPOINT ["java","-jar","/src/build/libs/backend-0.0.1-SNAPSHOT.jar"]
