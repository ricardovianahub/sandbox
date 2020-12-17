FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} /fakeresponse.jar
ENTRYPOINT ["java","-jar","/fakeresponse.jar"]
