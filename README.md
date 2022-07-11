## Transactions File Validator service
This minimal project is to help you validate transactions' files that are received from banks, companies and ... .

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle 7.4](https://docs.gradle.org)
- [Put your transactions files in src/main/resources/{filesdirectory=records}](src/main/resources/records)

## Running the application locally
Firstly, configure your "records" path in resources directory. By default, it is "records".

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `dev.djawadi.services.filevalidator.FileValidatorApplication` class from your IDE.

Alternatively you can use the [Spring Boot Gradle plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins.html#build-tool-plugins.gradle) like so:

```shell
./gradlew bootRun -Pargs="records.csv"
```

## Deploying the application to Kubernetes
Under construction ...

## Running Tests
```shell
./gradlew test
```


## TO DO List: 
- [ ] Prepare Dockerfile to make service ready to use in production
- [ ] It's better to read configurations from env instead of application.properties
- [ ] Add loggers and metric to improve monitoring and maintainability.
- [ ] [enhancement] We can write another implementation for `IFileLoader` that uses `AWS S3` instead of local file.

## License
Copyleft is licensed under the MIT license.