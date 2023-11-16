package numkt_kotlin.statistics

import com.rarnu.numkt.api.arange
import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.kotlin.stat.KEStatistics
import kotlin.test.Test
import kotlin.test.assertEquals

class KEStatisticsTest {

    @Test
    fun test_median() {
        val a = Numkt.ndarray(Numkt[Numkt[10, 7, 4], Numkt[3, 2, 1]])
        assertEquals(3.5, Numkt.stat.median(a))
    }

    @Test
    fun test_simple_average() {
        val a = Numkt.arange<Long>(1, 11, 1)
        assertEquals(Numkt.stat.mean(a), Numkt.stat.average(a))
    }

    @Test
    fun test_average_with_weights() {
        val a = Numkt.arange<Long>(1, 11, 1)
        val weights = Numkt.arange<Long>(10, 0, -1)
        assertEquals(4.0, Numkt.stat.average(a, weights))
    }

    @Test
    fun test_of_mean_function_on_a_3d_ndarray() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[Numkt[0, 3], Numkt[1, 4]], Numkt[Numkt[2, 5], Numkt[6, 8]], Numkt[Numkt[7, 9], Numkt[10, 11]]])

        val expectedWith0Axis = Numkt.ndarray(Numkt[Numkt[3.0, 5.666666666666667], Numkt[5.666666666666667, 7.666666666666667]])
        val expectedWith1Axis = Numkt.ndarray(Numkt[Numkt[0.5, 3.5], Numkt[4.0, 6.5], Numkt[8.5, 10.0]])
        val expectedWith2Axis = Numkt.ndarray(Numkt[Numkt[1.5, 2.5], Numkt[3.5, 7.0], Numkt[8.0, 10.5]])

        assertEquals(expectedWith0Axis, Numkt.stat.mean(ndarray, axis = 0))
        assertEquals(expectedWith1Axis, Numkt.stat.mean(ndarray, axis = 1))
        assertEquals(expectedWith2Axis, Numkt.stat.mean(ndarray, axis = 2))
    }

    @Test
    fun test_of_mean_function_on_a_flat_ndarray() {
        val ndarray = Numkt.ndarray(Numkt[1, 2, 3, 4])
        assertEquals(2.5, KEStatistics.mean(ndarray))
    }

    @Test
    fun test_of_mean_function_on_a_2d_ndarray() {
        val ndarray = Numkt.ndarray(Numkt[Numkt[1, 2], Numkt[3, 4]])
        assertEquals(2.5, KEStatistics.mean(ndarray))
    }
}