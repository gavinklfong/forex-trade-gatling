Gatling Load Test Implementation for Forex Trade API
==========================

This is the load test implementation for Forex API. 

## The Setup
You need the following software installed on your machine:
* Docker
* Java JDK version 8 or 11
* Maven
* Scala 2.12

## Run the Forex API
To run the Forex API application in your local environment, the convenient way is to run it as a docker container. Run this command to download the published docker image and bring up the application.

```
docker run --rm -p 8080:8080 whalebig27/reactive-spring-forex-trade
```

The application is by default running based on a mock data of forex rates to facilitate the local testing. If you are interested in knowing more about the API implementation, you can refer to this article and this Github repository

## Run this load test
Then, you can execute this load test using this command

```
mvn gatling:test
```