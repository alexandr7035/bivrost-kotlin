plugins {
    `java-library`
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    compileOnly(gradleApi())
    implementation(project(":bivkmp-abi-parser"))
    implementation(libs.android.gradle.plugin)
}

sourceSets {
    main {
        kotlin {
            srcDir("build/generated/source/abi")
        }
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
    repositories {
        mavenLocal()
        maven {
            url = uri("../repo")
        }
    }
}

