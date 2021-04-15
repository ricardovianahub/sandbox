FROM openjdk:15
COPY . /src
WORKDIR /src
RUN awk '{ sub("\r$", ""); print }' gradlew > gradlewconverted
RUN chmod +x gradlewconverted
RUN ./gradlewconverted bootJar --no-daemon
EXPOSE 8200
CMD ["java","-jar","/src/build/libs/backend-0.0.1-SNAPSHOT.jar"]
