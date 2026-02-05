pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://maven.fabricmc.net")
        maven("https://libraries.minecraft.net/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "TenetPaper"

include("tenet-api")
include("tenet-server")
include("fabric-loader")
include("paperclip")
include("paperclip:java17")
include("fabric-loader:minecraft")