package expected

import com.ionspin.kotlin.bignum.integer.BigInteger
import io.swisseth.solidity.model.Solidity
import io.swisseth.solidity.model.SolidityBase
import kotlin.String

public class Abi8 {
    public object Function {
        public const val METHOD_ID: String = "06da0736"

        public fun encode(arg1: Solidity.Address): String = "0x" + METHOD_ID + io.swisseth.solidity.model.SolidityBase.encodeFunctionArguments(arg1)

        public fun decode(`data`: String): Return {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0Offset = BigInteger.parseString(source.consume(), 16).intValue(exactRequired = true)
            val arg0 = Solidity.Bytes.DECODER.decode(source.subData(arg0Offset))

            return Return(arg0)
        }

        public fun decodeArguments(`data`: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0 = Solidity.Address.DECODER.decode(source)

            return Arguments(arg0)
        }

        public data class Return(
            public val param0: Solidity.Bytes,
        )

        public data class Arguments(
            public val param0: Solidity.Address,
        )
    }
}
