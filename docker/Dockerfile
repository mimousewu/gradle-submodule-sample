FROM openjdk:8-jdk-alpine as customjdk

RUN apk --update add \
            curl \
            bash

# Application image
FROM customjdk

ENV ENV dev

COPY ./app/build/libs/app.jar /app.jar

CMD ["java","-jar","/app.jar","--spring.profiles.active=${ENV}"]
