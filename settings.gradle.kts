plugins {
    id("com.gradle.enterprise") version "3.10.2"
}

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

rootProject.name = "ise-lab-code-template"
include("exercise")

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}
