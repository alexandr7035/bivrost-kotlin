package expected

import `by`.alexandr7035.bivkmp.model.Solidity
import `by`.alexandr7035.bivkmp.model.SolidityBase
import kotlin.String
import kotlin.collections.List

public class Abi15 {
    public object Events {
        public object Executed {
            public const val EVENT_ID: String =
                    "d3ecebd73eb8f84cd179cd91fe1e27c1a112b3c76ca5ceaca1661a808d6081aa"

            public fun decode(topics: List<String>): Arguments {
                // Decode topics
                val source1 = SolidityBase.PartitionData.of(topics[1])
                val t1 = Solidity.UInt256.DECODER.decode(source1)
                return Arguments(t1)
            }

            public data class Arguments(
                public val transactionid: Solidity.UInt256,
            )
        }
    }
}
