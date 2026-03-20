# AI Context - Java Spring Boot Template Project

> Quick reference for AI assistants and developers to understand this project rapidly

## 🎯 Project Purpose
Production-ready Spring Boot template with enterprise-grade code quality tools. Serves as a starting point for new Java projects.

## 📦 Tech Stack
```yaml
Language: Java 21
Framework: Spring Boot 3.3.5
Build Tool: Gradle 8.11.1 (Kotlin DSL)
Testing: JUnit 5
Quality Tools:
  - Checkstyle 10.20.2 (code style)
  - PMD 7.8.0 (static analysis)
  - SpotBugs 4.8.6 (bug detection)
  - JaCoCo 0.8.12 (coverage, min 80%)
```

## 📁 Project Structure
```
src/main/java/com/template/app/
  └── Application.java          # Spring Boot entry point
                                 # CHECKSTYLE: Suppressed HideUtilityClassConstructor + FinalClass
                                 # Reason: Spring needs to proxy this class
src/test/java/com/template/app/
  └── ApplicationTests.java     # Context load test with assertion
config/
  ├── checkstyle/checkstyle.xml # Style rules (SuppressionCommentFilter enabled)
  └── pmd/ruleset.xml           # PMD rules
build.gradle.kts                # Build config + custom tasks
tasks.bat / tasks.ps1           # Windows task runners
Makefile                        # Unix/Linux task runner
TASKS.md                        # Task runner documentation
```

## 🔧 Custom Gradle Tasks
```bash
testWithCoverage   # Tests + JaCoCo report
qualityCheck       # Checkstyle + PMD + SpotBugs
fullBuild          # Clean + build + test + quality
reports            # Show report locations
```

## 🚀 Task Runners (All equivalent)
```bash
# Windows
tasks.bat <command>

# PowerShell
.\tasks.ps1 <command>

# Unix/Linux/Mac
make <command>

# Direct Gradle
./gradlew <task>
```

### Common Commands
| Command | Purpose |
|---------|---------|
| `build` | Compile and package |
| `test` | Run tests only |
| `coverage` | Tests + coverage report |
| `quality` | All quality checks |
| `full-build` | Complete pipeline |
| `run` | Start Spring Boot app |
| `clean` | Remove build artifacts |

## ⚙️ Code Quality Configuration

### Checkstyle
- **Config**: `config/checkstyle/checkstyle.xml`
- **Suppression**: Use `// CHECKSTYLE:OFF: RuleName` comments
- **Key Rules**:
  - Max line length: 120
  - Max file length: 500
  - No utility class public constructors
  - Classes with only private constructors should be final
- **Note**: Application.java has suppressions for Spring Boot compatibility

### PMD
- **Config**: `config/pmd/ruleset.xml`
- **Warnings**: Some excluded rules don't exist in PMD 7.8 (safe to ignore)
- **Key**: Unit tests must have assertions

### SpotBugs
- **Effort**: MAX
- **Report**: HTML in `build/reports/spotbugs/`
- **Confidence**: LOW (catches more issues)

### JaCoCo
- **Threshold**: 80% overall, 75% per class
- **Report**: `build/reports/jacoco/test/html/index.html`
- **Note**: main() method not covered (expected for Spring Boot)

## 🔍 Build Behavior
```bash
./gradlew build
  ├── Compile
  ├── Test (with JaCoCo)
  ├── Checkstyle (main + test)
  ├── PMD (main + test)
  ├── SpotBugs (main + test)
  └── JaCoCo verify (80% threshold)
```

## 🐛 Known Quirks

### 1. Application.java Checkstyle Suppressions
```java
// CHECKSTYLE:OFF: HideUtilityClassConstructor
// CHECKSTYLE:OFF: FinalClass
@SpringBootApplication
public class Application { ... }
// CHECKSTYLE:ON
```
**Why**: Spring Boot needs to create CGLIB proxies, can't be final or have private constructor.

### 2. PMD Warnings
Build shows warnings about non-existent rules (JUnitTestContainsTooManyAsserts, etc). These are **safe to ignore** - rules renamed in PMD 7.x.

### 3. Test Coverage
Main method shows 0% coverage. This is **expected and correct** - Spring Boot entry points aren't unit tested.

### 4. Windows vs Unix
- Windows: Use `tasks.bat` (no dependencies) or `gradlew.bat`
- Unix/Mac: Use `make` or `./gradlew`
- Makefile requires `make` on Windows (Git Bash, WSL, or Chocolatey)

## 🎨 Code Conventions

### Application Entry Point
```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
**Don't**: Make it final, add private constructor, or add @Bean methods here

### Tests
```java
@SpringBootTest
class SomeTests {
    @Autowired
    private ApplicationContext context;

    @Test
    void testSomething() {
        assertNotNull(context); // PMD requires assertions
    }
}
```
**Must**: Include at least one assertion per test method (PMD rule)

## 📊 Report Locations
```
build/reports/
├── jacoco/test/html/index.html      # Coverage
├── tests/test/index.html            # Test results
├── checkstyle/                      # Style violations
├── pmd/                             # PMD findings
└── spotbugs/                        # Bug reports
```

## 🔄 Typical Workflows

### Development Cycle
```bash
# 1. Make changes
# 2. Quick feedback
./gradlew test

# 3. Before commit
./gradlew fullBuild
```

### Adding New Features
```bash
# 1. Write code + tests
# 2. Check quality
./gradlew qualityCheck

# 3. Check coverage
./gradlew testWithCoverage

# 4. Full verification
./gradlew fullBuild
```

### CI/CD
```bash
./gradlew clean check  # Standard CI command
# or
./gradlew fullBuild    # More verbose with report paths
```

## 🚨 Troubleshooting

### Build Fails with Checkstyle Error
- Check `build/reports/checkstyle/main.html`
- Use `// CHECKSTYLE:OFF: RuleName` if legitimate exception
- Must have `SuppressionCommentFilter` in checkstyle.xml

### Test Fails: "Context failed to load"
- Usually means Spring can't create bean
- Check if class is final when it shouldn't be
- Check constructor accessibility

### Coverage Below 80%
- Add more test cases
- Check `build/reports/jacoco/test/html/index.html` for uncovered lines
- main() methods don't need coverage

### Port 8080 Already in Use
```bash
# Find process
netstat -ano | findstr :8080

# Kill it (Windows)
taskkill //F //PID <PID>

# Or change port
./gradlew bootRun --args='--server.port=8081'
```

## 🎓 Quick Start for AI

1. **Read this file first** (you are here)
2. **Check** `build.gradle.kts` for dependencies
3. **Review** `config/` for quality tool configs
4. **Run** `./gradlew tasks` to see all available tasks
5. **Build** `./gradlew fullBuild` to verify everything works

## 📝 File Priorities (for AI to read)

When analyzing this project, read in this order:
1. `AI_CONTEXT.md` (this file) - Overview
2. `build.gradle.kts` - Build config, dependencies, custom tasks
3. `src/main/java/com/template/app/Application.java` - Entry point
4. `src/test/java/com/template/app/ApplicationTests.java` - Test pattern
5. `config/checkstyle/checkstyle.xml` - Style rules
6. `TASKS.md` - Command reference
7. `README.md` - User documentation

## 🔐 Important: DO NOT MODIFY

### Critical Suppressions
The Application.java Checkstyle suppressions are **critical** - removing them breaks Spring Boot's proxying mechanism.

### Quality Thresholds
- JaCoCo: 80% (configured in build.gradle.kts)
- Checkstyle: 0 violations (maxWarnings = 0)
- PMD/SpotBugs: 0 violations (ignoreFailures = false)

## 💡 Key Insights

1. **This is a template** - Designed to be cloned and customized
2. **Quality first** - All quality tools fail the build by default
3. **Task runners** - Multiple interfaces (make/bat/ps1) for different environments
4. **Spring Boot patterns** - Follows Spring Boot best practices
5. **Test assertions required** - PMD enforces this
6. **Windows-friendly** - Batch and PowerShell scripts included
7. **Coverage is reasonable** - 80% threshold, main() exempted

## 🌍 Language Note
This file is in English for compatibility. Project supports internationalization through Spring Boot's standard i18n mechanism.

---
**Last Updated**: 2026-02-12
**Spring Boot Version**: 3.3.5
**Java Version**: 21
**Gradle Version**: 8.11.1
