pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "HappyMoonDay"
include(":app")
include(":feature")
include(":feature:home")
include(":core")
include(":core:network")
include(":data")
include(":data:search")
include(":feature:search")
include(":feature:product_detail")
include(":core:common")
include(":core:database")
include(":data:product_detail")
include(":feature:bookmark")
