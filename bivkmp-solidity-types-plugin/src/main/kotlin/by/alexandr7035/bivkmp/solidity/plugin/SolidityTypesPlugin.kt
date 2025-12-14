package by.alexandr7035.bivkmp.solidity.plugin

import by.alexandr7035.bivkmp.solidity.AbiParser
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import javax.inject.Inject

open class BivkmpExtension @Inject constructor(objects: ObjectFactory) {
    val packageName: Property<String> = objects.property(String::class.java)
    val abiDir: DirectoryProperty = objects.directoryProperty()
    val outputDir: DirectoryProperty = objects.directoryProperty()
}

@Suppress("unused")
fun Project.solidityTypes(configure: BivkmpExtension.() -> Unit) {
    extensions.configure("solidityTypes", configure)
}

class SolidityTypesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val ext = project.extensions.create<BivkmpExtension>("solidityTypes")

        ext.abiDir.convention(project.layout.projectDirectory.dir("abi"))
        ext.outputDir.convention(project.layout.buildDirectory.dir("generated/source/abi/commonMain"))

        val generateTask = project.tasks.register<GenerateAbiWrapperTask>("generateAbiWrapper") {
            if (!ext.packageName.isPresent) {
                throw GradleException(
                    "Bivkmp: packageName is not set in solidityTypes extension. " +
                            "Please configure it in build.gradle.kts:\n\n" +
                            "solidityTypes {\n    packageName.set(\"my.pkg\")\n}"
                )
            }

            abiFolder.set(ext.abiDir)
            outputDir.set(ext.outputDir)
            packageName.set(ext.packageName)
        }

        val kmpExt = project.extensions.findByType(KotlinMultiplatformExtension::class.java)

        if (kmpExt != null) {
            kmpExt.sourceSets.getByName("commonMain").kotlin.srcDir(ext.outputDir)
            project.logger.lifecycle("Bivkmp: generating ABI wrappers for KMP commonMain")
        } else {
            val appExt = project.extensions.findByType(AppExtension::class.java)
            val libExt = project.extensions.findByType(LibraryExtension::class.java)

            val androidExt = appExt ?: libExt
            if (androidExt != null) {
                androidExt.sourceSets.getByName("main").java.srcDir(ext.outputDir)
                val platformName = if (appExt != null) "Android App" else "Android Library"
                project.logger.lifecycle("Bivkmp: generating ABI wrappers for $platformName")
            } else {
                project.logger.warn("Bivkmp: no KMP or Android extension found, skipping sourceSet registration")
            }
        }

        project.tasks.withType(KotlinCompile::class.java).configureEach {
            dependsOn(generateTask)
        }
    }
}

abstract class GenerateAbiWrapperTask : DefaultTask() {
    @get:InputDirectory
    abstract val abiFolder: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @get:Input
    abstract val packageName: Property<String>

    @TaskAction
    fun generate() {
        val abiDirFile = abiFolder.asFile.get()
        if (!abiDirFile.exists()) return

        val out = outputDir.asFile.get()
        if (out.exists()) out.deleteRecursively()
        out.mkdirs()

        val files = abiDirFile.listFiles()?.filter { it.isFile } ?: emptyList()
        if (files.isEmpty()) return

        val arraysMap = AbiParser.ArraysMap(packageName.get())

        files.forEach { file ->
            logger.lifecycle("Generating wrapper for ${file.name}")
            AbiParser.generateWrapper(packageName.get(), file.readText(), out, arraysMap)
        }

        arraysMap.generate(out)
    }
}
