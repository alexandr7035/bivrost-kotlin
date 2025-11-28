plugins {
    java
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
}

tasks.register<JavaExec>("runSolidityTypeGenerator") {
    val targetProject = project.parent?.childProjects?.get("bivkmp-solidity-types") ?: project
    val srcDirs = targetProject.extensions.getByType<SourceSetContainer>()
        .getByName("main")
        .kotlin
        .srcDirs
    if (srcDirs.isEmpty()) {
        logger.error("Couldn't find kotlin main source sets")
        return@register
    }

    val path = srcDirs.first().absolutePath
    mainClass.set("by.alexandr7035.bivkmp.SolidityTypeGenerator")
    classpath = sourceSets.main.get().runtimeClasspath
    args(path, project.group)
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(project(":bivkmp-utils"))
    implementation(project(":bivkmp-solidity-types"))
    implementation(libs.kotlinpoet)
    implementation(libs.bignum)
    testImplementation(libs.kotlin.test)
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

