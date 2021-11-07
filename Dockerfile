FROM openjdk:15
COPY /build/libs/test.task.eurora-1.0-all.jar /usr/app/
EXPOSE 8001
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "test.task.eurora-1.0-all.jar"]