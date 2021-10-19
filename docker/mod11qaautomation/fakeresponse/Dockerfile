FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} /fakeresponse.jar
ENTRYPOINT ["java","-jar","/fakeresponse.jar"]
