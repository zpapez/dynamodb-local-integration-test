# dynamodb-local-integration-test
Writing integration tests using Spring Boot and AWS local DynamoDB.


## Maven build and Tests

To run only unit tests:
```
mvn test
```

To run only integration tests (including also unit tests):
```
mvn install
```


## Running locally

### Run DynamoDB in container
To start up DynamoDB as docker container from project folder run
```
docker-compose -f docker/docker-compose.yml up -d 
```
#### Initialize some data
In order to create required tables in database run
```
./docker/dbsetup.sh
```

### Run Spring Application
Run the Spring application using class `Application` and profile `local`:
```
SPRING_PROFILES_ACTIVE=local
```

## Usage
Endpoint returns list of countries from DB
```
http://localhost:8080/api/v1/countries
```


