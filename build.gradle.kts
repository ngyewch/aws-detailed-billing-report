plugins {
    `java-library`
    id("ca.cutterslade.analyze") version "1.9.0"
    id("com.asarkar.gradle.build-time-tracker") version "4.3.0"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("se.ascp.gradle.gradle-versions-filter") version "0.1.16"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("commons-io:commons-io:2.11.0")
    implementation("joda-time:joda-time:2.11.2")
    implementation("org.apache.commons:commons-csv:1.9.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    permitUnusedDeclared("commons-io:commons-io:2.11.0")
}

repositories {
    mavenCentral()
}
