# Captains Log - A TODO Microservice Blueprint with DDD, TDD, and Observability

CaptainsLog is a production-grade TODO microservice built with Spring Boot, DDD, observability-first principles, and Kubernetes-native resilience."

## Blueprint

## Modern Microservice Development Blueprint

| **Category**            | **Concern**             | **Best Practice / Tool**                                    |
| ----------------------- | ----------------------- | ----------------------------------------------------------- |
| **1. Architecture**     | Design Style            | Hexagonal (Ports & Adapters), Clean Architecture            |
|                         | Domain Modeling         | Domain-Driven Design (DDD)                                  |
|                         | Module Structure        | Multi-module: domain, application, adapters                 |
|                         | DTO Boundaries          | Explicit API-layer DTOs, no entity leakage                  |
|                         | Dependency Inversion    | Interfaces for input/output ports, adapters implement ports |
| **2. Language & Build** | Language & JVM          | Java 21, Spring Boot 3.x                                    |
|                         | Build Tool              | Maven with BOM and multi-module support                     |
|                         | Native Build            | GraalVM native image (optional)                             |
| **3. API Design**       | API Type                | RESTful HTTP                                                |
|                         | Versioning              | URI versioning: `/api/v1/...`                               |
|                         | Validation              | Jakarta Validation annotations (JSR-380)                    |
|                         | Documentation           | OpenAPI (springdoc-openapi + Swagger UI)                    |
|                         | Error Format            | ProblemDetails (RFC 9457)                                   |
| **4. Observability**    | Metrics                 | Micrometer + Prometheus                                     |
|                         | Logging                 | JSON logs via SLF4J / Logback to stdout                     |
|                         | Tracing                 | OpenTelemetry with OTLP exporters                           |
|                         | Health Checks           | Spring Boot Actuator (`/health`, `/metrics`)                |
|                         | Contextual Logging      | MDC for correlation ID and trace ID                         |
| **5. Testing**          | Unit Testing            | JUnit 5, AssertJ                                            |
|                         | HTTP Testing            | RestAssured                                                 |
|                         | Integration Testing     | Spring Boot Test + Testcontainers                           |
|                         | Coverage Metrics        | JaCoCo, mutation testing (e.g. PIT)                         |
|                         | Database Test Isolation | PostgreSQL via Testcontainers                               |
| **6. Security**         | Authentication          | OAuth2 or JWT                                               |
|                         | Authorization           | Role-based via Spring Security                              |
|                         | Secret Management       | K8s Secrets, Vault, environment variables                   |
|                         | Input Validation        | DTO-based, annotated validation                             |
|                         | Transport Security      | TLS via Ingress or L4 Load Balancer                         |
| **7. Resilience**       | Circuit Breaker         | Resilience4j                                                |
|                         | Retry & Timeout         | Resilience4j Retry + Spring timeouts                        |
|                         | Rate Limiting           | Bucket4j, Redis, or Gateway throttling                      |
|                         | Bulkheading             | Resilience4j thread pools                                   |
| **8. CI/CD**            | Build & Test Automation | GitHub Actions, GitLab CI                                   |
|                         | Quality Gates           | Checkstyle, SpotBugs, PMD, JaCoCo                           |
|                         | Dockerization           | Layered Docker builds, `.dockerignore`                      |
|                         | Artifact Publishing     | Docker Registry, Maven Central                              |
|                         | Image Scanning          | Trivy, Snyk                                                 |
| **9. Runtime**          | Orchestration           | Kubernetes (e.g. EKS, GKE, AKS)                             |
|                         | Config Deployment       | K8s ConfigMap and Secrets                                   |
|                         | Helm                    | Helm charts for service packaging                           |
|                         | Load Balancing          | K8s Service, Ingress Controller                             |
|                         | Auto Scaling            | HPA (CPU or custom metrics)                                 |
| **10. Docs & Collab**   | Architecture Diagrams   | C4 Model, Mermaid, PlantUML                                 |
|                         | API Specs               | OpenAPI v3.0 + Swagger UI                                   |
|                         | ADRs                    | Architecture Decision Records (markdown)                    |
|                         | Onboarding Docs         | `README.md`, `CONTRIBUTING.md`, `.env.example`              |
| **11. Monitoring**      | Logs Aggregation        | ELK, Loki, or Fluentd                                       |
|                         | Dashboards              | Grafana                                                     |
|                         | Alerting                | Prometheus AlertManager, PagerDuty                          |
|                         | Distributed Tracing     | Jaeger, Zipkin                                              |
| **12. Data Management** | Primary DB              | PostgreSQL                                                  |
|                         | Migrations              | Flyway (preferred), Liquibase                               |
|                         | Dev/CI Database         | Testcontainers or Docker DB                                 |
|                         | Seed Data               | Migration scripts, profile-based seeding                    |

### [Twelve-Factor App Alignment](https://12factor.net/)

1. **Codebase**  
   One codebase tracked in version control, many deploys.  
   *CaptainsLog is a mono-repo Maven project, versioned with Git, deployable independently.*

2. **Dependencies**  
   Explicitly declare and isolate dependencies.  
   *All dependencies are managed via Maven. No reliance on system packages. Testcontainers ensure runtime parity.*

3. **Config**  
   Store configuration in the environment.  
   *Application configuration is externalized using `application.yml` and environment variables. Values are bound to typed classes via `@ConfigurationProperties`.*

4. **Backing Services**  
   Treat backing services as attached resources.  
   *PostgreSQL is accessed via a port (interface) abstraction. It can be swapped without modifying the domain logic.*

5. **Build, Release, Run**  
   Strictly separate build and run stages.  
   *CaptainsLog follows a clear build pipeline: Maven builds → Docker image → K8s deployment via Helm.*

6. **Processes**  
   Execute the app as one or more stateless processes.  
   *The service is stateless. State is stored in the database. It scales horizontally via Kubernetes.*

7. **Port Binding**  
   Export services via port binding.  
   *Spring Boot embeds a web server. The service is exposed on port 8080 (configurable via `server.port`).*

8. **Concurrency**  
   Scale out via the process model.  
   *Stateless design enables concurrent processing. Resilience4j provides bulkheading and circuit breaking.*

9. **Disposability**  
   Maximize robustness with fast startup and graceful shutdown.  
   *The service boots in seconds. Lifecycle hooks are in place for graceful shutdown. Testcontainers verify boot-time behavior.*

10. **Dev/Prod Parity**  
    Keep development, staging, and production as similar as possible.  
    *Testcontainers and Docker ensure dev mirrors production. Same image runs across environments.*

11. **Logs**  
    Treat logs as event streams.  
    *Structured JSON logs are emitted to stdout. External log systems (e.g., Loki, ELK) can aggregate and process logs.*

12. **Admin Processes**  
    Run admin/management tasks as one-off processes.  
    *Spring Boot Actuator exposes `/health`, `/env`, `/metrics`, and more. Scripts and container commands support DB migrations and diagnostics.*

## Getting Started

1. Create an empty parent pom

    ```bash
    mvn archetype:generate \
    -DarchetypeGroupId=org.codehaus.mojo.archetypes \
    -DarchetypeArtifactId=pom-root \
    -DgroupId=com.starlight \
    -DartifactId=captainslog \
    -Dversion=1.0.0-SNAPSHOT \
    -DinteractiveMode=false
    ```

## Java debugging in VSCode

1. [Launch Configuration](https://code.visualstudio.com/docs/debugtest/debugging#_launch-configurations)
1. [Java Debugging](https://code.visualstudio.com/docs/java/java-debugging)

## Project layout

```shell
captainslog/
├── bootstrap/                             # Spring Boot app entrypoint + config
│   ├── CaptainsLogApplication.java
│   ├── config/
│   │   ├── SwaggerConfig.java
│   │   ├── JpaConfig.java
│   │   └── ApplicationProperties.java
│   ├── monitoring/
│   │   └── OpenTelemetryConfig.java
│   ├── resources/
│   │   ├── application.yml
│   │   ├── logback-spring.xml
│   │   └── messages.properties
│   └── test/
│       └── AppSmokeTest.java
│
├── domain/                                # Core domain logic
│   ├── model/
│   │   ├── Todo.java
│   │   └── TodoStatus.java
│   └── port/
│       ├── in/
│       │   └── CreateTodoUseCase.java
│       └── out/
│           └── SaveTodoPort.java
│
├── application/                           # Use case implementations
│   └── service/
│       └── CreateTodoService.java         # Implements CreateTodoUseCase
│
├── infrastructure/                        # Persistence, integrations, system adapters
│   └── persistence/
│       └── JpaTodoAdapter.java            # Implements SaveTodoPort
│
├── adapters/                              # Driving adapters (HTTP, CLI, messaging)
│   └── rest/
│       ├── TodoController.java
│       └── dto/
│           ├── TodoRequest.java
│           └── TodoResponse.java
│
├── k8s/                                   # Deployment manifests
│   ├── deployment.yaml
│   ├── service.yaml
│   └── configmap.yaml
├── Dockerfile
├── pom.xml
└── README.md


```

## Maven Multi-module

| Flag   | Long Option              | Description                                    |
| ------ | ------------------------ | ---------------------------------------------- |
| `-pl`  | `--projects`             | Specify module(s) to build or run goals for    |
| `-am`  | `--also-make`            | Also build dependencies of the given module    |
| `-amd` | `--also-make-dependents` | Build modules that depend on the selected ones |

`mvn validate`

### Check effective pom

From project root run

```bash
 mvn -B help:effective-pom
```

> Always do a `mvn clean verify` from the project root after a refactor. Else old classes may cause build to pass.
>Example: `@SpringBootTest( classes = com.starlight.captainslog.rest.CaptainsLogBootstrap.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`
> This will pass if CaptainsLogBootstrap was moved to a new folder but the old class still exists. It will fail on a `mvn clean verify`
>

## NullGuard

| Tool            | Role                                                                |
| --------------- | ------------------------------------------------------------------- |
| **JSpecify**    | Adds standard annotations like `@Nullable`, `@NonNull`              |
| **NullAway**    | An **Error Prone plugin** that enforces null-safety at compile time |
| **Error Prone** | Static analyzer that plugs into `javac` to catch coding bugs        |

* [jspecifiy-nullaway-demo](https://github.com/sdeleuze/jspecify-nullway-demo)
* [jspecify talk](https://www.youtube.com/watch?v=5Lbxq6LP7FY&t=1348s)
* [JSpecify Demo Deck](null-safety-in-java-with-jspecify-and-nullaway-springio.pdf)

### Steps

1. Mark package as `@NullMarked` using `package-info.java`
2. Then mark the fields you want to guard against as `@Nullable`

See [LogEntryId.java](https://vscode.dev/github/66-24/captainslog/blob/main/domain/src/main/java/com/starlight/captainslog/domain/model/LogEntryId.java#L15)
You should see a build failure:

```bash
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.14.0:compile (default-compile) on project captainslog-domain: Compilation failure
[ERROR] /home/kirk/Projects/captainslog/domain/src/main/java/com/starlight/captainslog/domain/model/LogEntryId.java:[9,16] error: [NullAway] assigning @Nullable expression to @NonNull field
[ERROR]     (see http://t.uber.com/nullaway )
```

## SpringBoot Guidelines

1. [SpringBoot project structure](https://gist.github.com/sivaprasadreddy/9751db630b819b39e5e87f5ecfb53346#file-guidelines-md)
2. [SpringBoot Guidelines](https://github.com/JetBrains/junie-guidelines/blob/main/guidelines%2Fjava%2Fspring-boot%2Fguidelines-with-explanations.md)
3. [Spring Error Handling](https://github.com/wimdeblauwe/error-handling-spring-boot-starter)
4. [Why do I need better error handling](https://foojay.io/today/better-error-handling-for-your-spring-boot-rest-apis/)
5. [learn](https://spring.io/projects/spring-boot#learn)

## Spring

### API Testing with RestAssured and `@LocalServerPort`

We use [RestAssured](https://rest-assured.io/) for integration testing of HTTP endpoints. It provides a fluent Java DSL for writing expressive and readable API tests.

Spring Boot assigns a random port to the embedded server during tests. We use `@LocalServerPort` to inject this port and configure RestAssured accordingly:

```java
@LocalServerPort
int port;

@BeforeEach
void setup() {
    RestAssured.port = port;
}
```

This setup ensures:

1. No port conflicts
2. Realistic HTTP testing
3. Full application context is started and exercised

## Side Quests

[Devenv and Nix](https://www.youtube.com/watch?v=moBTEnkMch4&t=18s)
