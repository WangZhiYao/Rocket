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
include(":core:framework")
include(":core:model")
include(":core:infra")
include(":core:mediator")
include(":core:network")
include(":core:authenticate")

include(":data:database")
include(":data:datastore")

include(":domain:client")
include(":domain:contact")
include(":domain:character")

include(":feature:home")
include(":feature:client")
include(":feature:contact")
include(":feature:character")
