# spring-webflux-kotlin-demo

Simple web app using Spring Webflux, Kotlin, and more

## Project Evolution

### Initialization

1. Used Spring Initializr via IntelliJ IDEA.
   - Language: Kotlin
   - Type: Gradle
   - Packaging: Jar
   - Dependencies:
     - Developer Tools > Spring Boot DevTools
     - Web > Spring Reactive Web

2. Updated versions of Kotlin to 1.7.0. IntelliJ notified me that the new version of Kotlin is available.
   ```kt
   kotlin("jvm") version "1.7.0"
   kotlin("plugin.spring") version "1.7.0"
   ```

3. Renamed `application.properties` in `src/main/resources/` to `application.yaml`, because I prefer declaring properties using YAML format. To enjoy configuration auto-completion support of IDE, I added spring-boot-configuration-processor to dependencies in `build.gradle.kts`.
   ```kt
   annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
   ```

### Adding REST API Endpoints

1. Created a handler named `BasicHandler`, which contains a function named `ping`.
2. Created `RouterConfig` and declared a router function.
3. Run the app and check `GET /api/ping` works well:
   ```shell
   $ http localhost:8080/api/ping

   HTTP/1.1 200 OK
   Content-Length: 63
   Content-Type: application/json

   {
     "appStartedAt": "2022-07-05T07:35:30.498008+09:00[Asia/Seoul]"
   }
   ```

## References

- [Spring WebFlux - Functional Endpoints (official doc.)](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html#webflux-fn)
- [Spring WebFlux with Kotlin](https://www.baeldung.com/kotlin/spring-webflux-kotlin)
