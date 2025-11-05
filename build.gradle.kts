plugins {
    id("at.asitplus.gradle.conventions") version "20251023"
    kotlin("multiplatform") version "2.2.21" apply false
    kotlin("plugin.serialization") version "2.2.21" apply false
}

val artifactVersion: String by extra
group = "at.asitplus.wallet"
version = artifactVersion
