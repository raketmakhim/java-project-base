import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    checkstyle
    pmd
    id("com.github.spotbugs") version "6.0.26"
    jacoco
}

group = "com.template"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Development Tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<BootJar>("bootJar") {
    archiveFileName.set("${project.name}.jar")
}

// Checkstyle Configuration
checkstyle {
    toolVersion = "10.20.2"
    configFile = file("${rootDir}/config/checkstyle/checkstyle.xml")
    isIgnoreFailures = false
    maxWarnings = 0
}

// PMD Configuration
pmd {
    toolVersion = "7.8.0"
    isConsoleOutput = true
    ruleSetFiles = files("${rootDir}/config/pmd/ruleset.xml")
    ruleSets = listOf() // Clear default rulesets
    isIgnoreFailures = false
}

// SpotBugs Configuration
spotbugs {
    toolVersion = "4.8.6"
    effort = com.github.spotbugs.snom.Effort.MAX
    reportLevel = com.github.spotbugs.snom.Confidence.LOW
    ignoreFailures = false
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask>().configureEach {
    reports {
        create("html") {
            required.set(true)
            outputLocation.set(file("${layout.buildDirectory.get()}/reports/spotbugs/${name}.html"))
        }
        create("xml") {
            required.set(false)
        }
    }
}

// JaCoCo Configuration
jacoco {
    toolVersion = "0.8.12"
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

tasks.register<JacocoCoverageVerification>("jacocoVerify") {
    dependsOn(tasks.jacocoTestReport)
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal()
            }
        }
        rule {
            element = "CLASS"
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.75".toBigDecimal()
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.named("jacocoVerify"))
}

// Custom task aliases for common operations
tasks.register("testWithCoverage") {
    group = "verification"
    description = "Runs tests and generates coverage report"
    dependsOn(tasks.test, tasks.jacocoTestReport)
    doLast {
        println("\n✅ Tests completed!")
        println("📊 Coverage report: ${layout.buildDirectory.get()}/reports/jacoco/test/html/index.html")
    }
}

tasks.register("qualityCheck") {
    group = "verification"
    description = "Runs all quality checks (Checkstyle, PMD, SpotBugs)"
    dependsOn(tasks.checkstyleMain, tasks.checkstyleTest, tasks.pmdMain, tasks.pmdTest, tasks.spotbugsMain, tasks.spotbugsTest)
    doLast {
        println("\n✅ All quality checks passed!")
    }
}

tasks.register("fullBuild") {
    group = "build"
    description = "Clean, build, test with coverage, and run all quality checks"
    dependsOn(tasks.clean, tasks.build)
    doLast {
        println("\n🎉 Full build completed successfully!")
        println("📦 Artifact: ${layout.buildDirectory.get()}/libs/${project.name}.jar")
        println("📊 Coverage: ${layout.buildDirectory.get()}/reports/jacoco/test/html/index.html")
        println("🔍 Quality Reports:")
        println("   - Checkstyle: ${layout.buildDirectory.get()}/reports/checkstyle/")
        println("   - PMD: ${layout.buildDirectory.get()}/reports/pmd/")
        println("   - SpotBugs: ${layout.buildDirectory.get()}/reports/spotbugs/")
    }
}

tasks.register("reports") {
    group = "reporting"
    description = "Opens all generated reports in browser"
    doLast {
        val reports = listOf(
            "${layout.buildDirectory.get()}/reports/jacoco/test/html/index.html",
            "${layout.buildDirectory.get()}/reports/tests/test/index.html"
        )
        println("\n📊 Generated reports:")
        reports.forEach { println("   - $it") }
    }
}
