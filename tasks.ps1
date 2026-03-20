# PowerShell task runner for Java Spring Boot project
# Usage: .\tasks.ps1 <command>

param(
    [Parameter(Position=0)]
    [string]$Command = "help"
)

function Show-Help {
    Write-Host ""
    Write-Host "Java Spring Boot Template - Task Runner" -ForegroundColor Cyan
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Usage: .\tasks.ps1 <command>" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Available commands:" -ForegroundColor Green
    Write-Host "  help          - Show this help message"
    Write-Host "  install       - Install dependencies"
    Write-Host "  build         - Build the project"
    Write-Host "  test          - Run tests"
    Write-Host "  coverage      - Run tests with coverage report"
    Write-Host "  quality       - Run quality checks (Checkstyle, PMD, SpotBugs)"
    Write-Host "  clean         - Clean build artifacts"
    Write-Host "  run           - Run the application"
    Write-Host "  full-build    - Clean, build, test, and run all checks"
    Write-Host "  reports       - Show generated report locations"
    Write-Host "  jar           - Build executable JAR"
    Write-Host "  verify        - Run build with all verifications"
    Write-Host ""
}

switch ($Command.ToLower()) {
    "help" {
        Show-Help
    }
    "install" {
        Write-Host "Installing dependencies..." -ForegroundColor Cyan
        .\gradlew.bat build --refresh-dependencies
    }
    "build" {
        Write-Host "Building project..." -ForegroundColor Cyan
        .\gradlew.bat build
    }
    "test" {
        Write-Host "Running tests..." -ForegroundColor Cyan
        .\gradlew.bat test
    }
    "coverage" {
        Write-Host "Running tests with coverage..." -ForegroundColor Cyan
        .\gradlew.bat testWithCoverage
    }
    "quality" {
        Write-Host "Running quality checks..." -ForegroundColor Cyan
        .\gradlew.bat qualityCheck
    }
    "clean" {
        Write-Host "Cleaning build artifacts..." -ForegroundColor Cyan
        .\gradlew.bat clean
    }
    "run" {
        Write-Host "Running application..." -ForegroundColor Cyan
        .\gradlew.bat bootRun
    }
    "full-build" {
        Write-Host "Running full build..." -ForegroundColor Cyan
        .\gradlew.bat fullBuild
    }
    "reports" {
        Write-Host "Showing report locations..." -ForegroundColor Cyan
        .\gradlew.bat reports
    }
    "jar" {
        Write-Host "Building JAR..." -ForegroundColor Cyan
        .\gradlew.bat bootJar
        Write-Host "JAR created at: build\libs\JavaStartUp.jar" -ForegroundColor Green
    }
    "verify" {
        Write-Host "Running verification..." -ForegroundColor Cyan
        .\gradlew.bat clean check
    }
    default {
        Write-Host "Unknown command: $Command" -ForegroundColor Red
        Write-Host ""
        Show-Help
        exit 1
    }
}
