# Java Spring Boot Template Project

A production-ready Java Spring Boot template project with best practices for code quality, testing, and CI/CD integration.

## Prerequisites

### Required
- **Java 17 or higher** (Java 21 recommended)
  - Current system: Java 8 (needs upgrade)
  - Download: [Adoptium JDK](https://adoptium.net/) or [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
- **Gradle 8.11.1+** (included via wrapper)

### Optional
- Docker (for containerization)
- IDE: IntelliJ IDEA, Eclipse, or VS Code with Java extensions

## Current Configuration

**Note**: This project is configured for Spring Boot 3.3.5 which **requires Java 17+**.

Your system currently has **Java 8** installed. You have two options:

### Option 1: Install Java 17+ (Recommended)
1. Download and install Java 17 or 21 from [Adoptium](https://adoptium.net/)
2. Set `JAVA_HOME` environment variable to the new installation
3. Run the build: `./gradlew build`

### Option 2: Use Spring Boot 2.7.x (Temporary)
For compatibility with Java 8, I can downgrade to Spring Boot 2.7.18 (last Java 8 compatible version).
Note: Spring Boot 2.x reaches end-of-life soon, so upgrading Java is recommended.

## Project Structure

```
├── gradle/                   # Gradle wrapper files
├── src/
│   ├── main/
│   │   ├── java/            # Java source files
│   │   │   └── com/template/app/
│   │   │       └── Application.java
│   │   └── resources/       # Application resources
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       └── application-prod.yml
│   └── test/
│       ├── java/            # Test source files
│       └── resources/       # Test resources
├── build.gradle.kts         # Gradle build configuration (Kotlin DSL)
├── settings.gradle.kts      # Gradle settings
├── gradle.properties        # Gradle properties
└── README.md               # This file
```

## Building the Project

This project includes custom Gradle tasks and a Makefile for easy command execution.

### Quick Start (Using Makefile)
```bash
# Show all available commands
make help

# Build the project
make build

# Run tests with coverage
make coverage

# Run quality checks
make quality

# Complete build with all checks
make full-build

# Run the application
make run
```

### Gradle Tasks

#### Standard Tasks
```bash
./gradlew build           # Build the project
./gradlew test            # Run tests
./gradlew bootRun         # Run the application
./gradlew clean           # Clean build artifacts
```

#### Custom Tasks
```bash
./gradlew testWithCoverage   # Run tests with coverage report
./gradlew qualityCheck       # Run all quality checks (Checkstyle, PMD, SpotBugs)
./gradlew fullBuild          # Clean, build, test, and run all checks
./gradlew reports            # Show generated report locations
```

## Running the Application

### Using Gradle
```bash
./gradlew bootRun
```

### Using JAR
```bash
java -jar build/libs/java-startup-template.jar
```

### With Profile
```bash
# Development
./gradlew bootRun --args='--spring.profiles.active=dev'

# Production
java -jar build/libs/java-startup-template.jar --spring.profiles.active=prod
```

## Configuration

The application uses Spring profiles for environment-specific configuration:

- **dev** (default): Development settings with debug logging
- **prod**: Production settings with optimized logging

### Environment Variables
- `SPRING_PROFILE`: Set active profile (default: dev)
- `SERVER_PORT`: Set server port (default: 8080)

## Endpoints

### Application
- Base URL: `http://localhost:8080/api`

### Actuator (Monitoring)
- Health: `http://localhost:8080/api/actuator/health`
- Info: `http://localhost:8080/api/actuator/info`
- Metrics: `http://localhost:8080/api/actuator/metrics`

## Features Included

### Core Framework
- ✅ Gradle build tool with Kotlin DSL
- ✅ Spring Boot 3.3.5 framework
- ✅ Spring Boot Actuator (health checks, metrics)
- ✅ Multi-environment configuration (dev, prod)
- ✅ Spring DevTools for hot reload

### Code Quality & Testing
- ✅ JUnit 5 test framework
- ✅ Checkstyle (code style enforcement)
- ✅ PMD (static code analysis)
- ✅ SpotBugs (bug detection)
- ✅ JaCoCo (code coverage with 80% threshold)
- ✅ Custom Gradle tasks for common operations
- ✅ Makefile for Python/Make-style workflow

### Coming Next
- ⏳ Testing framework extensions (Mockito, AssertJ, REST Assured)
- ⏳ API documentation (SpringDoc/Swagger)
- ⏳ Security configuration
- ⏳ Docker support
- ⏳ CI/CD pipeline examples

## Code Quality

This project enforces strict code quality standards using multiple tools:

### Quality Checks
- **Checkstyle**: Enforces Java coding standards
- **PMD**: Detects common programming flaws
- **SpotBugs**: Identifies potential bugs
- **JaCoCo**: Ensures 80% code coverage

### Running Quality Checks
```bash
# Run all quality checks
make quality
# or
./gradlew qualityCheck

# Run specific checks
./gradlew checkstyleMain checkstyleTest
./gradlew pmdMain pmdTest
./gradlew spotbugsMain spotbugsTest

# Generate coverage report
make coverage
# or
./gradlew testWithCoverage
```

### View Reports
After running checks, reports are generated in:
- **Checkstyle**: `build/reports/checkstyle/`
- **PMD**: `build/reports/pmd/`
- **SpotBugs**: `build/reports/spotbugs/`
- **Coverage**: `build/reports/jacoco/test/html/index.html`
- **Test Results**: `build/reports/tests/test/index.html`

## Development

### IDE Setup

#### IntelliJ IDEA
1. Open the project root directory
2. IntelliJ will automatically detect Gradle and import the project
3. Wait for dependency resolution to complete
4. Run configurations will be auto-detected

#### VS Code
1. Install Java Extension Pack
2. Install Spring Boot Extension Pack
3. Open the project folder
4. Use Command Palette (Ctrl+Shift+P) → "Java: Create Java Project"

### Hot Reload
Spring DevTools is included for automatic application restart on code changes (dev profile).

## Troubleshooting

### Java Version Issues
```bash
# Check Java version
java -version

# Should show Java 17 or higher for Spring Boot 3.x
```

### Gradle Issues
```bash
# Clean and rebuild
./gradlew clean build --refresh-dependencies

# Clear Gradle cache
rm -rf ~/.gradle/caches/
```

### Port Already in Use
Change the port in `application.yml` or set environment variable:
```bash
export SERVER_PORT=8081
./gradlew bootRun
```

## Next Steps

Would you like me to:
1. **Downgrade to Spring Boot 2.7.x** (works with Java 8)
2. **Continue with Spring Boot 3.x** (after you install Java 17+)
3. **Add the next feature** (Checkstyle, testing framework, etc.)

## Contributing

1. Follow the code style guidelines (Checkstyle - coming soon)
2. Write tests for new features
3. Ensure all tests pass before committing
4. Keep code coverage above 80%

## License

[Add your license here]
