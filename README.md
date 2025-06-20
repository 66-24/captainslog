# Captains Log - take control of your journey

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

[configure vscode](https://code.visualstudio.com/docs/java/java-debugging)

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

## SpringBoot Guidelines

1. [SpringBoot project structure](https://gist.github.com/sivaprasadreddy/9751db630b819b39e5e87f5ecfb53346#file-guidelines-md)
2. [SpringBoot Guidelines](https://github.com/JetBrains/junie-guidelines/blob/main/guidelines%2Fjava%2Fspring-boot%2Fguidelines-with-explanations.md)
3. [Spring Error Handling](https://github.com/wimdeblauwe/error-handling-spring-boot-starter)
4. [Why do I need better error handling](https://foojay.io/today/better-error-handling-for-your-spring-boot-rest-apis/)
5. [learn](https://spring.io/projects/spring-boot#learn)

## Side Quests

[Devenv and Nix](https://www.youtube.com/watch?v=moBTEnkMch4&t=18s)
