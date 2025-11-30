plugins {
    java
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
}

tasks.register<JavaExec>("runSolidityTypeGenerator") {
    val targetProject = project.parent?.childProjects?.get("bivkmp-solidity-types") ?: project
    val kotlinExtension = targetProject.extensions.getByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()
    
    val commonMainSourceSet = kotlinExtension.sourceSets.getByName("commonMain")
    val commonMainSrcDirs = commonMainSourceSet.kotlin.srcDirs
    if (commonMainSrcDirs.isEmpty()) {
        logger.error("Couldn't find kotlin commonMain source sets")
        return@register
    }
    
    val commonTestSourceSet = kotlinExtension.sourceSets.getByName("commonTest")
    val commonTestSrcDirs = commonTestSourceSet.kotlin.srcDirs
    if (commonTestSrcDirs.isEmpty()) {
        logger.error("Couldn't find kotlin commonTest source sets")
        return@register
    }

    val commonMainPath = commonMainSrcDirs.first().absolutePath
    val commonTestPath = commonTestSrcDirs.first().absolutePath
    mainClass.set("by.alexandr7035.bivkmp.SolidityTypeGenerator")
    classpath = sourceSets.main.get().runtimeClasspath
    args(commonMainPath, commonTestPath, project.group)
}

dependencies {
    implementation(project(":bivkmp-utils"))
    implementation(project(":bivkmp-solidity-types"))

    implementation(libs.kotlin.stdlib)
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
