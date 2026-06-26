# AGENTS.md — examen-final

Spring Boot 4.1.0 / Java 17 / Maven project generated from Spring Initializr. Currently a scaffold with no custom code.

## Commands

```bash
./mvnw spring-boot:run          # dev server
./mvnw test                     # all tests
./mvnw test -Dtest=SpecificTest # single test class
./mvnw clean compile            # compile only
./mvnw clean package            # build jar
```

## Project structure

- Java package: `com.unaempresa.examen_final` (underscore — Maven rejects hyphens in package names)
- Base path: `src/main/java/com/unaempresa/examen_final/`
- Templates: `src/main/resources/templates/` (Thymeleaf)
- Static assets: `src/main/resources/static/`
- Config: `src/main/resources/application.properties`
- Test base path: `src/test/java/com/unaempresa/examen_final/`

## Stack

- **Web**: Spring WebMVC + Thymeleaf + Validation
- **Persistence**: Spring Data JPA with H2 (dev) / MySQL (prod) — both on classpath at runtime
- **Security**: Spring Security + Thymeleaf extras for Spring Security 6
- **Lombok**: enabled via annotation processor in compiler plugin config
- **DevTools**: hot reload on classpath changes

## Testing

- JUnit 5 (Jupiter) via `spring-boot-starter-*test` starters
- `@SpringBootTest` loads full application context
- Dedicated test starters: `data-jpa-test`, `security-test`, `thymeleaf-test`, `validation-test`, `webmvc-test`

## Eclipse/STS project

IDE metadata (`.classpath`, `.project`, `.settings/`) is committed. Do not regenerate with `mvn eclipse:eclipse` without checking if it duplicates existing config.

## .gitignore

Ignores `target/`, IDE dirs (`.idea`, `.vscode`, `.settings` is tracked), `.mvn/wrapper/maven-wrapper.jar`.