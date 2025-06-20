# Captains Log - take control of your journey

## Getting Started

## Java debugging in VSCode

[configure vscode](https://code.visualstudio.com/docs/java/java-debugging)

## Project layout

```shell
captainslog/
├── app/
│   └── CaptainsLogApplication.java           # Main Spring Boot app class
│
├── domain/                                    # Core business logic (no Spring here)
│   ├── model/                                 # Entities, Value Objects, Aggregates
│   │   ├── Todo.java
│   │   ├── TodoId.java
│   │   └── TodoStatus.java
│   └── port/
│       ├── in/                                # Input ports (use cases)
│       │   ├── CreateTodoUseCase.java
│       │   └── GetTodoQuery.java
│       └── out/                               # Output ports (e.g., repository, observability)
│           ├── SaveTodoPort.java
│           └── LoadTodoPort.java
│
├── application/                               # Use case implementations
│   └── service/
│       └── CreateTodoService.java             # Implements input ports using domain logic
│
├── adapters/
│   ├── in/                                    # Driving adapters (HTTP, CLI, messaging)
│   │   └── rest/
│   │       ├── TodoController.java
│   │       └── dto/                           # DTOs for API layer
│   │           ├── TodoRequest.java
│   │           └── TodoResponse.java
│   └── out/                                   # Driven adapters (persistence, observability)
│       ├── persistence/
│       │   ├── TodoEntity.java
│       │   ├── JpaTodoRepository.java
│       │   └── JpaTodoAdapter.java            # Implements SaveTodoPort + LoadTodoPort
│       └── observability/
│           └── OpenTelemetryConfig.java
│
├── config/                                    # Configuration classes
│   ├── JpaConfig.java
│   ├── SwaggerConfig.java
│   └── ApplicationProperties.java             # Bound to application.yml using @ConfigurationProperties
│
├── resources/
│   ├── application.yml                        # App configuration
│   ├── logback-spring.xml                     # Logging config
│   └── messages.properties                    # I18n support
│
├── test/
│   ├── domain/                                # Unit tests for domain logic
│   ├── application/                           # Use case tests
│   ├── adapters/                              # Controller and integration tests
│   └── integration/
│       └── PostgresTestcontainersTest.java    # Real DB tests using Testcontainers
│
├── k8s/                                       # Kubernetes manifests
│   ├── deployment.yaml
│   ├── service.yaml
│   └── configmap.yaml
│
├── Dockerfile                                 # GraalVM native build image
├── pom.xml                                    # Parent Maven POM
└── README.md

```

## SpringBoot Guidelines

1. [SpringBoot project structure](https://gist.github.com/sivaprasadreddy/9751db630b819b39e5e87f5ecfb53346#file-guidelines-md)
2. [SpringBoot Guidelines](https://github.com/JetBrains/junie-guidelines/blob/main/guidelines%2Fjava%2Fspring-boot%2Fguidelines-with-explanations.md)
3. [Spring Error Handling](https://github.com/wimdeblauwe/error-handling-spring-boot-starter)
4. [Why do I need better error handling](https://foojay.io/today/better-error-handling-for-your-spring-boot-rest-apis/)


## Side Quests

[Devenv and Nix](https://www.youtube.com/watch?v=moBTEnkMch4&t=18s)