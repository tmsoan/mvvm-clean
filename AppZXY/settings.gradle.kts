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

rootProject.name = "AppZXY"
include(":app")
include(":core:data")
include(":feature:home")
include(":feature:favorite")
include(":feature:trending")
include(":feature:settings")
include(":core:ui")
include(":core:model")
include(":core:network")
include(":core:domain")
