rootProject.name = "crowdproj-resources-bookings"

pluginManagement {
    val kotlinVersion: String by settings
    val kotestVersion: String by settings
    val openapiVersion: String by settings
    val cwpGeneratorVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("io.kotest.multiplatform") version kotestVersion apply false
        id("org.openapi.generator") version openapiVersion apply false
        id("com.crowdproj.generator") version cwpGeneratorVersion apply false
    }
}

include("api-v1")
include("common")
include("mappers-v1")