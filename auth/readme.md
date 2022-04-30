## Simple test project

-- Web application based on spring web

## Minimal Requiriments
-- java version jdk 11 

## Install and Run
-- simple run app required just jdk11
command line:
java -jar auth-1.0.0-SNAPSHOT.jar

-- Deploy using docker
For this operation you need docker service installed on your machine
just run 
docker build -t springio/gs-spring-boot-docker .
docker run -p 8080:8080 springio/gs-spring-boot-docker

-- Getting Started
after app starts by default that have user named admin with password 12345 you have to use that user for add new one
register your admin with your password using api

## API Reference

- all api implemented as a Rest with Http Basic authorization
- full Api reference you could find in swagger UI

## Special instructions
You must not expose checkAuthToken to users