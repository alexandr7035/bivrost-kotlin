package by.alexandr7035.bivkmp.solidity

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import java.io.File
import kotlin.io.path.createTempDirectory

class AbiGeneratorTest {
    private lateinit var generatedFolder: File

    companion object {
        const val PACKAGE_NAME = "expected"
        val PATH = PACKAGE_NAME.replace('.', File.separatorChar)
    }

    private fun setup() {
        generatedFolder = createTempDirectory("testGeneratedCode").toFile()
    }

    @Test
    fun testGeneratedCode() {
        setup()
        val testsFolder = File(javaClass.classLoader.getResource("automatic_tests").toURI())
        for (testFolder in testsFolder.listFiles().sortedBy { it.name }) {
            testAbi(testFolder)
        }
    }

    private fun testAbi(testFolder: File) {
        println("Testing ${testFolder.nameWithoutExtension}")

        generatedFolder.deleteRecursively()
        val abisFolder = File(testFolder, "abis")

        val arraysMap = AbiParser.ArraysMap(PACKAGE_NAME)
        abisFolder.listFiles()?.forEach { jsonAbi ->
            val jsonAbiContents = readAllFromFile(jsonAbi)
            AbiParser.generateWrapper(PACKAGE_NAME, jsonAbiContents, generatedFolder, arraysMap)
        }

        arraysMap.generate(generatedFolder)

        val generatedRootFolder = File(generatedFolder, PATH)
        assertTrue(generatedRootFolder.exists() && generatedRootFolder.isDirectory,
            "Root folder was not generated")

        val expectedFolder = File(testFolder, PATH)
        checkGeneratedFolder(generatedRootFolder, expectedFolder)
        checkGeneratedFileList(generatedRootFolder, expectedFolder)
    }

    private fun checkGeneratedFileList(generatedFolder: File, expectedFolder: File) {
        expectedFolder.listFiles()?.forEach {
            val generated = File(generatedFolder, it.name)
            assertTrue(generated.exists() &&
                    generated.isDirectory == it.isDirectory && generated.isFile == it.isFile,
                    "$it was not generated!")
            if (it.isDirectory) {
                checkGeneratedFileList(generated, it)
            }
        }
    }

    private fun checkGeneratedFolder(generatedFolder: File, expectedFolder: File) {
        generatedFolder.listFiles()?.forEach {
            if (it.isDirectory) {
                val target = File(expectedFolder, it.name)
                assertTrue(target.exists() && target.isDirectory,
                    "$it was not expected as a generated folder!")
                checkGeneratedFolder(it, target)
            } else {
                checkGeneratedFile(it, expectedFolder)
            }
        }
    }

    private fun checkGeneratedFile(generatedFile: File, expectedFolder: File) {
        val expectedFile = File(expectedFolder, generatedFile.name)
        assertTrue(expectedFile.exists() && expectedFile.isFile,
            "$generatedFile was not expected to be generated!")

        val generatedContent = readAllFromFile(generatedFile)
        val expectedContent = readAllFromFile(expectedFile)

        assertEquals(expectedContent, generatedContent,
            "${generatedFile.name} does not match the expected file")
    }

    private fun readAllFromFile(file: File) = file.bufferedReader().use { it.readText() }
}
