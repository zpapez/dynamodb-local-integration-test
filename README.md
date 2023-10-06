# dynamodb-local-integration-test
Writing integration tests using Spring Boot and AWS local DynamoDB.


## Running locally

### Run DynamoDB in container
To start up DynamoDB as docker container from project folder run
```
docker-compose -f docker/docker-compose.yml -p pqc up -d 
```
#### Initialize some data
In order to create required tables in database run
```
./docker/dbsetup.sh
```

### Run Spring Application
```
SPRING_PROFILES_ACTIVE=local
```

## Usage
Endpoint returns list of countries from DB
```
http://localhost:8080/api/v1/countries
```
