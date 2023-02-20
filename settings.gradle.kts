pluginManagement {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Invoices Test App"
include(":app")
include(":data")
include(":domain")
include(":core")
include(":splash_screen")
include(":invoice_info_screen")
include(":invoice_info_details_screen")
include(":test-utils")
