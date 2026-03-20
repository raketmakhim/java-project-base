@echo off
REM Batch task runner for Java Spring Boot project
REM Usage: tasks.bat <command>

if "%1"=="" goto help
if "%1"=="help" goto help
if "%1"=="install" goto install
if "%1"=="build" goto build
if "%1"=="test" goto test
if "%1"=="coverage" goto coverage
if "%1"=="quality" goto quality
if "%1"=="clean" goto clean
if "%1"=="run" goto run
if "%1"=="full-build" goto full-build
if "%1"=="reports" goto reports
if "%1"=="jar" goto jar
if "%1"=="verify" goto verify
goto unknown

:help
echo.
echo Java Spring Boot Template - Task Runner
echo ========================================
echo.
echo Usage: tasks.bat ^<command^>
echo.
echo Available commands:
echo   help          - Show this help message
echo   install       - Install dependencies
echo   build         - Build the project
echo   test          - Run tests
echo   coverage      - Run tests with coverage report
echo   quality       - Run quality checks (Checkstyle, PMD, SpotBugs)
echo   clean         - Clean build artifacts
echo   run           - Run the application
echo   full-build    - Clean, build, test, and run all checks
echo   reports       - Show generated report locations
echo   jar           - Build executable JAR
echo   verify        - Run build with all verifications
echo.
goto end

:install
echo Installing dependencies...
gradlew.bat build --refresh-dependencies
goto end

:build
echo Building project...
gradlew.bat build
goto end

:test
echo Running tests...
gradlew.bat test
goto end

:coverage
echo Running tests with coverage...
gradlew.bat testWithCoverage
goto end

:quality
echo Running quality checks...
gradlew.bat qualityCheck
goto end

:clean
echo Cleaning build artifacts...
gradlew.bat clean
goto end

:run
echo Running application...
gradlew.bat bootRun
goto end

:full-build
echo Running full build...
gradlew.bat fullBuild
goto end

:reports
echo Showing report locations...
gradlew.bat reports
goto end

:jar
echo Building JAR...
gradlew.bat bootJar
echo JAR created at: build\libs\JavaStartUp.jar
goto end

:verify
echo Running verification...
gradlew.bat clean check
goto end

:unknown
echo Unknown command: %1
echo.
goto help

:end
