package io.swisseth.solidity


import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertFailsWith
import kotlin.test.assertContentEquals
import io.swisseth.solidity.model.AbiRoot
import io.swisseth.solidity.model.ParameterJson
import io.swisseth.solidity.model.Solidity
import io.swisseth.solidity.model.SolidityBase
import com.ionspin.kotlin.bignum.integer.BigInteger
import kotlin.reflect.KClass


class AbiParserTest {

    private fun testContext() = AbiParser.GeneratorContext(AbiRoot(ArrayList(), "Test"), AbiParser.ArraysMap("com.example"))

    private fun testParameter(type: String, name: String = "test", components: List<ParameterJson>? = null)
            = ParameterJson(name, type, components)

    private fun assertType(instance: Any, type: KClass<*>) {
        assertTrue(type.isInstance(instance), "$instance should be a $type")
    }

    @Test
    fun testInvalidArrayDefOpeningBracketStart() {
        assertFailsWith<IllegalArgumentException> {
            mapType(testParameter("uint[[5][]"), testContext())
        }
    }

    @Test
    fun testInvalidArrayDefOpeningBracketMiddle() {
        assertFailsWith<IllegalArgumentException> {
            mapType(testParameter("uint[5][[]"), testContext())
        }
    }

    @Test
    fun testInvalidArrayDefOpeningBracketEnd() {
        assertFailsWith<IllegalArgumentException> {
            mapType(testParameter("uint[5][]["), testContext())
        }
    }

    @Test
    fun testInvalidArrayDefLetterAsSize() {
        assertFailsWith<IllegalArgumentException> {
            mapType(testParameter("uint[a][]"), testContext())
        }
    }

    @Test
    fun testInvalidArrayDefClosingBracket() {
        assertFailsWith<IllegalArgumentException> {
            mapType(testParameter("uint[5][]]"), testContext())
        }
    }

    @Test
    fun testInvalidArrayDefUnknownType() {
        assertFailsWith<IllegalArgumentException> {
            mapType(testParameter("gnosis[1][]"), testContext())
        }
    }

    @Test
    fun testSimpleTypeHolder() {
        assertEquals(null, SimpleTypeHolder.forType("unknown"), "Unknown type should return null")
        assertEquals(null, SimpleTypeHolder.forType("tuple"), "Tuple type should return null")

        val bytes32 = SimpleTypeHolder.forType("bytes32")!!
        assertFalse(bytes32.isDynamic(), "bytes32 should be static")

        val uint = SimpleTypeHolder.forType("uint")!!
        assertFalse(uint.isDynamic(), "uint should be static")

        val int = SimpleTypeHolder.forType("int")!!
        assertFalse(int.isDynamic(), "int should be static")

        val bytes = SimpleTypeHolder.forType("bytes")!!
        assertTrue(bytes.isDynamic(), "bytes should be dynamic")

        val string = SimpleTypeHolder.forType("string")!!
        assertTrue(string.isDynamic(), "string should be dynamic")
    }

    @Test
    fun testParseAliasTypes() {
        val uintType = mapType(testParameter("uint"), testContext())
        assertType(uintType, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), uintType.toTypeName())

        val intType = mapType(testParameter("int"), testContext())
        assertType(intType, SimpleTypeHolder::class)
        assertEquals(Solidity.Int256::class.asClassName(), intType.toTypeName())

        val byteType = mapType(testParameter("byte"), testContext())
        assertType(byteType, SimpleTypeHolder::class)
        assertEquals(Solidity.Bytes1::class.asClassName(), byteType.toTypeName())
    }

    @Test
    fun testParseDynamicTupleType() {
        val components = listOf(testParameter("uint", "a"), testParameter("uint[]", "b"))
        val tupleType = mapType(testParameter("tuple", components = components), testContext())
        assertType(tupleType, TupleTypeHolder::class)
        val pTuple = tupleType as TupleTypeHolder
        assertEquals(2, pTuple.entries.size)
        assertEquals("a", pTuple.entries[0].first)
        assertType(pTuple.entries[0].second, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pTuple.entries[0].second.toTypeName())

        assertEquals("b", pTuple.entries[1].first)
        assertType(pTuple.entries[1].second, VectorTypeHolder::class)
        val pArray = pTuple.entries[1].second as VectorTypeHolder
        assertType(pArray.itemType, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pArray.itemType.toTypeName())

        assertEquals(true, tupleType.isDynamic())
        assertEquals("TupleA", tupleType.name)
        assertEquals(ClassName("", "TupleA"), tupleType.toTypeName())
    }

    @Test
    fun testStaticDynamicTupleType() {
        val components = listOf(testParameter("uint", "a"), testParameter("uint[5]", "b"))
        val tupleType = mapType(testParameter("tuple", components = components), testContext())
        assertType(tupleType, TupleTypeHolder::class)
        val pTuple = tupleType as TupleTypeHolder
        assertEquals(2, pTuple.entries.size)
        assertEquals("a", pTuple.entries[0].first)
        assertType(pTuple.entries[0].second, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pTuple.entries[0].second.toTypeName())

        assertEquals("b", pTuple.entries[1].first)
        assertType(pTuple.entries[1].second, ArrayTypeHolder::class)
        val pArray = pTuple.entries[1].second as ArrayTypeHolder
        assertType(pArray.itemType, SimpleTypeHolder::class)
        assertEquals(Solidity.UInt256::class.asClassName(), pArray.itemType.toTypeName())
        assertEquals(5, pArray.capacity)

        assertEquals(false, tupleType.isDynamic())
        assertEquals("TupleA", tupleType.name)
        assertEquals(ClassName("", "TupleA"), tupleType.toTypeName())
    }

    @Test
    fun testParseUIntNestedArray() {
        val type = mapType(testParameter("uint[5][]"), testContext())
        assertType(type, VectorTypeHolder::class)
        val pType = type as VectorTypeHolder
        assertEquals(SolidityBase.Vector::class.asClassName(), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertType(g1Type, ArrayTypeHolder::class)
        val g1pType = g1Type as ArrayTypeHolder
        assertEquals(ClassName("com.example.arrays", "Array5"), g1pType.listType)

        // Second generic type
        val g2Type = g1pType.itemType
        assertEquals(Solidity.UInt256::class.asTypeName(), g2Type.toTypeName())
    }

    @Test
    fun testParseStringDynamicArray() {
        val type = mapType(testParameter("string[]"), testContext())
        assertType(type, VectorTypeHolder::class)
        val pType = type as VectorTypeHolder
        assertEquals(SolidityBase.Vector::class.asClassName(), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.String::class.asTypeName(), g1Type.toTypeName())
    }

    @Test
    fun testParseStringStaticArray() {
        val type = mapType(testParameter("string[5]"), testContext())
        assertType(type, ArrayTypeHolder::class)
        val pType = type as ArrayTypeHolder
        assertEquals(5, pType.capacity)
        assertEquals(ClassName("com.example.arrays", "Array5"), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.String::class.asTypeName(), g1Type.toTypeName())
    }

    @Test
    fun testParseBytesArray() {
        val type = mapType(testParameter("bytes[5]"), testContext())
        assertType(type, ArrayTypeHolder::class)
        val pType = type as ArrayTypeHolder
        assertEquals(5, pType.capacity)
        assertTrue(pType.isDynamic())
        assertEquals(ClassName("com.example.arrays", "Array5"), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.Bytes::class.asTypeName(), g1Type.toTypeName())
        assertTrue(g1Type.isDynamic())
    }

    @Test
    fun testParseBytesXArray() {
        val type = mapType(testParameter("bytes32[5]"), testContext())
        assertType(type, ArrayTypeHolder::class)
        val pType = type as ArrayTypeHolder
        assertEquals(5, pType.capacity)
        assertFalse(pType.isDynamic())
        assertEquals(ClassName("com.example.arrays", "Array5"), pType.listType)

        // First generic type
        val g1Type = pType.itemType
        assertEquals(Solidity.Bytes32::class.asTypeName(), g1Type.toTypeName())
        assertFalse(g1Type.isDynamic())
    }

    @Test
    fun testDecodeFunctionArguments() {
        /*
        f(uint,uint32[],bytes10,bytes)
        with values
        (0x123, [0x456, 0x789], "1234567890", "Hello, world!")
         */
        val testData = SolidityBase.PartitionData.of("0000000000000000000000000000000000000000000000000000000000000123" +
                "0000000000000000000000000000000000000000000000000000000000000080" +
                "3132333435363738393000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000000000000000e0" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000")
        // Decode uint
        assertEquals(
                BigInteger.parseString("123", 16),
                Solidity.UInt256.DECODER.decode(testData).value)

        // Decode uint32[]
        val uint32Offset = BigInteger.parseString(testData.consume(), 16).intValue(exactRequired = true)
        assertEquals(
            listOf(Solidity.UInt32(BigInteger.parseString("456", 16)), Solidity.UInt32(BigInteger.parseString("789", 16))),
            SolidityBase.Vector.Decoder(Solidity.UInt32.DECODER).decode(testData.subData(uint32Offset)).items)

        // Decode bytes10
        assertContentEquals(
                "1234567890".toByteArray(),
                Solidity.Bytes10.DECODER.decode(testData).bytes)

        // Consume location of bytes (we don't need it)
        val bytesOffset = BigInteger.parseString(testData.consume(), 16).intValue(exactRequired = true)
        assertContentEquals(
            "Hello, world!".toByteArray(),
            Solidity.Bytes.DECODER.decode(testData.subData(bytesOffset)).items)
    }

    @Test
    fun testEncodeFunctionArguments() {
        /*
        f(uint,uint32[],bytes10,bytes)
        with values
        (0x123, [0x456, 0x789], "1234567890", "Hello, world!")
         */

        val arg1 = Solidity.UInt256(BigInteger.parseString("123", 16))
        val arg2 = SolidityBase.Vector(
                listOf(Solidity.UInt32(BigInteger.parseString("456", 16)), Solidity.UInt32(BigInteger.parseString("789", 16)))
        )
        val arg3 = Solidity.Bytes10("1234567890".toByteArray())
        val arg4 = Solidity.String("Hello, world!")
        val data = SolidityBase.encodeFunctionArguments(arg1, arg2, arg3, arg4)

        val expected = "" +
                "0000000000000000000000000000000000000000000000000000000000000123" +
                "0000000000000000000000000000000000000000000000000000000000000080" +
                "3132333435363738393000000000000000000000000000000000000000000000" +
                "00000000000000000000000000000000000000000000000000000000000000e0" +
                "0000000000000000000000000000000000000000000000000000000000000002" +
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000"
        assertEquals(expected, data)
    }

    @Test
    fun testEncodeFunctionArgumentsWithStaticArray() {
        /*
        f(uint32[2],bytes,uint32[])
        with values
        ([0x456, 0x789], "Hello, world!", [0x123])
         */

        val arg1 = TestArray(
                listOf(Solidity.UInt32(BigInteger.parseString("456", 16)), Solidity.UInt32(BigInteger.parseString("789", 16))), 2
        )
        val arg2 = Solidity.String("Hello, world!")
        val arg3 = SolidityBase.Vector(
                listOf(Solidity.UInt32(BigInteger.parseString("123", 16)))
        )
        val data = SolidityBase.encodeFunctionArguments(arg1, arg2, arg3)

        val expected = "" +
                // uint32[2]
                "0000000000000000000000000000000000000000000000000000000000000456" +
                "0000000000000000000000000000000000000000000000000000000000000789" +
                // Pointer to bytes
                "0000000000000000000000000000000000000000000000000000000000000080" +
                // Pointer to uint32[]
                "00000000000000000000000000000000000000000000000000000000000000c0" +

                // Dynamic Part
                // bytes -> "Hello world!"
                "000000000000000000000000000000000000000000000000000000000000000d" +
                "48656c6c6f2c20776f726c642100000000000000000000000000000000000000" +
                // uint32[] -> [0x123]
                "0000000000000000000000000000000000000000000000000000000000000001" +
                "0000000000000000000000000000000000000000000000000000000000000123"
        assertEquals(expected, data)
    }

    private class TestArray<out T : SolidityBase.Type>(items: List<T>, capacity: Int) : SolidityBase.Array<T>(items, capacity)
}
