FROM openjdk:14
COPY . /src
WORKDIR /src
RUN ./gradlew bootJar --no-daemon
EXPOSE 8200
CMD ["java","-jar","/src/build/libs/backend-0.0.1-SNAPSHOT.jar"]
