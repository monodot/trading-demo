# trading-demo

This is a demo of a trading application. It is a simple application that allows you to buy and sell stocks.

This application writes logs to the console for each trade, which can be scraped and sent to a log aggregator like Promtail/Loki.

## Getting Started

### Prerequisites

You will need to have the following installed on your machine:

- Java (if running standalone)
- Docker (if running the complete application + log collector in a container)

### Running the application

To run the application, you can run the following command:

```bash
./mvnw spring-boot:run
```

Also provided is a docker-compose file, which runs the application and a Promtail instance to send to Loki. To run it, you can run the following command:

```bash
docker-compose up -d
```

## Running the tests

There aren't many tests at the moment, but to run them, you can run the following command:

```bash
./mvnw test
```

## Built With

- [Maven](https://maven.apache.org) - Dependency Management
- [Spring Boot](https://spring.io/projects/spring-boot) - Application Framework

## Authors

- **Tom Donohue** - [monodot](https://github.com/monodot)

