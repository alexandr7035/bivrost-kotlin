package expected

import `by`.alexandr7035.bivkmp.model.Solidity
import `by`.alexandr7035.bivkmp.model.SolidityBase
import com.ionspin.kotlin.bignum.integer.BigInteger
import expected.arrays.Array5
import expected.arrays.Array7
import kotlin.Boolean
import kotlin.String

public class Abi9 {
    public object Owners {
        public const val METHOD_ID: String = "9f767eb7"

        public fun encode(c: SolidityBase.Vector<TupleA>, arg2: SolidityBase.Vector<SolidityBase.Vector<Array7<Array5<Solidity.UInt256>>>>): String = "0x" + METHOD_ID + by.alexandr7035.bivkmp.model.SolidityBase.encodeFunctionArguments(c, arg2)

        public fun decode(`data`: String): Return {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0 = TupleB.DECODER.decode(source)
            val arg1Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
            val arg1 = SolidityBase.Vector.Decoder(TupleB.DECODER).decode(source.subData(arg1Offset))

            return Return(arg0, arg1)
        }

        public fun decodeArguments(`data`: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
            val arg0 = SolidityBase.Vector.Decoder(TupleA.DECODER).decode(source.subData(arg0Offset))
            val arg1Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
            val arg1 = SolidityBase.Vector.Decoder(SolidityBase.Vector.Decoder(Array7.Decoder(Array5.Decoder(Solidity.UInt256.DECODER)))).decode(source.subData(arg1Offset))

            return Arguments(arg0, arg1)
        }

        public data class Return(
            public val param0: TupleB,
            public val param1: SolidityBase.Vector<TupleB>,
        )

        public data class Arguments(
            public val c: SolidityBase.Vector<TupleA>,
            public val param1:
                    SolidityBase.Vector<SolidityBase.Vector<Array7<Array5<Solidity.UInt256>>>>,
        )
    }

    public data class TupleB(
        public val x: Solidity.UInt256,
        public val y: Solidity.UInt256,
    ) : SolidityBase.StaticType {
        override fun encode(): String = SolidityBase.encodeFunctionArguments(x, y)

        override fun encodePacked(): String = throw UnsupportedOperationException("Structs are  not supported via encodePacked")

        public class Decoder : SolidityBase.TypeDecoder<TupleB> {
            override fun isDynamic(): Boolean = false

            override fun decode(source: SolidityBase.PartitionData): TupleB {
                val arg0 = Solidity.UInt256.DECODER.decode(source)
                val arg1 = Solidity.UInt256.DECODER.decode(source)
                return TupleB(arg0, arg1)
            }
        }

        public companion object {
            public val DECODER: Decoder = Decoder()
        }
    }

    public data class TupleA(
        public val a: Solidity.UInt256,
        public val b: Solidity.UInt256,
        public val param2:
                SolidityBase.Vector<SolidityBase.Vector<Array7<Array5<Solidity.UInt256>>>>,
    ) : SolidityBase.DynamicType {
        override fun encode(): String = SolidityBase.encodeFunctionArguments(a, b, param2)

        override fun encodePacked(): String = throw UnsupportedOperationException("Structs are  not supported via encodePacked")

        public class Decoder : SolidityBase.TypeDecoder<TupleA> {
            override fun isDynamic(): Boolean = true

            override fun decode(source: SolidityBase.PartitionData): TupleA {
                val arg0 = Solidity.UInt256.DECODER.decode(source)
                val arg1 = Solidity.UInt256.DECODER.decode(source)
                val arg2Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
                val arg2 = SolidityBase.Vector.Decoder(SolidityBase.Vector.Decoder(Array7.Decoder(Array5.Decoder(Solidity.UInt256.DECODER)))).decode(source.subData(arg2Offset))
                return TupleA(arg0, arg1, arg2)
            }
        }

        public companion object {
            public val DECODER: Decoder = Decoder()
        }
    }
}
