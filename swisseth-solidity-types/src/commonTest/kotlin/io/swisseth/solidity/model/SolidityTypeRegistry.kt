package io.swisseth.solidity.model

import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.ByteArray
import kotlin.Function1
import kotlin.String
import kotlin.collections.Map

/**
 * Generated code. Do not modify
 * Internal registry for creating Solidity type instances without reflection.
 */
internal object SolidityTypeRegistry {
    internal val uintFactories: Map<String, Function1<BigInteger, SolidityBase.UIntBase>> = mapOf(
                    "uint8" to { value: BigInteger -> Solidity.UInt8(value) },
                    "uint16" to { value: BigInteger -> Solidity.UInt16(value) },
                    "uint24" to { value: BigInteger -> Solidity.UInt24(value) },
                    "uint32" to { value: BigInteger -> Solidity.UInt32(value) },
                    "uint40" to { value: BigInteger -> Solidity.UInt40(value) },
                    "uint48" to { value: BigInteger -> Solidity.UInt48(value) },
                    "uint56" to { value: BigInteger -> Solidity.UInt56(value) },
                    "uint64" to { value: BigInteger -> Solidity.UInt64(value) },
                    "uint72" to { value: BigInteger -> Solidity.UInt72(value) },
                    "uint80" to { value: BigInteger -> Solidity.UInt80(value) },
                    "uint88" to { value: BigInteger -> Solidity.UInt88(value) },
                    "uint96" to { value: BigInteger -> Solidity.UInt96(value) },
                    "uint104" to { value: BigInteger -> Solidity.UInt104(value) },
                    "uint112" to { value: BigInteger -> Solidity.UInt112(value) },
                    "uint120" to { value: BigInteger -> Solidity.UInt120(value) },
                    "uint128" to { value: BigInteger -> Solidity.UInt128(value) },
                    "uint136" to { value: BigInteger -> Solidity.UInt136(value) },
                    "uint144" to { value: BigInteger -> Solidity.UInt144(value) },
                    "uint152" to { value: BigInteger -> Solidity.UInt152(value) },
                    "uint160" to { value: BigInteger -> Solidity.UInt160(value) },
                    "uint168" to { value: BigInteger -> Solidity.UInt168(value) },
                    "uint176" to { value: BigInteger -> Solidity.UInt176(value) },
                    "uint184" to { value: BigInteger -> Solidity.UInt184(value) },
                    "uint192" to { value: BigInteger -> Solidity.UInt192(value) },
                    "uint200" to { value: BigInteger -> Solidity.UInt200(value) },
                    "uint208" to { value: BigInteger -> Solidity.UInt208(value) },
                    "uint216" to { value: BigInteger -> Solidity.UInt216(value) },
                    "uint224" to { value: BigInteger -> Solidity.UInt224(value) },
                    "uint232" to { value: BigInteger -> Solidity.UInt232(value) },
                    "uint240" to { value: BigInteger -> Solidity.UInt240(value) },
                    "uint248" to { value: BigInteger -> Solidity.UInt248(value) },
                    "uint256" to { value: BigInteger -> Solidity.UInt256(value) }
                )

    internal val intFactories: Map<String, Function1<BigInteger, SolidityBase.IntBase>> = mapOf(
                    "int8" to { value: BigInteger -> Solidity.Int8(value) },
                    "int16" to { value: BigInteger -> Solidity.Int16(value) },
                    "int24" to { value: BigInteger -> Solidity.Int24(value) },
                    "int32" to { value: BigInteger -> Solidity.Int32(value) },
                    "int40" to { value: BigInteger -> Solidity.Int40(value) },
                    "int48" to { value: BigInteger -> Solidity.Int48(value) },
                    "int56" to { value: BigInteger -> Solidity.Int56(value) },
                    "int64" to { value: BigInteger -> Solidity.Int64(value) },
                    "int72" to { value: BigInteger -> Solidity.Int72(value) },
                    "int80" to { value: BigInteger -> Solidity.Int80(value) },
                    "int88" to { value: BigInteger -> Solidity.Int88(value) },
                    "int96" to { value: BigInteger -> Solidity.Int96(value) },
                    "int104" to { value: BigInteger -> Solidity.Int104(value) },
                    "int112" to { value: BigInteger -> Solidity.Int112(value) },
                    "int120" to { value: BigInteger -> Solidity.Int120(value) },
                    "int128" to { value: BigInteger -> Solidity.Int128(value) },
                    "int136" to { value: BigInteger -> Solidity.Int136(value) },
                    "int144" to { value: BigInteger -> Solidity.Int144(value) },
                    "int152" to { value: BigInteger -> Solidity.Int152(value) },
                    "int160" to { value: BigInteger -> Solidity.Int160(value) },
                    "int168" to { value: BigInteger -> Solidity.Int168(value) },
                    "int176" to { value: BigInteger -> Solidity.Int176(value) },
                    "int184" to { value: BigInteger -> Solidity.Int184(value) },
                    "int192" to { value: BigInteger -> Solidity.Int192(value) },
                    "int200" to { value: BigInteger -> Solidity.Int200(value) },
                    "int208" to { value: BigInteger -> Solidity.Int208(value) },
                    "int216" to { value: BigInteger -> Solidity.Int216(value) },
                    "int224" to { value: BigInteger -> Solidity.Int224(value) },
                    "int232" to { value: BigInteger -> Solidity.Int232(value) },
                    "int240" to { value: BigInteger -> Solidity.Int240(value) },
                    "int248" to { value: BigInteger -> Solidity.Int248(value) },
                    "int256" to { value: BigInteger -> Solidity.Int256(value) }
                )

    internal val bytesFactories: Map<String, Function1<ByteArray, SolidityBase.StaticBytes>> =
            mapOf(
                    "bytes1" to { bytes: ByteArray -> Solidity.Bytes1(bytes) },
                    "bytes2" to { bytes: ByteArray -> Solidity.Bytes2(bytes) },
                    "bytes3" to { bytes: ByteArray -> Solidity.Bytes3(bytes) },
                    "bytes4" to { bytes: ByteArray -> Solidity.Bytes4(bytes) },
                    "bytes5" to { bytes: ByteArray -> Solidity.Bytes5(bytes) },
                    "bytes6" to { bytes: ByteArray -> Solidity.Bytes6(bytes) },
                    "bytes7" to { bytes: ByteArray -> Solidity.Bytes7(bytes) },
                    "bytes8" to { bytes: ByteArray -> Solidity.Bytes8(bytes) },
                    "bytes9" to { bytes: ByteArray -> Solidity.Bytes9(bytes) },
                    "bytes10" to { bytes: ByteArray -> Solidity.Bytes10(bytes) },
                    "bytes11" to { bytes: ByteArray -> Solidity.Bytes11(bytes) },
                    "bytes12" to { bytes: ByteArray -> Solidity.Bytes12(bytes) },
                    "bytes13" to { bytes: ByteArray -> Solidity.Bytes13(bytes) },
                    "bytes14" to { bytes: ByteArray -> Solidity.Bytes14(bytes) },
                    "bytes15" to { bytes: ByteArray -> Solidity.Bytes15(bytes) },
                    "bytes16" to { bytes: ByteArray -> Solidity.Bytes16(bytes) },
                    "bytes17" to { bytes: ByteArray -> Solidity.Bytes17(bytes) },
                    "bytes18" to { bytes: ByteArray -> Solidity.Bytes18(bytes) },
                    "bytes19" to { bytes: ByteArray -> Solidity.Bytes19(bytes) },
                    "bytes20" to { bytes: ByteArray -> Solidity.Bytes20(bytes) },
                    "bytes21" to { bytes: ByteArray -> Solidity.Bytes21(bytes) },
                    "bytes22" to { bytes: ByteArray -> Solidity.Bytes22(bytes) },
                    "bytes23" to { bytes: ByteArray -> Solidity.Bytes23(bytes) },
                    "bytes24" to { bytes: ByteArray -> Solidity.Bytes24(bytes) },
                    "bytes25" to { bytes: ByteArray -> Solidity.Bytes25(bytes) },
                    "bytes26" to { bytes: ByteArray -> Solidity.Bytes26(bytes) },
                    "bytes27" to { bytes: ByteArray -> Solidity.Bytes27(bytes) },
                    "bytes28" to { bytes: ByteArray -> Solidity.Bytes28(bytes) },
                    "bytes29" to { bytes: ByteArray -> Solidity.Bytes29(bytes) },
                    "bytes30" to { bytes: ByteArray -> Solidity.Bytes30(bytes) },
                    "bytes31" to { bytes: ByteArray -> Solidity.Bytes31(bytes) },
                    "bytes32" to { bytes: ByteArray -> Solidity.Bytes32(bytes) }
                )
}
