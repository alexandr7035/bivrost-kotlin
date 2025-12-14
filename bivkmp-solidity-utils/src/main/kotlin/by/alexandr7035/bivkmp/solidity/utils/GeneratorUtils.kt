package by.alexandr7035.bivkmp.solidity.utils

import by.alexandr7035.bivkmp.solidity.model.SolidityBase
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

object GeneratorUtils {
    fun generateDecoderCompanion(decoderTypeName: TypeName, decoderInit: CodeBlock) = TypeSpec.companionObjectBuilder()
        .addProperty(
            PropertySpec.builder("DECODER", decoderTypeName)
                .initializer(decoderInit)
                .build()
        )
        .build()

    fun generateDecoder(name: String, decodeCode: CodeBlock, isDynamic: Boolean = true, paramName: String = "source") =
        generateDecoderBuilder(ClassName("", name), decodeCode, CodeBlock.of("return %L", isDynamic), paramName).build()

    fun generateDecoderBuilder(forClass: TypeName, decodeCode: CodeBlock, isDynamicBlock: CodeBlock, paramName: String = "source"): TypeSpec.Builder {
        return TypeSpec.classBuilder("Decoder")
            .addSuperinterface(SolidityBase.TypeDecoder::class.asClassName().parameterizedBy(forClass))
            .addFunction(generateIsDynamicFunction(isDynamicBlock))
            .addFunction(generateDecodeSourceFunction(decodeCode, forClass, paramName))
    }

    fun generateDecodeSourceFunction(decodeCode: CodeBlock, returnType: TypeName, paramName: String) =
        FunSpec.builder("decode")
            .addParameter(paramName, SolidityBase.PartitionData::class)
            .addModifiers(KModifier.OVERRIDE)
            .returns(returnType)
            .addCode(decodeCode)
            .build()

    fun generateIsDynamicFunction(isDynamicBlock: CodeBlock): FunSpec = FunSpec.builder("isDynamic")
        .addModifiers(KModifier.OVERRIDE)
        .returns(Boolean::class)
        .addCode(isDynamicBlock)
        .build()
}
