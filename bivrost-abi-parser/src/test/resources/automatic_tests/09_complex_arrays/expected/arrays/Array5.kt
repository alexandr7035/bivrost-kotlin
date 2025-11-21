package expected.arrays

import kotlin.Boolean
import kotlin.collections.List
import pm.gnosis.model.SolidityBase

public class Array5<T : SolidityBase.Type>(
    items: List<T>,
) : SolidityBase.Array<T>(items, 5) {
    public class Decoder<T : SolidityBase.Type>(
        public val itemDecoder: SolidityBase.TypeDecoder<T>,
    ) : SolidityBase.TypeDecoder<Array5<T>> {
        override fun isDynamic(): Boolean = itemDecoder.isDynamic()

        override fun decode(source: SolidityBase.PartitionData): Array5<T> = Array5(SolidityBase.decodeList(source, 5, itemDecoder))
    }
}
