plugins {
    kotlin("jvm") apply false
}

group = "com.crowdproj"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version
}