plugins {
    kotlin("jvm")
    id("io.qameta.allure") version "2.8.1"
}

dependencies {
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
                "Class-Path" to configurations.compileClasspath.get().joinToString(" ") { it.name }
            )
        )
    }

    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
    from("LICENCE.md") {
        into("META-INF")
    }
}


allure {

}