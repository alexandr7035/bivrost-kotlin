package expected

import com.ionspin.kotlin.bignum.integer.BigInteger
import io.swisseth.solidity.model.Solidity
import io.swisseth.solidity.model.SolidityBase
import kotlin.Boolean
import kotlin.String

public class Abi16 {
    public object Malformed {
        public const val METHOD_ID: String = "a76411a9"

        public fun encode(c: TupleA): String = "0x" + METHOD_ID + io.swisseth.solidity.model.SolidityBase.encodeFunctionArguments(c)

        public fun decodeArguments(`data`: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
            val arg0 = TupleA.DECODER.decode(source.subData(arg0Offset))

            return Arguments(arg0)
        }

        public data class Arguments(
            public val c: TupleA,
        )
    }

    public data class TupleA(
        public val bytesvar: Solidity.Bytes,
        public val stringvar: Solidity.String,
    ) : SolidityBase.DynamicType {
        override fun encode(): String = SolidityBase.encodeFunctionArguments(bytesvar, stringvar)

        override fun encodePacked(): String = throw UnsupportedOperationException("Structs are not supported via encodePacked")

        public class Decoder : SolidityBase.TypeDecoder<TupleA> {
            override fun isDynamic(): Boolean = true

            override fun decode(source: SolidityBase.PartitionData): TupleA {
                val arg0Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
                val arg0 = Solidity.Bytes.DECODER.decode(source.subData(arg0Offset))
                val arg1Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
                val arg1 = Solidity.String.DECODER.decode(source.subData(arg1Offset))
                return TupleA(arg0, arg1)
            }
        }

        public companion object {
            public val DECODER: Decoder = Decoder()
        }
    }
}
