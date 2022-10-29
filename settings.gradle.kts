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
include(":core:common")
include(":core:database")
include(":core:logger")
include(":core:mediator")

include(":feature:home")
include(":feature:customer")

//include(":domain:customer")
//include(":domain:character")
include(":core:datastore")
include(":core:network")
include(":data:customer")
include(":core:infra")
include(":core:framework")
