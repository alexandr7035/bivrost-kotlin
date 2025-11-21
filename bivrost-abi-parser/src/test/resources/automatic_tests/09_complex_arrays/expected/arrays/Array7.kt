package expected.arrays

import kotlin.Boolean
import kotlin.collections.List
import pm.gnosis.model.SolidityBase

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
