package expected

import kotlin.String

public class Abi2 {
    public object Function {
        public const val METHOD_ID: String = "9d96e2df"

        public fun encode(): String = "0x" + METHOD_ID
    }
}
