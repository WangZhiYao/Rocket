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
// Core
include(":core:framework")
include(":core:common")
include(":core:logger")
include(":core:model")
include(":core:mediator")
include(":core:network")
include(":core:database")
include(":core:datastore")
include(":core:authenticate")
// Data
include(":data:client")
include(":data:contacttype")
include(":data:account")
include(":data:character")
include(":data:zoneserver")
include(":data:sectinternal")
include(":data:order")
include(":data:paymentmethod")
include(":data:category")
include(":data:subject")
// Domain
include(":domain:common")
include(":domain:client")
include(":domain:contacttype")
include(":domain:account")
include(":domain:character")
include(":domain:zoneserver")
include(":domain:sectinternal")
include(":domain:order")
include(":domain:paymentmethod")
include(":domain:category")
include(":domain:subject")
// Feature
include(":feature:home")
include(":feature:client")
include(":feature:contacttype")
include(":feature:account")
include(":feature:character")
include(":feature:zoneserver")
include(":feature:sectinternal")
include(":feature:order")
include(":feature:paymentmethod")
include(":feature:category")
include(":feature:subject")
include(":feature:user")
include(":feature:setting")
