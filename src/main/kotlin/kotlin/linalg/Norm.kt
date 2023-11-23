@file:Suppress("DuplicatedCode")

package com.rarnu.numkt.kotlin.linalg

import kotlin.math.abs
import kotlin.math.pow

internal fun normFro(mat: FloatArray, offset: Int, strides: IntArray, n: Int, m: Int, power: Int): Float {
    var result = 0f
    val (stride0, stride1) = strides

    for (i in 0 until n) {
        val matInd = i * stride0 + offset
        for (k in 0 until m) {
            val absValue = abs(mat[matInd + k * stride1])
            result += absValue.pow(power)
        }
    }

    return result.pow(1f / power)
}

internal fun normFro(mat: DoubleArray, offset: Int, strides: IntArray, n: Int, m: Int, power: Int): Double {
    //most common case of matrix elements
    var result = 0.0
    val (stride0, stride1) = strides

    for (i in 0 until n) {
        val matInd = i * stride0 + offset
        for (k in 0 until m) {
            val absValue = abs(mat[matInd + k * stride1])
            result += absValue.pow(power)
        }
    }

    return result.pow(1.0 / power)
}

internal fun norm1(mat: FloatArray, offset: Int, strides: IntArray, n: Int, m: Int): Float {
    var result = 0f
    val (stride0, stride1) = strides

    for (j in 0 until m) {
        val matIdx = j * stride1
        var sum = 0f
        for (i in 0 until n) sum += abs(mat[matIdx + (i * stride0 + offset)])
        if (result < sum) result = sum
    }

    return result
}

internal fun norm1(mat: DoubleArray, offset: Int, strides: IntArray, n: Int, m: Int): Double {
    var result = .0
    val (stride0, stride1) = strides

    for (j in 0 until m) {
        val matIdx = j * stride1
        var sum = .0
        for (i in 0 until n) sum += abs(mat[matIdx + (i * stride0 + offset)])
        if (result < sum) result = sum
    }

    return result
}

internal fun normMax(mat: FloatArray, offset: Int, strides: IntArray, n: Int, m: Int): Float {
    var result: Float = mat[offset]
    val (stride0, stride1) = strides

    for (i in 0 until n) {
        val matIdx = i * stride0 + offset
        for (j in 0 until m) {
            val element = abs(mat[matIdx + j * stride1])
            if (result < element) result = element
        }
    }
    return result
}

internal fun normMax(mat: DoubleArray, offset: Int, strides: IntArray, n: Int, m: Int): Double {
    var result: Double = mat[offset]
    val (stride0, stride1) = strides

    for (i in 0 until n) {
        val matIdx = i * stride0 + offset
        for (j in 0 until m) {
            val element = abs(mat[matIdx + j * stride1])
            if (result < element) result = element
        }
    }
    return result
}

internal fun normInf(mat: FloatArray, offset: Int, strides: IntArray, n: Int, m: Int): Float {
    var result = 0f
    val (stride0, stride1) = strides

    for (i in 0 until n) {
        val matIdx = i * stride0 + offset
        var sum = 0f
        for (j in 0 until m) sum += abs(mat[matIdx + j * stride1])
        if (result < sum) result = sum
    }
    return result
}

internal fun normInf(mat: DoubleArray, offset: Int, strides: IntArray, n: Int, m: Int): Double {
    var result = .0
    val (stride0, stride1) = strides

    for (i in 0 until n) {
        val matIdx = i * stride0 + offset
        var sum = .0
        for (j in 0 until m) sum += abs(mat[matIdx + j * stride1])
        if (result < sum) result = sum
    }

    return result
}
