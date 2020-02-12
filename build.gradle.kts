plugins {
    kotlin("jvm") version "1.3.61"
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
    id("io.qameta.allure") version "2.8.1"
}

subprojects {
    group = properties["GROUP"].toString()
    version = properties["VERSION"].toString()

    buildscript {
        repositories {
            maven("https://plugins.gradle.org/m2/")
        }
        dependencies {
            classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
        }
    }

    apply {
        plugin("org.jetbrains.kotlin.jvm")
    }

    repositories {
        jcenter()
        mavenCentral()
    }

    dependencies {
        val implementation by configurations
        val testImplementation by configurations
        val testRuntimeOnly by configurations
        val runtimeOnly by configurations

        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
        implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
        implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.10.1")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
        implementation("com.github.mifmif:generex:1.0.2")
        implementation("io.github.classgraph:classgraph:4.8.56")
        implementation("org.slf4j:slf4j-api:1.7.28")
        testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
        testImplementation("io.kotlintest:kotlintest-extensions-allure:3.4.2")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
        testRuntimeOnly("ch.qos.logback:logback-core:1.2.3")
        testRuntimeOnly("ch.qos.logback:logback-classic:1.2.3")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
        testRuntimeOnly("org.codehaus.groovy:groovy:2.5.6")
        runtimeOnly(kotlin("script-runtime"))
    }

    tasks.withType<Jar> {
        manifest {
            attributes(
                mapOf(
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to project.version,
                    "Class-Path" to this@subprojects.configurations.compileClasspath.get().joinToString(" ") { it.name }
                )
            )
        }

        archiveClassifier.set("sources")
        from(sourceSets.getByName("main").allSource)
        from("LICENCE.md") {
            into("META-INF")
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    tasks.withType<Wrapper> {
        this.gradleVersion = "6.0.1"
        this.distributionType = Wrapper.DistributionType.ALL
    }

    val test by tasks.getting(Test::class) {
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
