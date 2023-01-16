pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Rocket"

include(":app")
include(":core:framework")
include(":core:common")
include(":core:logger")
include(":core:model")
include(":core:mediator")
include(":core:network")
include(":core:database")
include(":core:datastore")
include(":core:authenticate")

include(":feature:home")
include(":feature:user")
include(":feature:client")
include(":data:client")
include(":domain:client")
