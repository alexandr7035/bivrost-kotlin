package `by`.alexandr7035.bivkmp.solidity.model

import `by`.alexandr7035.bivkmp.solidity.utils.padEndMultiple
import `by`.alexandr7035.bivkmp.solidity.utils.toHex
import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.Boolean
import kotlin.ByteArray
import kotlin.Exception
import kotlin.collections.Map

/**
 * Generated code. Do not modify
 */
public object Solidity {
    public val aliases: Map<kotlin.String, kotlin.String> = mapOf(
            "int" to "int256",
            "uint" to "uint256",
            "byte" to "bytes1")

    public val types: Map<kotlin.String, kotlin.String> = mapOf(
            "uint8" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt8",
            "uint16" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt16",
            "uint24" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt24",
            "uint32" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt32",
            "uint40" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt40",
            "uint48" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt48",
            "uint56" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt56",
            "uint64" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt64",
            "uint72" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt72",
            "uint80" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt80",
            "uint88" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt88",
            "uint96" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt96",
            "uint104" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt104",
            "uint112" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt112",
            "uint120" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt120",
            "uint128" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt128",
            "uint136" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt136",
            "uint144" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt144",
            "uint152" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt152",
            "uint160" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt160",
            "uint168" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt168",
            "uint176" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt176",
            "uint184" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt184",
            "uint192" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt192",
            "uint200" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt200",
            "uint208" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt208",
            "uint216" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt216",
            "uint224" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt224",
            "uint232" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt232",
            "uint240" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt240",
            "uint248" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt248",
            "uint256" to "by.alexandr7035.bivkmp.solidity.model.Solidity.UInt256",
            "int8" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int8",
            "int16" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int16",
            "int24" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int24",
            "int32" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int32",
            "int40" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int40",
            "int48" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int48",
            "int56" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int56",
            "int64" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int64",
            "int72" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int72",
            "int80" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int80",
            "int88" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int88",
            "int96" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int96",
            "int104" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int104",
            "int112" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int112",
            "int120" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int120",
            "int128" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int128",
            "int136" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int136",
            "int144" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int144",
            "int152" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int152",
            "int160" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int160",
            "int168" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int168",
            "int176" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int176",
            "int184" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int184",
            "int192" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int192",
            "int200" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int200",
            "int208" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int208",
            "int216" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int216",
            "int224" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int224",
            "int232" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int232",
            "int240" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int240",
            "int248" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int248",
            "int256" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Int256",
            "bytes1" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes1",
            "bytes2" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes2",
            "bytes3" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes3",
            "bytes4" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes4",
            "bytes5" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes5",
            "bytes6" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes6",
            "bytes7" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes7",
            "bytes8" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes8",
            "bytes9" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes9",
            "bytes10" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes10",
            "bytes11" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes11",
            "bytes12" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes12",
            "bytes13" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes13",
            "bytes14" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes14",
            "bytes15" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes15",
            "bytes16" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes16",
            "bytes17" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes17",
            "bytes18" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes18",
            "bytes19" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes19",
            "bytes20" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes20",
            "bytes21" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes21",
            "bytes22" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes22",
            "bytes23" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes23",
            "bytes24" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes24",
            "bytes25" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes25",
            "bytes26" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes26",
            "bytes27" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes27",
            "bytes28" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes28",
            "bytes29" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes29",
            "bytes30" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes30",
            "bytes31" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes31",
            "bytes32" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes32",
            "address" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Address",
            "bool" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bool",
            "bytes" to "by.alexandr7035.bivkmp.solidity.model.Solidity.Bytes",
            "string" to "by.alexandr7035.bivkmp.solidity.model.Solidity.String")

    public data class UInt8(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 8) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt8> =
                    SolidityBase.UIntBase.Decoder<UInt8>({ UInt8(it) })
        }
    }

    public data class UInt16(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 16) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt16> =
                    SolidityBase.UIntBase.Decoder<UInt16>({ UInt16(it) })
        }
    }

    public data class UInt24(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 24) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt24> =
                    SolidityBase.UIntBase.Decoder<UInt24>({ UInt24(it) })
        }
    }

    public data class UInt32(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 32) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt32> =
                    SolidityBase.UIntBase.Decoder<UInt32>({ UInt32(it) })
        }
    }

    public data class UInt40(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 40) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt40> =
                    SolidityBase.UIntBase.Decoder<UInt40>({ UInt40(it) })
        }
    }

    public data class UInt48(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 48) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt48> =
                    SolidityBase.UIntBase.Decoder<UInt48>({ UInt48(it) })
        }
    }

    public data class UInt56(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 56) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt56> =
                    SolidityBase.UIntBase.Decoder<UInt56>({ UInt56(it) })
        }
    }

    public data class UInt64(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 64) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt64> =
                    SolidityBase.UIntBase.Decoder<UInt64>({ UInt64(it) })
        }
    }

    public data class UInt72(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 72) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt72> =
                    SolidityBase.UIntBase.Decoder<UInt72>({ UInt72(it) })
        }
    }

    public data class UInt80(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 80) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt80> =
                    SolidityBase.UIntBase.Decoder<UInt80>({ UInt80(it) })
        }
    }

    public data class UInt88(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 88) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt88> =
                    SolidityBase.UIntBase.Decoder<UInt88>({ UInt88(it) })
        }
    }

    public data class UInt96(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 96) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt96> =
                    SolidityBase.UIntBase.Decoder<UInt96>({ UInt96(it) })
        }
    }

    public data class UInt104(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 104) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt104> =
                    SolidityBase.UIntBase.Decoder<UInt104>({ UInt104(it) })
        }
    }

    public data class UInt112(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 112) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt112> =
                    SolidityBase.UIntBase.Decoder<UInt112>({ UInt112(it) })
        }
    }

    public data class UInt120(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 120) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt120> =
                    SolidityBase.UIntBase.Decoder<UInt120>({ UInt120(it) })
        }
    }

    public data class UInt128(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 128) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt128> =
                    SolidityBase.UIntBase.Decoder<UInt128>({ UInt128(it) })
        }
    }

    public data class UInt136(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 136) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt136> =
                    SolidityBase.UIntBase.Decoder<UInt136>({ UInt136(it) })
        }
    }

    public data class UInt144(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 144) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt144> =
                    SolidityBase.UIntBase.Decoder<UInt144>({ UInt144(it) })
        }
    }

    public data class UInt152(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 152) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt152> =
                    SolidityBase.UIntBase.Decoder<UInt152>({ UInt152(it) })
        }
    }

    public data class UInt160(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 160) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt160> =
                    SolidityBase.UIntBase.Decoder<UInt160>({ UInt160(it) })
        }
    }

    public data class UInt168(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 168) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt168> =
                    SolidityBase.UIntBase.Decoder<UInt168>({ UInt168(it) })
        }
    }

    public data class UInt176(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 176) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt176> =
                    SolidityBase.UIntBase.Decoder<UInt176>({ UInt176(it) })
        }
    }

    public data class UInt184(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 184) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt184> =
                    SolidityBase.UIntBase.Decoder<UInt184>({ UInt184(it) })
        }
    }

    public data class UInt192(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 192) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt192> =
                    SolidityBase.UIntBase.Decoder<UInt192>({ UInt192(it) })
        }
    }

    public data class UInt200(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 200) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt200> =
                    SolidityBase.UIntBase.Decoder<UInt200>({ UInt200(it) })
        }
    }

    public data class UInt208(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 208) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt208> =
                    SolidityBase.UIntBase.Decoder<UInt208>({ UInt208(it) })
        }
    }

    public data class UInt216(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 216) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt216> =
                    SolidityBase.UIntBase.Decoder<UInt216>({ UInt216(it) })
        }
    }

    public data class UInt224(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 224) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt224> =
                    SolidityBase.UIntBase.Decoder<UInt224>({ UInt224(it) })
        }
    }

    public data class UInt232(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 232) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt232> =
                    SolidityBase.UIntBase.Decoder<UInt232>({ UInt232(it) })
        }
    }

    public data class UInt240(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 240) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt240> =
                    SolidityBase.UIntBase.Decoder<UInt240>({ UInt240(it) })
        }
    }

    public data class UInt248(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 248) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt248> =
                    SolidityBase.UIntBase.Decoder<UInt248>({ UInt248(it) })
        }
    }

    public data class UInt256(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 256) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<UInt256> =
                    SolidityBase.UIntBase.Decoder<UInt256>({ UInt256(it) })
        }
    }

    public data class Int8(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 8) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int8> =
                    SolidityBase.IntBase.Decoder<Int8>({ Int8(it) })
        }
    }

    public data class Int16(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 16) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int16> =
                    SolidityBase.IntBase.Decoder<Int16>({ Int16(it) })
        }
    }

    public data class Int24(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 24) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int24> =
                    SolidityBase.IntBase.Decoder<Int24>({ Int24(it) })
        }
    }

    public data class Int32(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 32) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int32> =
                    SolidityBase.IntBase.Decoder<Int32>({ Int32(it) })
        }
    }

    public data class Int40(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 40) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int40> =
                    SolidityBase.IntBase.Decoder<Int40>({ Int40(it) })
        }
    }

    public data class Int48(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 48) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int48> =
                    SolidityBase.IntBase.Decoder<Int48>({ Int48(it) })
        }
    }

    public data class Int56(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 56) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int56> =
                    SolidityBase.IntBase.Decoder<Int56>({ Int56(it) })
        }
    }

    public data class Int64(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 64) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int64> =
                    SolidityBase.IntBase.Decoder<Int64>({ Int64(it) })
        }
    }

    public data class Int72(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 72) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int72> =
                    SolidityBase.IntBase.Decoder<Int72>({ Int72(it) })
        }
    }

    public data class Int80(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 80) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int80> =
                    SolidityBase.IntBase.Decoder<Int80>({ Int80(it) })
        }
    }

    public data class Int88(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 88) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int88> =
                    SolidityBase.IntBase.Decoder<Int88>({ Int88(it) })
        }
    }

    public data class Int96(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 96) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int96> =
                    SolidityBase.IntBase.Decoder<Int96>({ Int96(it) })
        }
    }

    public data class Int104(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 104) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int104> =
                    SolidityBase.IntBase.Decoder<Int104>({ Int104(it) })
        }
    }

    public data class Int112(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 112) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int112> =
                    SolidityBase.IntBase.Decoder<Int112>({ Int112(it) })
        }
    }

    public data class Int120(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 120) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int120> =
                    SolidityBase.IntBase.Decoder<Int120>({ Int120(it) })
        }
    }

    public data class Int128(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 128) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int128> =
                    SolidityBase.IntBase.Decoder<Int128>({ Int128(it) })
        }
    }

    public data class Int136(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 136) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int136> =
                    SolidityBase.IntBase.Decoder<Int136>({ Int136(it) })
        }
    }

    public data class Int144(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 144) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int144> =
                    SolidityBase.IntBase.Decoder<Int144>({ Int144(it) })
        }
    }

    public data class Int152(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 152) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int152> =
                    SolidityBase.IntBase.Decoder<Int152>({ Int152(it) })
        }
    }

    public data class Int160(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 160) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int160> =
                    SolidityBase.IntBase.Decoder<Int160>({ Int160(it) })
        }
    }

    public data class Int168(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 168) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int168> =
                    SolidityBase.IntBase.Decoder<Int168>({ Int168(it) })
        }
    }

    public data class Int176(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 176) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int176> =
                    SolidityBase.IntBase.Decoder<Int176>({ Int176(it) })
        }
    }

    public data class Int184(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 184) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int184> =
                    SolidityBase.IntBase.Decoder<Int184>({ Int184(it) })
        }
    }

    public data class Int192(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 192) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int192> =
                    SolidityBase.IntBase.Decoder<Int192>({ Int192(it) })
        }
    }

    public data class Int200(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 200) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int200> =
                    SolidityBase.IntBase.Decoder<Int200>({ Int200(it) })
        }
    }

    public data class Int208(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 208) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int208> =
                    SolidityBase.IntBase.Decoder<Int208>({ Int208(it) })
        }
    }

    public data class Int216(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 216) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int216> =
                    SolidityBase.IntBase.Decoder<Int216>({ Int216(it) })
        }
    }

    public data class Int224(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 224) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int224> =
                    SolidityBase.IntBase.Decoder<Int224>({ Int224(it) })
        }
    }

    public data class Int232(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 232) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int232> =
                    SolidityBase.IntBase.Decoder<Int232>({ Int232(it) })
        }
    }

    public data class Int240(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 240) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int240> =
                    SolidityBase.IntBase.Decoder<Int240>({ Int240(it) })
        }
    }

    public data class Int248(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 248) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int248> =
                    SolidityBase.IntBase.Decoder<Int248>({ Int248(it) })
        }
    }

    public data class Int256(
        public val `value`: BigInteger,
    ) : SolidityBase.IntBase(value, 256) {
        public companion object {
            public val DECODER: SolidityBase.IntBase.Decoder<Int256> =
                    SolidityBase.IntBase.Decoder<Int256>({ Int256(it) })
        }
    }

    public class Bytes1(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 1) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes1> =
                    SolidityBase.StaticBytes.Decoder<Bytes1>({ Bytes1(it) }, 1)
        }
    }

    public class Bytes2(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 2) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes2> =
                    SolidityBase.StaticBytes.Decoder<Bytes2>({ Bytes2(it) }, 2)
        }
    }

    public class Bytes3(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 3) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes3> =
                    SolidityBase.StaticBytes.Decoder<Bytes3>({ Bytes3(it) }, 3)
        }
    }

    public class Bytes4(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 4) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes4> =
                    SolidityBase.StaticBytes.Decoder<Bytes4>({ Bytes4(it) }, 4)
        }
    }

    public class Bytes5(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 5) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes5> =
                    SolidityBase.StaticBytes.Decoder<Bytes5>({ Bytes5(it) }, 5)
        }
    }

    public class Bytes6(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 6) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes6> =
                    SolidityBase.StaticBytes.Decoder<Bytes6>({ Bytes6(it) }, 6)
        }
    }

    public class Bytes7(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 7) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes7> =
                    SolidityBase.StaticBytes.Decoder<Bytes7>({ Bytes7(it) }, 7)
        }
    }

    public class Bytes8(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 8) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes8> =
                    SolidityBase.StaticBytes.Decoder<Bytes8>({ Bytes8(it) }, 8)
        }
    }

    public class Bytes9(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 9) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes9> =
                    SolidityBase.StaticBytes.Decoder<Bytes9>({ Bytes9(it) }, 9)
        }
    }

    public class Bytes10(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 10) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes10> =
                    SolidityBase.StaticBytes.Decoder<Bytes10>({ Bytes10(it) }, 10)
        }
    }

    public class Bytes11(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 11) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes11> =
                    SolidityBase.StaticBytes.Decoder<Bytes11>({ Bytes11(it) }, 11)
        }
    }

    public class Bytes12(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 12) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes12> =
                    SolidityBase.StaticBytes.Decoder<Bytes12>({ Bytes12(it) }, 12)
        }
    }

    public class Bytes13(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 13) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes13> =
                    SolidityBase.StaticBytes.Decoder<Bytes13>({ Bytes13(it) }, 13)
        }
    }

    public class Bytes14(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 14) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes14> =
                    SolidityBase.StaticBytes.Decoder<Bytes14>({ Bytes14(it) }, 14)
        }
    }

    public class Bytes15(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 15) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes15> =
                    SolidityBase.StaticBytes.Decoder<Bytes15>({ Bytes15(it) }, 15)
        }
    }

    public class Bytes16(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 16) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes16> =
                    SolidityBase.StaticBytes.Decoder<Bytes16>({ Bytes16(it) }, 16)
        }
    }

    public class Bytes17(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 17) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes17> =
                    SolidityBase.StaticBytes.Decoder<Bytes17>({ Bytes17(it) }, 17)
        }
    }

    public class Bytes18(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 18) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes18> =
                    SolidityBase.StaticBytes.Decoder<Bytes18>({ Bytes18(it) }, 18)
        }
    }

    public class Bytes19(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 19) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes19> =
                    SolidityBase.StaticBytes.Decoder<Bytes19>({ Bytes19(it) }, 19)
        }
    }

    public class Bytes20(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 20) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes20> =
                    SolidityBase.StaticBytes.Decoder<Bytes20>({ Bytes20(it) }, 20)
        }
    }

    public class Bytes21(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 21) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes21> =
                    SolidityBase.StaticBytes.Decoder<Bytes21>({ Bytes21(it) }, 21)
        }
    }

    public class Bytes22(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 22) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes22> =
                    SolidityBase.StaticBytes.Decoder<Bytes22>({ Bytes22(it) }, 22)
        }
    }

    public class Bytes23(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 23) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes23> =
                    SolidityBase.StaticBytes.Decoder<Bytes23>({ Bytes23(it) }, 23)
        }
    }

    public class Bytes24(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 24) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes24> =
                    SolidityBase.StaticBytes.Decoder<Bytes24>({ Bytes24(it) }, 24)
        }
    }

    public class Bytes25(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 25) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes25> =
                    SolidityBase.StaticBytes.Decoder<Bytes25>({ Bytes25(it) }, 25)
        }
    }

    public class Bytes26(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 26) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes26> =
                    SolidityBase.StaticBytes.Decoder<Bytes26>({ Bytes26(it) }, 26)
        }
    }

    public class Bytes27(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 27) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes27> =
                    SolidityBase.StaticBytes.Decoder<Bytes27>({ Bytes27(it) }, 27)
        }
    }

    public class Bytes28(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 28) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes28> =
                    SolidityBase.StaticBytes.Decoder<Bytes28>({ Bytes28(it) }, 28)
        }
    }

    public class Bytes29(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 29) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes29> =
                    SolidityBase.StaticBytes.Decoder<Bytes29>({ Bytes29(it) }, 29)
        }
    }

    public class Bytes30(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 30) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes30> =
                    SolidityBase.StaticBytes.Decoder<Bytes30>({ Bytes30(it) }, 30)
        }
    }

    public class Bytes31(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 31) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes31> =
                    SolidityBase.StaticBytes.Decoder<Bytes31>({ Bytes31(it) }, 31)
        }
    }

    public class Bytes32(
        public val bytes: ByteArray,
    ) : SolidityBase.StaticBytes(bytes, 32) {
        public companion object {
            public val DECODER: SolidityBase.StaticBytes.Decoder<Bytes32> =
                    SolidityBase.StaticBytes.Decoder<Bytes32>({ Bytes32(it) }, 32)
        }
    }

    public data class Address(
        public val `value`: BigInteger,
    ) : SolidityBase.UIntBase(value, 160) {
        public companion object {
            public val DECODER: SolidityBase.UIntBase.Decoder<Address> =
                    SolidityBase.UIntBase.Decoder<Address>({ Address(it) })
        }
    }

    public data class Bool(
        public val `value`: Boolean,
    ) : SolidityBase.UIntBase(if (value) BigInteger.ONE else BigInteger.ZERO, 8) {
        public class Decoder : SolidityBase.TypeDecoder<Bool> {
            override fun isDynamic(): Boolean = false

            override fun decode(source: SolidityBase.PartitionData): Bool = Bool(SolidityBase.decodeBool(source.consume()))
        }

        public companion object {
            public val DECODER: Decoder = Decoder()
        }
    }

    public open class Bytes(
        public val items: ByteArray,
    ) : SolidityBase.DynamicType {
        init {
            if (BigInteger.parseString(items.size.toString(10), 10) > BigInteger.TWO.pow(256)) throw Exception()
        }

        override fun encode(): kotlin.String {
            val parts = encodeParts()
            return parts.static + parts.dynamic
        }

        private fun encodeParts(): SolidityBase.DynamicType.Parts {
            val length = items.size.toString(16).padStart(64, '0')
            val contents = items.toHex().padEndMultiple(64, '0')
            return SolidityBase.DynamicType.Parts(length, contents)
        }

        override fun encodePacked(): kotlin.String = items.toHex()

        public class Decoder : SolidityBase.TypeDecoder<Bytes> {
            override fun isDynamic(): Boolean = true

            override fun decode(source: SolidityBase.PartitionData): Bytes = Bytes(SolidityBase.decodeBytes(source))
        }

        public companion object {
            public val DECODER: Decoder = Decoder()
        }
    }

    public data class String(
        public val `value`: kotlin.String,
    ) : Bytes(value.encodeToByteArray()) {
        public class Decoder : SolidityBase.TypeDecoder<String> {
            override fun isDynamic(): Boolean = true

            override fun decode(source: SolidityBase.PartitionData): String = String(SolidityBase.decodeString(source))
        }

        public companion object {
            public val DECODER: Decoder = Decoder()
        }
    }
}
