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

rootProject.name = "exercise2"
include(":app")
include(":circlesmodule")
include(":primenumbersmodule")
include(":thermohydrometermodule")
include(":loggermodule")
