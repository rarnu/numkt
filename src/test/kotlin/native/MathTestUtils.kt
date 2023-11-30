package com.rarnu.numkt.test.native

import com.rarnu.numkt.api.stat.abs
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.NDArray
import com.rarnu.numkt.ndarray.operations.all
import com.rarnu.numkt.ndarray.operations.minus
import kotlin.math.abs
import kotlin.test.assertTrue

fun <D : Dimension> assertFloatingNDArray(expected: NDArray<Float, D>, actual: NDArray<Float, D>, epsilon: Float = 1e-6f, message: String? = null) {
    val diff = abs(expected - actual)
    assertTrue("${if (message == null) "" else "$message.\n"} " + "Expected \n<$expected>\n Actual \n<$actual>.") { diff.all { it < epsilon } }
}

fun <D : Dimension> assertComplexFloatingNDArray(expected: NDArray<ComplexFloat, D>, actual: NDArray<ComplexFloat, D>, epsilon: Float = 1e-6f, message: String? = null) {
    val diff = abs(expected - actual)
    assertTrue("${if (message == null) "" else "$message.\n"} " + "Expected \n<$expected>\n Actual \n<$actual>.") { diff.all { it < epsilon } }
}

fun <D : Dimension> assertFloatingNDArray(expected: NDArray<Double, D>, actual: NDArray<Double, D>, epsilon: Double = 1e-8, message: String? = null) {
    val diff = abs(expected - actual)
    assertTrue("${if (message == null) "" else "$message.\n"} " + "Expected \n<$expected>\n Actual \n<$actual>.") { diff.all { it < epsilon } }
}

fun <D : Dimension> assertComplexFloatingNDArray(expected: NDArray<ComplexDouble, D>, actual: NDArray<ComplexDouble, D>, epsilon: Double = 1e-8, message: String? = null) {
    val diff = abs(expected - actual)
    assertTrue("${if (message == null) "" else "$message.\n"} " + "Expected \n<$expected>\n Actual \n<$actual>.") { diff.all { it < epsilon } }
}

fun assertFloatingNumber(expected: Float, actual: Float, epsilon: Float = 1e-6f, message: String? = null) {
    val diff = abs(expected - actual)
    assertTrue("${if (message == null) "" else "$message. "} Expected <$expected>, actual <$actual>.") { diff < epsilon }
}

fun assertFloatingNumber(expected: Double, actual: Double, epsilon: Double = 1e-8, message: String? = null) {
    val diff = abs(expected - actual)
    assertTrue("${if (message == null) "" else "$message. "} Expected <$expected>, actual <$actual>.") { diff < epsilon }
}

fun assertFloatingComplexNumber(expected: ComplexFloat, actual: ComplexFloat, epsilon: Float = 1e-6f, message: String? = null) {
    val diff = (expected - actual).abs()
    assertTrue("${if (message == null) "" else "$message. "} Expected <$expected>, actual <$actual>.") { diff < epsilon }
}

fun assertFloatingComplexNumber(expected: ComplexDouble, actual: ComplexDouble, epsilon: Double = 1e-8, message: String? = null) {
    val diff = (expected - actual).abs()
    assertTrue("${if (message == null) "" else "$message. "} Expected <$expected>, actual <$actual>.") { diff < epsilon }
}