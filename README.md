# Product API

Api desenvolvida referente ao desafio da Compasso UOL

## Requirements

For building and running the application you need:

- JDK 11
- Maven 3

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `br.com.compasso.productapi.ProductApiApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
./mvnw spring-boot:run
```

## Executing Tests

For execute the tests, run command below:

```shell
./mvnw test
```

## Test Api Manually, use openAPI:

http://localhost:9999/swagger-ui.html

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
