package sample.real

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.ndarray.operations.filter
import com.rarnu.numkt.ndarray.operations.map
import com.rarnu.numkt.ndarray.operations.reduce
import org.junit.Assert.assertEquals
import org.junit.Test

class TestStdOperations {

    @Test
    fun smallExampleCollectionOperations() {
        val a = Numkt.ndarray(Numkt[1, 2, 3, 4, 5])
        val b = a.filter { it > 2 }
        val c = a.map { it * 2 }
        val d = a.reduce { acc, value -> acc + value }

        assertEquals(Numkt.ndarray(Numkt[3, 4, 5]), b)
        assertEquals(Numkt.ndarray(Numkt[2, 4, 6, 8, 10]), c)
        assertEquals(15, d)
    }
}