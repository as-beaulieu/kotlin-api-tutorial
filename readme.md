# Overview

- Build a course catalog service
- Restful API that manages the course catalog for an onlne learning platform
- Will use a Database for storing the course information

## Constructor Injection

## Spring Profiles

- Configurations for different environments
- Without one set, spring has a default environment applied

```
No active profile set, falling back to 1 default profile: "default"
```

Setting a profile is done inside the application.yml
```yaml
spring:
  config:
    activate:
      on-profile: prod
message: Hello from default profile
```

To activate this, in edit run configuration for application,
provide a flag in the `VM options`:

`- Dspring.profiles.active=prod`

When running, should now see this instead of the default profile message:

`The following 1 profile is active: "prod"`

Can also manage multiple profiles/environments with their own yml file
`application-{profile}.yml`

```
application.yml
application-prod.yml
```

### Using profile environment variables

- Setting variables with a `lateinit` keyword helps for when injecting through Spring later

```kotlin
@Value("\${message}")
lateinit var message: String
```

## Logging in Kotlin Spring

`companion object: KLogging()`

## Automated Tests with JUnit 5

Setting up two different test folders to separate between integration and unit tests

```kotlin
sourceSets {
	test {
        //Gradle 7.1+
        java {
            setSrcDirs(listOf("src/test/integrated", "src/test/unit"))
        }


        //before 7.1 gradle
//		withConvention(KotlinSourceSet::class) {
//			kotlin.setSrcDirs(listOf("src/test/integrated", "src/test/unit"))
//		}
	}
}
```

This will allow two new folders to be made at the `src/test` level, "integrated" and "unit", and
have them show as green test folders

### Integration Tests

- tests the application from end to end, only focusing on input and output
- Expecting a controller request to process to service layer, then DB, then back,
    - but only worried about return being expected.


### Unit Tests

- tests individual classes and methods for functionality, focusing on the
parameters, behavior, and return of that item.
  - Anything before or after the tested unit will be mocked to supply expected behavior
for the test condition

#### Mocking

Mockito is a standard for mocking behavior

This lesson presents an alternative that works well with Kotlin `Mockk`

- Mockk considerations
  - `every` block is the same as `when`

## Course Catalog Service

Taking in JSON ---> Course Catalog Service ---> Database

CourseDTO (Data Transfer Object) to convert JSON to internal object

CourseEntity to handle object to save to database

### DTO vs Entity

- Not a good practice to export the DB columns as a plain contract
- Also allows the validation to apply to the entity, but not to the data across
the application

### H2

- most of the project will be done with an in-memory DB: h2

Access the h2 database in the following link:

http://localhost:8080/h2-console
