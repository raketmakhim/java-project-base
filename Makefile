.PHONY: help install build test coverage quality clean run full-build reports

help: ## Show this help message
	@echo 'Usage: make [target]'
	@echo ''
	@echo 'Available targets:'
	@awk 'BEGIN {FS = ":.*##"; printf "\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  %-15s %s\n", $$1, $$2 }' $(MAKEFILE_LIST)

install: ## Install dependencies
	./gradlew build --refresh-dependencies

build: ## Build the project
	./gradlew build

test: ## Run tests
	./gradlew test

coverage: ## Run tests with coverage report
	./gradlew testWithCoverage

quality: ## Run quality checks (Checkstyle, PMD, SpotBugs)
	./gradlew qualityCheck

clean: ## Clean build artifacts
	./gradlew clean

run: ## Run the application
	./gradlew bootRun

full-build: ## Clean, build, test, and run all checks
	./gradlew fullBuild

reports: ## Show generated report locations
	./gradlew reports

jar: ## Build executable JAR
	./gradlew bootJar
	@echo "JAR created at: build/libs/JavaStartUp.jar"

verify: ## Run build with all verifications
	./gradlew clean check
