Gatling Load Test Implementation for Forex Trade API
==========================

This is the load test implementation for Forex Trade API. Refer to [this article](https://medium.com/devtechtoday/how-to-build-performance-tests-to-validate-your-apis-using-gatling-b91adaf08222) for the step-by-step guide to Gatling load test implementation.

## The Setup
You need the following software installed on your machine:
* Docker
* Java JDK version 8 or 11
* Maven
* Scala 2.12

## Demonstration of The Difference Between Servlet Spring and Reactive Spring

You can use the load test to find out the performance difference between blocking and non-blocking technology.

Forex Trade API comes with two kinds of implementation - Servlet Spring and Reactive Spring.

The application is by default running based on a mock data of forex rates to facilitate the local testing. If you are interested in knowing more about the API implementation, you can refer to [this article]([this article](https://medium.com/devtechtoday/how-to-build-performance-tests-to-validate-your-apis-using-gatling-b91adaf08222)) and the Github repositories

- [Reactive Spring Forex Trade](https://github.com/gavinklfong/reactive-spring-forex-trade)
- [Servlet Spring Forex Trade](https://github.com/gavinklfong/servlet-spring-forex-trade)


### Run the Forex API (Servlet Spring - Blocking Technology)

To run the Forex API application in your local environment, the convenient way is to run it as a docker container. Run this command to download the published docker image and bring up the application.

```
docker run --rm -p 8080:8080 whalebig27/servlet-spring-forex-trade
```

Run this docker image version if you would like to simulate a slow exchange rate API

```
docker run --rm -p 8080:8080 whalebig27/servlet-spring-forex-trade:slow
```

### Run the Forex API (Reactive Spring - Non-Blocking Technology)

To run the Forex API application in your local environment, the convenient way is to run it as a docker container. Run this command to download the published docker image and bring up the application.

```
docker run --rm -p 8080:8080 whalebig27/reactive-spring-forex-trade
```

Run this docker image version if you would like to simulate a slow exchange rate API

```
docker run --rm -p 8080:8080 whalebig27/reactive-spring-forex-trade:slow
```

## Run load test
Execute this load test using this command

```
mvn gatling:test
```