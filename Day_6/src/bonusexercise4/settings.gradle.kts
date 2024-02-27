pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "bonusexercise4"
include(":app")
include(":circlesmodule")
include(":primenumbersmodule")
include(":thermohydrometermodule")
include(":loggermodule")
include(":speechmodule")
