package expected

import java.math.BigInteger
import kotlin.String
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase
import pm.gnosis.utils.BigIntegerUtils

public class Abi7 {
    public object Function {
        public const val METHOD_ID: String = "06da0736"

        public fun encode(owner: Solidity.Address): String = "0x" + METHOD_ID + pm.gnosis.model.SolidityBase.encodeFunctionArguments(owner)

        public fun decode(`data`: String): Return {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0Offset = BigIntegerUtils.exact(BigInteger(source.consume(), 16))
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
            public val `data`: Solidity.Bytes,
        )

        public data class Arguments(
            public val owner: Solidity.Address,
        )
    }
}
