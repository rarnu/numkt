package numkt.ndarray.operation

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.ndarray.operations.clip
import com.rarnu.numkt.ndarray.operations.toList
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class TestTransformation {

    @Test
    fun clip1DIntDataType() {
        val a = Numkt.ndarray(Numkt[1, 2, 3, 4, 5])
        val result = a.clip(2, 4)
        assertArrayEquals(listOf(2, 2, 3, 4, 4).toTypedArray(), result.toList().toTypedArray())
    }

    @Test
    fun clip1DShortDataType() {
        val a = Numkt.ndarray<Short>(Numkt[1, 2, 3, 4, 5])
        val result = a.clip(2, 4)
        assertArrayEquals(listOf<Short>(2, 2, 3, 4, 4).toTypedArray(), result.toList().toTypedArray())
    }

    @Test
    fun clip1DLongDataType() {
        val a = Numkt.ndarray<Long>(Numkt[1, 2, 3, 4, 5])
        val result = a.clip(2, 4)
        assertArrayEquals(listOf<Long>(2, 2, 3, 4, 4).toTypedArray(), result.toList().toTypedArray())
    }

    @Test
    fun clip2dFloatDataType() {
        val absoluteTolerance = 0.01f
        val a = Numkt.ndarray(Numkt[Numkt[1f, 2f, 3f, 4f, 5f], Numkt[6f, 7f, 8f, 9f, 10f]])
        val min = 3.5f
        val max = 7.1f
        val expected = listOf(3.5f, 3.5f, 3.5f, 4f, 5f, 6f, 7f, 7.1f, 7.1f, 7.1f)
        val result = a.clip(min, max).toList()
        for (i in expected.indices) { // run assert with absolute tolerance because of floating number is not stable when we test for js
            assertEquals(expected[i], result[i], absoluteTolerance)
        }
    }

    @Test
    fun clip3dByteDataTypeAndMinIsMax() {
        val inputArray = ByteArray(60) { it.toByte() }
        val a = Numkt.ndarray(inputArray, 2, 5, 6)
        val min = 1.toByte()
        val expected = ByteArray(60) { min }
        assertArrayEquals(expected.toList().toTypedArray(), a.clip(min, min).toList().toTypedArray())
    }

    @Test
    fun clip4dDoubleDataType() {
        val inputArray = DoubleArray(60) { Random.nextDouble() }
        val min = 7.0
        val max = 42.0
        val expected = inputArray.copyOf().map { if (it < min) min else if (it > max) max else it }
        val a = Numkt.ndarray(inputArray, 2, 5, 3, 2)
        assertArrayEquals(expected.toList().toTypedArray(), a.clip(min, max).toList().toTypedArray())
    }
}
