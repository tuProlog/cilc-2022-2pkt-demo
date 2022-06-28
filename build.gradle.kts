plugins {
    alias(libs.plugins.kotlin.jvm)
}

allprojects {
    repositories {
        mavenCentral()
    }
    group = "it.unibo.tuprolog.demo"
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform(libs.kotlin.bom))
    // Use the Kotlin JDK 8 standard library.
    implementation(libs.kotlin.stdlib.jvm)

    // Import all 2P-Kt modules
    implementation(libs.tuprolog.full)

    // Use the Kotlin test library.
    testImplementation(libs.kotlin.test)
    // Use the Kotlin JUnit integration.
    testImplementation(libs.kotlin.test.junit)
}
