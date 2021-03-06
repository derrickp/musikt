/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.30"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    id("io.gitlab.arturbosch.detekt").version("1.6.0")
    id("org.jlleitschuh.gradle.ktlint").version("10.0.0")
}

detekt {
    toolVersion = "1.6.0"
    config = files("config/detekt/detekt.yml")
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
            // Include any other artifacts here, like javadocs
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/derrickp/musikt")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        maven("https://plugins.gradle.org/m2/")
    }
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
    jcenter {
        content {
            // just allow to include kotlinx projects
            // detekt needs 'kotlinx-html' for the html report
            includeGroup("org.jetbrains.kotlinx")
        }
    }
}

dependencies {
    api(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.4.30"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.squareup.moshi:moshi:1.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.11.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("com.airbnb.okreplay:junit:1.5.0")
    testImplementation("com.airbnb.okreplay:okreplay:1.5.0")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
