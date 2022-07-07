plugins {
    kotlin("jvm") version "1.7.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
    group = "it.unibo.tuprolog.demo"
}

dependencies {
    // Align versions of all Kotlin components
    implementation("org.jetbrains.kotlin", "kotlin-bom", "1.7.0")
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8", "1.7.0")

    // Import all 2P-Kt modules
    implementation("it.unibo.tuprolog", "full-jvm", "0.20.9")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin", "kotlin-test", "1.7.10")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin", "kotlin-test-junit", "1.7.0")
}
