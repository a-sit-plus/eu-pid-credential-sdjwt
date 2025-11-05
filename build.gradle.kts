plugins {
    id("at.asitplus.gradle.conventions") version "20251023"
    kotlin("multiplatform") version "2.2.21" apply false
    kotlin("plugin.serialization") version "2.2.21" apply false
    id("de.infix.testBalloon") version "0.6.3-K2.2.20-SNAPSHOT" apply false
}

val artifactVersion: String by extra
group = "at.asitplus.wallet"
version = artifactVersion
