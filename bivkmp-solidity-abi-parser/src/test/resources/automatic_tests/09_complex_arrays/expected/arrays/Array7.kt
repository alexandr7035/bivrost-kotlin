package expected.arrays

import `by`.alexandr7035.bivkmp.solidity.model.SolidityBase
import kotlin.Boolean
import kotlin.collections.List

public class Array7<T : SolidityBase.Type>(
    items: List<T>,
) : SolidityBase.Array<T>(items, 7) {
    public class Decoder<T : SolidityBase.Type>(
        public val itemDecoder: SolidityBase.TypeDecoder<T>,
    ) : SolidityBase.TypeDecoder<Array7<T>> {
        override fun isDynamic(): Boolean = itemDecoder.isDynamic()

        override fun decode(source: SolidityBase.PartitionData): Array7<T> = Array7(SolidityBase.decodeList(source, 7, itemDecoder))
    }
}
