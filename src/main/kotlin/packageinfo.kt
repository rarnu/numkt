package com.rarnu.numkt

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.linalg.dot
import com.rarnu.numkt.api.ndarray
import com.rarnu.numkt.native.libLoader
import com.rarnu.numkt.native.linalg.NativeLinAlg
import com.rarnu.numkt.ndarray.complex.ComplexDouble

fun main() {
    libLoader("numkt_jni").load()

    val expected = Numkt.ndarray(
        Numkt[Numkt[ComplexDouble(-11.0, 79.0), ComplexDouble(-7.0, 59.0), ComplexDouble(-3.0, +39.0)], Numkt[ComplexDouble(-9.0, 111.0), ComplexDouble(-5.0, 83.0), ComplexDouble(-1.0, 55.0)], Numkt[ComplexDouble(-7.0, 143.0), ComplexDouble(-3.0, 107.0), ComplexDouble(1.0, 71.0)]]
    )

    val matrix1 = Numkt.ndarray(
        Numkt[Numkt[ComplexDouble(1, 2), ComplexDouble(3, 4)], Numkt[ComplexDouble(2, 3), ComplexDouble(4, 5)], Numkt[ComplexDouble(3, 4), ComplexDouble(5, 6)]]
    )
    val matrix2 = Numkt.ndarray(
        Numkt[Numkt[ComplexDouble(9, 8), ComplexDouble(7, 6), ComplexDouble(5, 4)], Numkt[ComplexDouble(8, 7), ComplexDouble(6, 5), ComplexDouble(4, 3)]]
    )

    val actual = NativeLinAlg.dot(matrix1, matrix2)
    println(expected == actual)
    // assertEquals(expected, actual)
}