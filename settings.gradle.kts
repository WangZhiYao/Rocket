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

include(":data:client")
include(":data:contacttype")
include(":data:account")
include(":data:character")
include(":data:zone")
include(":data:server")
include(":data:sect")
include(":data:internal")
include(":data:order")

include(":domain:client")
include(":domain:contacttype")
include(":domain:account")
include(":domain:character")
include(":domain:zone")
include(":domain:server")
include(":domain:sect")
include(":domain:internal")
include(":domain:order")

include(":feature:home")
include(":feature:user")
include(":feature:client")
include(":feature:contacttype")
include(":feature:account")
include(":feature:character")
include(":feature:order")
