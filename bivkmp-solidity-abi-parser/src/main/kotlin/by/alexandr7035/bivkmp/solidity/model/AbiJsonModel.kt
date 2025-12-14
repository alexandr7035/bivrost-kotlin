package by.alexandr7035.bivkmp.solidity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AbiRoot(
    @SerialName("abi") val abi: List<AbiElementJson>,
    @SerialName("contractName") val contractName: String
)

@Serializable
internal class AbiElementJson(
    @SerialName("constant") val constant: Boolean = false,
    @SerialName("inputs") val inputs: List<ParameterJson> = emptyList(),
    @SerialName("name") val name: String = "",
    @SerialName("outputs") val outputs: List<ParameterJson> = emptyList(),
    @SerialName("payable") val payable: Boolean = false,
    @SerialName("type") val type: String = "function",
    @SerialName("anonymous") val anonymous: Boolean = false
)

@Serializable
internal class ParameterJson(
    @SerialName("name") val name: String,
    @SerialName("type") val type: String,
    @SerialName("components") val components: List<ParameterJson>? = null,
    @SerialName("indexed") val indexed: Boolean = false
)
