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
include(":core:infra")
include(":core:mediator")
include(":core:network")
include(":core:authenticate")

include(":data:database")
include(":data:datastore")
include(":data:model")

include(":domain:client")

include(":feature:home")
include(":feature:client")
