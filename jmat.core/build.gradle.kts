/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.1.1/userguide/building_java_projects.html
 */

plugins {
	kotlin("jvm") version "1.4.30"
    id("maven-publish")
}

repositories {
    mavenCentral() 
}

dependencies {
    testImplementation("junit:junit:4.12")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.4.2")

    api("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.guava:guava:30.1-jre")
    implementation("org.apache.commons:commons-math3:3.6.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.test {
    useJUnitPlatform() 
    testLogging {
		events("passed", "skipped", "failed")
	}
}

publishing {
    repositories {
        maven {
            name = "JMatPackages"
            url = uri("https://maven.pkg.github.com/joshmcdonagh/jmat")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

version = "1.1.0"