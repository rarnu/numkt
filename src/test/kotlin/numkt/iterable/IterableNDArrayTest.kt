package numkt.iterable

import com.rarnu.numkt.api.*
import com.rarnu.numkt.ndarray.data.set
import com.rarnu.numkt.ndarray.operations.*
import org.junit.Assert.*
import org.junit.Test

class IterableNDArrayTest {

    @Test
    fun testOfFunctionAssociate() {
        val charCodesNDArray = Numkt.ndarray(Numkt[72, 69, 76, 76, 79])

        val actual = mutableMapOf<Int, Char>()
        charCodesNDArray.associateTo(actual) { it to it.toChar() }
        val expected = mapOf(72 to 'H', 69 to 'E', 76 to 'L', 79 to 'O')
        assertEquals(expected, actual)
    }

    @Test
    fun testOfFunctionAssociateBy() {
        val charCodesNDArray = Numkt.ndarray(Numkt[72, 69, 76, 76, 79])

        val actual = mutableMapOf<Char, Int>()
        charCodesNDArray.associateByTo(actual) { it.toChar() }
        val expected = mapOf('H' to 72, 'E' to 69, 'L' to 76, 'O' to 79)
        assertEquals(expected, actual)
    }

    @Test
    fun testOfFunctionAssociateByWithTransform() {
        val charCodesNDArray = Numkt.ndarray(Numkt[65, 65, 66, 67, 68, 69])

        val actual = mutableMapOf<Char, Char>()
        charCodesNDArray.associateByTo(actual, { it.toChar() }, { (it + 32).toChar() })
        val expected = mapOf('A' to 'a', 'B' to 'b', 'C' to 'c', 'D' to 'd', 'E' to 'e')
        assertEquals(expected, actual)
    }

    @Test
    fun testOfFunctionAssociateWith() {
        val numbers = Numkt.ndarray(Numkt[1, 2, 3, 4])

        val actual = mutableMapOf<Int, Int>()
        numbers.associateWithTo(actual) { it * it }
        val expected = mapOf(1 to 1, 2 to 4, 3 to 9, 4 to 16)
        assertEquals(expected, actual)
    }

    @Test
    fun testOfFunctionAverage() {
        val array = intArrayOf(12, 49, 23, 4, 35, 60, 33)

        val ndarray = Numkt.ndarray(array)

        val actual = ndarray.average()
        val expected = array.average()
        assertEquals(expected, actual, 0.01)
    }

    @Test
    fun testOfFunctionChunked() {
        val a = Numkt.ndarray(Numkt[1, 2, 3, 4, 5, 6, 7, 8, 9, 10])

        val actual = a.chunked(3)
        val expected = Numkt.ndarray(Numkt[Numkt[1, 2, 3], Numkt[4, 5, 6], Numkt[7, 8, 9], Numkt[10, 0, 0]])
        assertEquals(expected, actual)
    }

    @Test
    fun testOfFunctionContains() {
        val ndarray = Numkt.d2array(5, 5) { it - 3f }
        assertTrue(-1f in ndarray)
        assertFalse(25f in ndarray)
    }

    @Test
    fun testOfFunctionCount() {
        val ndarray = Numkt.ndarray(Numkt[1, 1, 2, 3, 4, 5, 2, 10])
        assertEquals(1, ndarray.count { it == 3 })
        assertEquals(4, ndarray.count { it % 2 == 0 })
    }

    @Test
    fun testDistinct() {
        val data = Numkt.ndarrayOf(1, 2, 3, 1, 2, 3)
        assertEquals(Numkt.ndarrayOf(1, 2, 3), data.distinct())
    }

    @Test
    fun testDistinctBy() {
        val data = Numkt.ndarrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0).distinctBy {
            if (it <= 3.0)
                it * it
            else {
                it
            }
        }
        assertEquals(Numkt.ndarrayOf(1.0, 2.0, 3.0, 5.0, 6.0), data)
    }

    @Test
    fun testDrop() {
        val data = Numkt.arange<Float>(10)
        assertEquals(Numkt.arange<Float>(start = 5, stop = 10), data.drop(5))
        assertEquals(Numkt.arange<Float>(start = 0, 8), data.drop(-2))
    }

    @Test
    fun testDropWhile() {
        val data = Numkt.arange<Long>(50)
        assertEquals(Numkt.arange<Long>(45, 50, 1), data.dropWhile { it < 45 })
    }

    @Test
    fun testFilter() {
        val data = Numkt.arange<Int>(10, 30, 1)
        val actual = data.filter { it in 23..27 }
        assertEquals(Numkt.arange<Int>(23, 28, 1), actual)
    }

    @Test
    fun testFilterIndexed() {
        val data = Numkt.arange<Float>(10)
        data[0] = 10f
        assertEquals(Numkt.arange<Float>(6, 10, 1), data.filterIndexed { index, fl -> (index != 0) && (fl > 5) })
    }

    @Test
    fun testFilterNot() {
        val list = listOf(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29)
        val data = Numkt.ndarray(list)
        val actual = data.filterNot { it in 23..27 }
        val expectedList = list.filterNot { it in 23..27 }
        assertEquals(Numkt.ndarray(expectedList), actual)
    }

    @Test
    fun testFind() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        val ndarray = Numkt.ndarray(list)
        assertEquals(list.find { it % 2 != 0 }, ndarray.find { it % 2 != 0 })
        assertEquals(list.findLast { it % 2 == 0 }, ndarray.findLast { it % 2 == 0 })
    }

    @Test
    fun testFirstAndFirstOrNullWithPredicate() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        val ndarray = Numkt.ndarray(list)
        println(list.first { it % 2 != 0 })
        assertEquals(list.first { it % 2 != 0 }, ndarray.first { it % 2 != 0 })
        assertEquals(list.firstOrNull { it % 10 == 0 }, ndarray.firstOrNull { it % 10 == 0 })
    }

    @Test
    fun testFlatMap() {
        val list = listOf(0, 1, 2, 3)
        val ndarray = Numkt.ndarray(list, 2, 2)
        assertEquals(
            Numkt.ndarray(list.flatMap { listOf(it, it + 1, it + 2) }),
            ndarray.flatMap { listOf(it, it + 1, it + 2) })
    }

    @Test
    fun testFlatMapIndexed() {
        val list = listOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(list)
        assertEquals(
            Numkt.ndarray(list.flatMapIndexed { i, e -> listOf(e, i) }),
            ndarray.flatMapIndexed { i: Int, e -> listOf(e, i) })
    }

    @Test
    fun testFold() {
        val list = listOf(1, 2, 3, 4)
        val ndarray = Numkt.ndarray(list)
        assertEquals(list.fold(3, Int::times), ndarray.fold(3, Int::times))
    }

    @Test
    fun testFoldIndexed() {
        val list = listOf(1, 2, 3, 4, 5)
        val ndarray = Numkt.ndarray(list)
        val actual = ndarray.foldIndexed(Pair(1, 1)) { index, acc: Pair<Int, Int>, i: Int ->
            Pair(acc.first + index, acc.second * i)
        }
        val expected = list.foldIndexed(Pair(1, 1)) { index, acc: Pair<Int, Int>, i: Int ->
            Pair(acc.first + index, acc.second * i)
        }
        assertEquals(expected, actual)
    }

    @Test
    fun testGroupNDArrayBy() {
        val data = Numkt.d3array(2, 2, 2) { it }
        val expected1 = mapOf(0 to Numkt.ndarrayOf(0, 2, 4, 6), 1 to Numkt.ndarrayOf(1, 3, 5, 7))
        assertEquals(expected1, data.groupNDArrayBy { it % 2 })

        val expected2 = mapOf(0 to Numkt.ndarrayOf(0f, 2f, 4f, 6f), 1 to Numkt.ndarrayOf(1f, 3f, 5f, 7f))
        assertEquals(expected2, data.groupNDArrayBy({ it % 2 }, { it.toFloat() }))
    }

    @Test
    fun testIntersect() {
        val list = listOf(1, 3, 4, 5, 6, 10)
        val ndarray = Numkt.ndarray(list)
        val list2 = listOf(2, 3, 5, 7, 6, 11)
        val expected = list intersect list2
        val actual = ndarray intersect list2
        assertEquals(expected, actual)
    }

    @Test
    fun testLast() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[2, 3, -17], Numkt[10, 23, 33]])
        assertEquals(33, ndarray.last())
    }

    @Test
    fun testLastWithPredicate() {
        val list = listOf(1, 2, 3, -12, 42, 33, 89)
        val ndarray = Numkt.ndarray(list)
        println(list.last { it % 2 == 0 })
        println(ndarray.last { it % 2 == 0 })
    }

    @Test
    fun testMapForScalarNdarray() {
        val a = Numkt.ndarray(Numkt[Numkt[Numkt[3.2]]])
        assertEquals(Numkt.ndarray(Numkt[Numkt[Numkt[3]]]), a.map { it.toInt() })
    }

    @Test
    fun testMap() {
        val data = Numkt.ndarrayOf(1, 2, 3, 4)
        assertEquals(Numkt.ndarrayOf(1, 4, 9, 16), data.map { it * it })
    }

    @Test
    fun testMapIndexed() {
        val data = Numkt.ndarrayOf(1, 2, 3, 4)
        assertEquals(Numkt.ndarrayOf(0, 2, 6, 12), data.mapIndexed { idx: Int, value -> value * idx })
        val ndarray = Numkt.ndarrayOf(1, 2, 3, 4).reshape(2, 2)
        ndarray.mapMultiIndexed { idx: IntArray, value -> value * (idx[0] xor idx[1]) }
    }

    @Test
    fun testMax() {
        val array = intArrayOf(1, -2, 10, 23, 3, 10, 32, -1, 17)
        val ndarray = Numkt.ndarray(array)
        assertEquals(array.maxOrNull(), ndarray.max())
    }

    @Test
    fun testMaxBy() {
        val array = intArrayOf(1, -2, 10, 23, 3, 10, 32, -1, 17)
        val ndarray = Numkt.ndarray(array)
        assertEquals(array.maxByOrNull { -it }, ndarray.maxBy { -it })
    }

    @Test
    fun testMin() {
        val array = intArrayOf(1, -2, 10, 23, 3, 10, 32, -1, 17)
        val ndarray = Numkt.ndarray(array)
        assertEquals(array.minOrNull(), ndarray.min())

    }

    @Test
    fun testMinBy() {
        val array = intArrayOf(1, -2, 10, 23, 3, 10, 32, -1, 17)
        val ndarray = Numkt.ndarray(array)
        assertEquals(array.minByOrNull { -it }, ndarray.minBy { -it })
    }

    @Test
    fun testPartition() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        val ndarray = Numkt.ndarray(list)
        val (h, t) = ndarray.partition { it % 2 == 0 }
        val (lH, lT) = list.partition { it % 2 == 0 }
        assertEquals(Numkt.ndarray(lH), h)
        assertEquals(Numkt.ndarray(lT), t)
    }

    @Test
    fun testSort() {
        val intArray = intArrayOf(42, 42, 23, 1, 23, 4, 10, 14, 3, 7, 25, 16, 2, 1, 37)
        val ndarray = Numkt.ndarray(intArray, 3, 5)
        val sortedNDArray = ndarray.sorted()
        sortedNDArray[2, 2] = 1000

    }

    @Test
    fun testReduce() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        val ndarray = Numkt.ndarray(list)
        val expected = list.reduce { acc, i -> acc + i / 2 }
        val actual = ndarray.reduce { acc, i -> acc + i / 2 }
        assertEquals(expected, actual)
    }

    @Test
    fun testReversed() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        val ndarray = Numkt.ndarray(list, 2, 4)
        val expected = Numkt.ndarray(list.reversed(), 2, 4)
        assertEquals(expected, ndarray.reversed())
    }

    @Test
    fun testScan() {
        val ndarray = Numkt.ndarray(Numkt[1, 2, 3, 4, 5, 6])
        println(ndarray.scan(10) { acc: Int, i: Int -> acc + i })
    }

    @Test
    fun testMinimum() {
        val ndarray1 = Numkt.ndarray(Numkt[2, 3, 4])
        val ndarray2 = Numkt.ndarray(Numkt[1, 5, 2])
        assertEquals(Numkt.ndarray(Numkt[1, 3, 2]), ndarray1.minimum(ndarray2))
    }

    @Test
    fun testMaximum() {
        val ndarray1 = Numkt.ndarray(Numkt[2, 3, 4])
        val ndarray2 = Numkt.ndarray(Numkt[1, 5, 2])
        assertEquals(Numkt.ndarray(Numkt[2, 5, 4]), ndarray1.maximum(ndarray2))
    }
}
