@file:JvmName("SolidityTypeGenerator")

package io.swisseth.solidity

import io.swisseth.solidity.utils.GeneratorUtils
import io.swisseth.solidity.model.SolidityBase
import com.ionspin.kotlin.bignum.integer.BigInteger
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import java.io.File

// Explicit ClassName for BigInteger to avoid ambiguity with java.math.BigInteger
private val BigIntegerClassName = BigInteger::class.asClassName()

fun main(vararg args: String) {
    args.forEach { println(it.split(File.separator).last()) }
    if (args.size >= 3) {
        generate(args[0], args[1], args[2])
    }
}

fun generate(commonMainPath: String, commonTestPath: String, packageName: String) {
    val fileName = "Solidity"
    val indentation = "    "

    val modelPackageName = "$packageName.solidity.model"
    val kotlinFile = FileSpec.builder(modelPackageName, fileName)
    val solidityGeneratedObject = TypeSpec.objectBuilder(fileName)

    kotlinFile.addImport("io.swisseth.solidity.utils", "padEndMultiple", "toHex")
    solidityGeneratedObject.addKdoc("Generated code. Do not modify\n")

    // Generate types
    val uInts = generateUInts()
    val ints = generateInts()
    val staticBytes = generateStaticBytes()
    val address = generateAddress()
    val bool = generateBool()
    val dynamicBytes = generateDynamicBytes()
    val string = generateString()

    // Add types to object
    solidityGeneratedObject.addTypes(uInts)
    solidityGeneratedObject.addTypes(ints)
    solidityGeneratedObject.addTypes(staticBytes)
    solidityGeneratedObject.addType(address)
    solidityGeneratedObject.addType(bool)
    solidityGeneratedObject.addType(dynamicBytes)
    solidityGeneratedObject.addType(string)

    val mapClassName = ClassName("kotlin.collections", "Map")
    val stringClassName = ClassName("kotlin", "String")
    val mapType = mapClassName.parameterizedBy(stringClassName, stringClassName)

    // Generate aliases map
    val aliasesMapContent = arrayOf(
            "int" to "int256",
            "uint" to "uint256",
            "byte" to "bytes1"
    )
            .joinToString(",\n") { "\"${it.first}\" to \"${it.second}\"" }

    val aliasesMapBlock = CodeBlock.builder().add(CodeBlock.of("mapOf(\n$aliasesMapContent)"))

    // Add map to object
    solidityGeneratedObject.addProperty(PropertySpec.builder("aliases", mapType).initializer(aliasesMapBlock.build()).build())

    // Generate type map
    val typeMapContent = (uInts + ints + staticBytes + address + bool + dynamicBytes + string)
            .map { it.name?.lowercase() to it.name }
            .joinToString(",\n") { "\"${it.first}\" to \"$modelPackageName.$fileName.${it.second}\"" }

    val typeMapBlock = CodeBlock.builder().add(CodeBlock.of("mapOf(\n$typeMapContent)"))

    // Add map to object
    solidityGeneratedObject.addProperty(PropertySpec.builder("types", mapType).initializer(typeMapBlock.build()).build())

    // Write object file to commonMain - KotlinPoet will create directory structure based on package
    kotlinFile.indent(indentation).addType(solidityGeneratedObject.build()).build().writeTo(File(commonMainPath))
    
    // Generate type registry to commonTest
    generateTypeRegistry(commonTestPath, modelPackageName, uInts, ints, staticBytes, fileName, indentation)
}

private fun generateUInts(): List<TypeSpec> = (8..256 step 8).map { generateUInt("UInt$it", it) }.toList()

private fun generateUInt(className: String, nBits: Int): TypeSpec {
    val decoderTypeName = SolidityBase.UIntBase.Decoder::class.asClassName().parameterizedBy(ClassName("", className))
    return TypeSpec.classBuilder(className)
            .addModifiers(KModifier.DATA)
            .superclass(SolidityBase.UIntBase::class)
            .addSuperclassConstructorParameter("%1L, %2L", "value", nBits)
            .addType(GeneratorUtils.generateDecoderCompanion(
                    decoderTypeName,
                    CodeBlock.of("%1T({ %2L(it) })", decoderTypeName, className)))
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", BigIntegerClassName).build()).build())
            .addProperty(PropertySpec.builder("value", BigIntegerClassName)
                    .initializer("value")
                    .build())
            .build()
}

private fun generateAddress(): TypeSpec {
    val name = "Address"
    val decoderTypeName = SolidityBase.UIntBase.Decoder::class.asClassName().parameterizedBy(ClassName("", name))
    return TypeSpec.classBuilder(name)
            .addModifiers(KModifier.DATA)
            .superclass(SolidityBase.UIntBase::class)
            .addSuperclassConstructorParameter("%1L, %2L", "value", "160")
            .addType(GeneratorUtils.generateDecoderCompanion(
                    decoderTypeName,
                    CodeBlock.of("%1T({ %2L(it) })", decoderTypeName, name)))
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", BigIntegerClassName).build()).build())
            .addProperty(PropertySpec.builder("value", BigIntegerClassName)
                    .initializer("value")
                    .build())
            .build()
}

private fun generateBool(): TypeSpec {
    val name = "Bool"
    val decoderTypeName = ClassName.bestGuess("Decoder")
    return TypeSpec.classBuilder(name)
            .addModifiers(KModifier.DATA)
            .superclass(SolidityBase.UIntBase::class)
            .addSuperclassConstructorParameter("if (%1L) %2T.ONE else %2T.ZERO, %3L", "value", BigIntegerClassName, "8")
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", Boolean::class).build()).build())
            .addProperty(PropertySpec.builder("value", Boolean::class)
                    .initializer("value")
                    .build())
            .addType(GeneratorUtils.generateDecoder(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2T.decodeBool(%3L.consume()))", name, SolidityBase::class, "source")
                            .build(),
                    false)
            )
            .addType(GeneratorUtils.generateDecoderCompanion(
                    decoderTypeName,
                    CodeBlock.of("%1T()", decoderTypeName)))
            .build()
}

private fun generateInts() = (8..256 step 8).map { generateInt("Int$it", it) }.toList()

private fun generateInt(className: String, nBits: Int): TypeSpec {
    val decoderTypeName = SolidityBase.IntBase.Decoder::class.asClassName().parameterizedBy(ClassName("", className))
    return TypeSpec.classBuilder(className)
            .addModifiers(KModifier.DATA)
            .superclass(SolidityBase.IntBase::class)
            .addSuperclassConstructorParameter("%1L, %2L", "value", nBits)
            .addType(GeneratorUtils.generateDecoderCompanion(
                    decoderTypeName,
                    CodeBlock.of("%1T({ %2L(it) })", decoderTypeName, className)))
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", BigIntegerClassName).build()).build())
            .addProperty(PropertySpec.builder("value", BigIntegerClassName)
                    .initializer("value")
                    .build())
            .build()
}

private fun generateStaticBytes() = (1..32).map {
    val name = "Bytes$it"
    val decoderTypeName = SolidityBase.StaticBytes.Decoder::class.asClassName().parameterizedBy(ClassName("", name))
    TypeSpec.classBuilder(name)
            .superclass(SolidityBase.StaticBytes::class)
            .addSuperclassConstructorParameter("%1L, %2L", "bytes", it)
            .addType(GeneratorUtils.generateDecoderCompanion(
                    decoderTypeName,
                    CodeBlock.of("%1T({ %2L(it) }, %3L)", decoderTypeName, name, it)))
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("bytes", ByteArray::class).build()).build())
            .addProperty(PropertySpec.builder("bytes", ByteArray::class)
                    .initializer("bytes")
                    .build())
            .build()
}.toList()

private fun generateDynamicBytes(): TypeSpec {
    val name = "Bytes"
    val decoderTypeName = ClassName.bestGuess("Decoder")
    return TypeSpec.classBuilder(name)
            .addModifiers(KModifier.OPEN)
            .addSuperinterface(SolidityBase.DynamicType::class)
            .primaryConstructor(FunSpec.constructorBuilder()
                    .addParameter("items", ByteArray::class)
                    .build())
            .addProperty(PropertySpec.builder("items", ByteArray::class).initializer("items").build())
            .addInitializerBlock(CodeBlock.builder()
                    .addStatement("if (%1T.parseString(items.size.toString(10), 10) > %1T.TWO.pow(256)) throw %2T()", BigIntegerClassName, ClassName("kotlin", "Exception"))
                    .build())
            .addFunction(FunSpec.builder("encode")
                    .addModifiers(KModifier.OVERRIDE)
                    .returns(String::class)
                    .addCode(CodeBlock.builder()
                            .addStatement("val parts = encodeParts()")
                            .addStatement("return parts.static + parts.dynamic")
                            .build())
                    .build())
            .addFunction(FunSpec.builder("encodeParts")
                    .addModifiers(KModifier.PRIVATE)
                    .returns(SolidityBase.DynamicType.Parts::class)
                    .addCode(CodeBlock.builder()
                            .addStatement("val length = items.size.toString(16).padStart(64, '0')")
                            .addStatement("val contents = items.toHex().padEndMultiple(64, '0')")
                            .addStatement("return %1T(length, contents)", SolidityBase.DynamicType.Parts::class)
                            .build())
                    .build())
        .addFunction(FunSpec.builder("encodePacked")
            .addModifiers(KModifier.OVERRIDE)
            .returns(String::class)
            .addCode(CodeBlock.builder()
                .addStatement("return items.toHex()")
                .build())
            .build())
            .addType(GeneratorUtils.generateDecoder(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2T.decodeBytes(source))", name, SolidityBase::class)
                            .build()))
            .addType(GeneratorUtils.generateDecoderCompanion(
                    decoderTypeName,
                    CodeBlock.of("%1T()", decoderTypeName)))
            .build()
}

private fun generateString(): TypeSpec {
    val name = "String"
    val superClass = ClassName.bestGuess("Bytes")
    val decoderTypeName = ClassName.bestGuess("Decoder")
    return TypeSpec.classBuilder(name)
            .addModifiers(KModifier.DATA)
            .superclass(superClass)
            .addSuperclassConstructorParameter("%1L.encodeToByteArray()", "value")
            .primaryConstructor(FunSpec.constructorBuilder().addParameter(
                    ParameterSpec.builder("value", String::class).build()).build())
            .addProperty(PropertySpec.builder("value", String::class)
                    .initializer("value")
                    .build())
            .addType(GeneratorUtils.generateDecoder(name,
                    CodeBlock.builder()
                            .addStatement("return %1N(%2T.decodeString(%3L))", name, SolidityBase::class, "source")
                            .build())
            )
            .addType(GeneratorUtils.generateDecoderCompanion(
                    decoderTypeName,
                    CodeBlock.of("%1T()", decoderTypeName)))
            .build()
}

private fun generateTypeRegistry(commonTestPath: String, modelPackageName: String, uInts: List<TypeSpec>, ints: List<TypeSpec>, staticBytes: List<TypeSpec>, solidityObjectName: String, indentation: String) {
    val registryFileName = "SolidityTypeRegistry"
    val registryFile = FileSpec.builder(modelPackageName, registryFileName)
    val registryObject = TypeSpec.objectBuilder(registryFileName)
        .addModifiers(KModifier.INTERNAL)
    
    // Add imports - only external dependencies, not same-package classes (Solidity and SolidityBase are in the same package)
    registryFile.addImport("com.ionspin.kotlin.bignum.integer", "BigInteger")
    
    registryObject.addKdoc("Generated code. Do not modify\n")
    registryObject.addKdoc("Internal registry for creating Solidity type instances without reflection.\n")
    
    val stringClassName = ClassName("kotlin", "String")
    val mapClassName = ClassName("kotlin.collections", "Map")
    val uintBaseClassName = SolidityBase.UIntBase::class.asClassName()
    val intBaseClassName = SolidityBase.IntBase::class.asClassName()
    val staticBytesClassName = SolidityBase.StaticBytes::class.asClassName()
    val bigIntegerClassName = BigIntegerClassName
    val byteArrayClassName = ClassName("kotlin", "ByteArray")
    
    // Define functional types for factories
    val uintFactoryType = ClassName("kotlin", "Function1").parameterizedBy(bigIntegerClassName, uintBaseClassName)
    val intFactoryType = ClassName("kotlin", "Function1").parameterizedBy(bigIntegerClassName, intBaseClassName)
    val bytesFactoryType = ClassName("kotlin", "Function1").parameterizedBy(byteArrayClassName, staticBytesClassName)
    
    // Generate UInt factories map
    val solidityClassName = ClassName(modelPackageName, solidityObjectName)
    val uintFactoriesType = mapClassName.parameterizedBy(stringClassName, uintFactoryType)
    val uintFactoryMapBuilder = CodeBlock.builder()
    uintFactoryMapBuilder.add("mapOf(\n")
    uInts.forEachIndexed { index, typeSpec ->
        val typeName = typeSpec.name!!
        val mapKey = typeName.lowercase()
        val comma = if (index < uInts.size - 1) "," else ""
        uintFactoryMapBuilder.add("$indentation$indentation\"$mapKey\" to { value: %T -> %T.$typeName(value) }$comma\n",
            bigIntegerClassName,
            solidityClassName
        )
    }
    uintFactoryMapBuilder.add("$indentation)")
    
    registryObject.addProperty(PropertySpec.builder("uintFactories", uintFactoriesType)
        .addModifiers(KModifier.INTERNAL)
        .initializer(uintFactoryMapBuilder.build())
        .build())
    
    // Generate Int factories map
    val intFactoriesType = mapClassName.parameterizedBy(stringClassName, intFactoryType)
    val intFactoryMapBuilder = CodeBlock.builder()
    intFactoryMapBuilder.add("mapOf(\n")
    ints.forEachIndexed { index, typeSpec ->
        val typeName = typeSpec.name!!
        val mapKey = typeName.lowercase()
        val comma = if (index < ints.size - 1) "," else ""
        intFactoryMapBuilder.add("$indentation$indentation\"$mapKey\" to { value: %T -> %T.$typeName(value) }$comma\n",
            bigIntegerClassName,
            solidityClassName
        )
    }
    intFactoryMapBuilder.add("$indentation)")
    
    registryObject.addProperty(PropertySpec.builder("intFactories", intFactoriesType)
        .addModifiers(KModifier.INTERNAL)
        .initializer(intFactoryMapBuilder.build())
        .build())
    
    // Generate Bytes factories map
    val bytesFactoriesType = mapClassName.parameterizedBy(stringClassName, bytesFactoryType)
    val bytesFactoryMapBuilder = CodeBlock.builder()
    bytesFactoryMapBuilder.add("mapOf(\n")
    staticBytes.forEachIndexed { index, typeSpec ->
        val typeName = typeSpec.name!!
        val mapKey = typeName.lowercase()
        val comma = if (index < staticBytes.size - 1) "," else ""
        bytesFactoryMapBuilder.add("$indentation$indentation\"$mapKey\" to { bytes: %T -> %T.$typeName(bytes) }$comma\n",
            byteArrayClassName,
            solidityClassName
        )
    }
    bytesFactoryMapBuilder.add("$indentation)")
    
    registryObject.addProperty(PropertySpec.builder("bytesFactories", bytesFactoriesType)
        .addModifiers(KModifier.INTERNAL)
        .initializer(bytesFactoryMapBuilder.build())
        .build())
    
    // Write registry file to commonTest - KotlinPoet will create directory structure based on package
    registryFile.indent(indentation)
        .addType(registryObject.build())
        .build()
        .writeTo(File(commonTestPath))
}
