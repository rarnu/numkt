package com.rarnu.numkt.test.native.math

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.ndarray.data.D2Array
import com.rarnu.numkt.native.libLoader
import com.rarnu.numkt.native.math.NativeMath
import com.rarnu.numkt.native.math.NativeMathEx
import com.rarnu.numkt.test.native.DataStructure
import com.rarnu.numkt.test.native.assertFloatingNDArray
import com.rarnu.numkt.test.native.assertFloatingNumber
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NativeMathTest {

    private lateinit var data: DataStructure
    private lateinit var ndarray: D2Array<Float>

    @BeforeTest
    fun load() {
        libLoader("numkt_jni").load()
        data = DataStructure(42)
        ndarray = data.getFloatM(2)
    }

    @Test
    fun argMaxTest() = assertEquals(3, NativeMath.argMax(ndarray))

    @Test
    fun argMinTest() = assertEquals(0, NativeMath.argMin(ndarray))

    @Test
    fun expTest() {
        val expected = Numkt.ndarray(Numkt[Numkt[1.2539709297612778, 1.499692498450485], Numkt[2.47182503414346, 2.647835581718662]])
        assertFloatingNDArray(expected, NativeMathEx.exp(ndarray))
    }

    @Test
    fun logTest() {
        val expected = Numkt.ndarray(Numkt[Numkt[-1.4858262962985072, -0.9032262301885379], Numkt[-0.0998681176145879, -0.026608338154056003]])
        assertFloatingNDArray(expected, NativeMathEx.log(ndarray))
    }

    @Test
    fun sinTest() {
        val expected = Numkt.ndarray(Numkt[Numkt[0.22438827641771292, 0.39425779276225403], Numkt[0.7863984442713585, 0.826995590204087]])
        assertFloatingNDArray(expected, NativeMathEx.sin(ndarray))
    }

    @Test
    fun cosTest() {
        val expected = Numkt.ndarray(Numkt[Numkt[0.9744998211422555, 0.9189998872939189], Numkt[0.6177195859349023, 0.5622084077839763]])
        assertFloatingNDArray(expected, NativeMathEx.cos(ndarray))
    }

    @Test
    fun maxTest() = assertEquals(0.97374254f, Numkt.math.max(ndarray))

    @Test
    fun minTest() = assertEquals(0.22631526f, Numkt.math.min(ndarray))

    @Test
    fun sumTest() = assertFloatingNumber(2.5102746f, Numkt.math.sum(ndarray))
}