package expected

import kotlin.String
import pm.gnosis.model.Solidity
import pm.gnosis.model.SolidityBase

public class Abi6 {
    public object Function {
        public const val METHOD_ID: String = "06da0736"

        public fun encode(owner: Solidity.Address): String = "0x" + METHOD_ID + pm.gnosis.model.SolidityBase.encodeFunctionArguments(owner)

        public fun decodeArguments(`data`: String): Arguments {
            val source = SolidityBase.PartitionData.of(data)

            // Add decoders
            val arg0 = Solidity.Address.DECODER.decode(source)

            return Arguments(arg0)
        }

        public data class Arguments(
            public val owner: Solidity.Address,
        )
    }
}
