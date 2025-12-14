package by.alexandr7035.bivkmp.solidity.utils

import kotlin.experimental.and

fun String.padStartMultiple(multiple: Int, padChar: Char = ' ') =
    this.padStart(if (this.length % multiple != 0) this.length + multiple - this.length % multiple else 0, padChar)

fun String.padEndMultiple(multiple: Int, padChar: Char = ' ') =
    this.padEnd(if (this.length % multiple != 0) this.length + multiple - this.length % multiple else 0, padChar)


private val hexArray = "0123456789abcdef".toCharArray()

fun ByteArray.toHex(): String {
    val hexChars = CharArray(this.size * 2)
    for (j in this.indices) {
        val v = ((this[j] and 0xFF.toByte()).toInt() + 256) % 256
        hexChars[j * 2] = hexArray[v ushr 4]
        hexChars[j * 2 + 1] = hexArray[v and 0x0F]
    }
    return hexChars.concatToString()
}

fun String.hexToByteArray(): ByteArray {
    val s = this.removePrefix("0x")
    val len = s.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        val high = s[i].digitToInt(16)
        val low = s[i + 1].digitToInt(16)
        data[i / 2] = ((high shl 4) + low).toByte()
        i += 2
    }
    return data
}
