package numkt

import com.rarnu.numkt.ndarray.complex.ComplexDoubleArray
import com.rarnu.numkt.ndarray.complex.ComplexFloatArray
import com.rarnu.numkt.ndarray.complex.joinToString
import org.junit.Assert.assertTrue


infix fun ByteArray.shouldBe(expected: ByteArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this.contentEquals(expected))
}

infix fun ShortArray.shouldBe(expected: ShortArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this.contentEquals(expected))
}

infix fun IntArray.shouldBe(expected: IntArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this.contentEquals(expected))
}

infix fun LongArray.shouldBe(expected: LongArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this.contentEquals(expected))
}

infix fun FloatArray.shouldBe(expected: FloatArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this.contentEquals(expected))
}

infix fun DoubleArray.shouldBe(expected: DoubleArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this.contentEquals(expected))
}

infix fun ComplexFloatArray.shouldBe(expected: ComplexFloatArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this == expected)
}

infix fun ComplexDoubleArray.shouldBe(expected: ComplexDoubleArray) {
    assertTrue("Expected <${expected.joinToString(prefix = "[", postfix = "]")}>, actual <${this.joinToString(prefix = "[", postfix = "]")}>.", this == expected)
}
