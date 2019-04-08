FROM java:8-jdk-alpine

COPY ./target/immobilier-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch immobilier-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","immobilier-0.0.1-SNAPSHOT.jar"]  