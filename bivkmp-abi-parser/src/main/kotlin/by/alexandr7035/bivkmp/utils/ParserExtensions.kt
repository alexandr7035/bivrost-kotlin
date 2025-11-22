package by.alexandr7035.bivkmp.utils

import org.kotlincrypto.hash.sha3.Keccak256
import kotlin.experimental.and

fun String.generateSolidityMethodId() = keccak256().substring(0..7)

fun String.keccak256(): String {
    val digest = Keccak256()
    digest.update(this.toByteArray())
    val hash = digest.digest()
    val buff = StringBuffer()
    for (b in hash) {
        buff.append(String.format("%02x", b and 0xFF.toByte()))
    }
    return buff.toString()
}
