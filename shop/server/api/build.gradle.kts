import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.ktor.plugin") version "2.1.3"
}

group = "com.keygenqt.shop.api"
version = "0.9.0"

application {
    mainClass.set("com.keygenqt.shop.api.ApplicationKt")
}

tasks.withType<KotlinCompile> {
    compilerOptions.languageVersion.set(KotlinVersion.KOTLIN_2_0)
}

dependencies {
    implementation(project(":shared"))
    // Ktor
    implementation(apiApp.bundles.ktor)

    implementation("io.ktor:ktor-server-websockets:2.1.3")
}

ktor {
    fatJar {
        archiveFileName.set("shop-api.jar")
    }
}