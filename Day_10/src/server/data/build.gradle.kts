plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.21"
}

group = "org.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("com.h2database:h2:2.2.224")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}