import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    kotlin("jvm") version "1.3.61" apply false
}

subprojects {
    group = properties["GROUP"].toString()
    version = properties["VERSION"].toString()

    repositories {
        jcenter()
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform {}

        // show standard out and standard error of the test JVM(s) on the console
        testLogging.showStandardStreams = true

        // Always run tests, even when nothing changed.
        dependsOn("cleanTest")

        testLogging {
            showStandardStreams = true
            events(
                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
                org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
            )
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true

            debug {
                events(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
                )
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            }

            info.events = debug.events
            info.exceptionFormat = debug.exceptionFormat
        }
    }
}

tasks.withType<Wrapper> {
    this.gradleVersion = "6.0.1"
    this.distributionType = Wrapper.DistributionType.ALL
}
