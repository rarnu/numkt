package com.rarnu.numkt.api

import com.rarnu.numkt.ndarray.data.*

/**
 * Returns an D2Array from Array<ByteArray>.
 */
fun Numkt.ndarray(args: Array<ByteArray>): D2Array<Byte> {
    val dim0 = args.size
    val dim1 = args[0].size
    require(args.all { dim1 == it.size }) { "Arrays must be the same size." }

    val array = ByteArray(dim0 * dim1)
    var index = 0
    for (i in 0 until dim0) {
        for (j in 0 until dim1) {
            array[index++] = args[i][j]
        }
    }
    val data = MemoryViewByteArray(array)
    return D2Array(data, shape = intArrayOf(dim0, dim1), dim = D2)
}

/**
 * Returns an D2Array from Array<ShortArray>.
 */
fun Numkt.ndarray(args: Array<ShortArray>): D2Array<Short> {
    val dim0 = args.size
    val dim1 = args[0].size
    require(args.all { dim1 == it.size }) { "Arrays must be the same size." }

    val array = ShortArray(dim0 * dim1)
    var index = 0
    for (i in 0 until dim0) {
        for (j in 0 until dim1) {
            array[index++] = args[i][j]
        }
    }
    val data = MemoryViewShortArray(array)
    return D2Array(data, shape = intArrayOf(dim0, dim1), dim = D2)
}

/**
 * Returns an D2Array from Array<IntArray>.
 */
fun Numkt.ndarray(args: Array<IntArray>): D2Array<Int> {
    val dim0 = args.size
    val dim1 = args[0].size
    require(args.all { dim1 == it.size }) { "Arrays must be the same size." }

    val array = IntArray(dim0 * dim1)
    var index = 0
    for (i in 0 until dim0) {
        for (j in 0 until dim1) {
            array[index++] = args[i][j]
        }
    }
    val data = MemoryViewIntArray(array)
    return D2Array(data, shape = intArrayOf(dim0, dim1), dim = D2)
}

/**
 * Returns an D2Array from Array<LongArray>.
 */
fun Numkt.ndarray(args: Array<LongArray>): D2Array<Long> {
    val dim0 = args.size
    val dim1 = args[0].size
    require(args.all { dim1 == it.size }) { "Arrays must be the same size." }

    val array = LongArray(dim0 * dim1)
    var index = 0
    for (i in 0 until dim0) {
        for (j in 0 until dim1) {
            array[index++] = args[i][j]
        }
    }
    val data = MemoryViewLongArray(array)
    return D2Array(data, shape = intArrayOf(dim0, dim1), dim = D2)
}

/**
 * Returns an D2Array from Array<FloatArray>.
 */
fun Numkt.ndarray(args: Array<FloatArray>): D2Array<Float> {
    val dim0 = args.size
    val dim1 = args[0].size
    require(args.all { dim1 == it.size }) { "Arrays must be the same size." }

    val array = FloatArray(dim0 * dim1)
    var index = 0
    for (i in 0 until dim0) {
        for (j in 0 until dim1) {
            array[index++] = args[i][j]
        }
    }
    val data = MemoryViewFloatArray(array)
    return D2Array(data, shape = intArrayOf(dim0, dim1), dim = D2)
}

/**
 * Returns an D2Array from Array<DoubleArray>.
 */
fun Numkt.ndarray(args: Array<DoubleArray>): D2Array<Double> {
    val dim0 = args.size
    val dim1 = args[0].size
    require(args.all { dim1 == it.size }) { "Arrays must be the same size." }

    val array = DoubleArray(dim0 * dim1)
    var index = 0
    for (i in 0 until dim0) {
        for (j in 0 until dim1) {
            array[index++] = args[i][j]
        }
    }
    val data = MemoryViewDoubleArray(array)
    return D2Array(data, shape = intArrayOf(dim0, dim1), dim = D2)
}

/**
 * Returns an D2Array.
 */
fun Array<ByteArray>.toNDArray(): D2Array<Byte> = Numkt.ndarray(this)

/**
 * Returns an D2Array.
 */
fun Array<ShortArray>.toNDArray(): D2Array<Short> = Numkt.ndarray(this)

/**
 * Returns an D2Array.
 */
fun Array<IntArray>.toNDArray(): D2Array<Int> = Numkt.ndarray(this)

/**
 * Returns an D2Array.
 */
fun Array<LongArray>.toNDArray(): D2Array<Long> = Numkt.ndarray(this)

/**
 * Returns an D2Array.
 */
fun Array<FloatArray>.toNDArray(): D2Array<Float> = Numkt.ndarray(this)

/**
 * Returns an D2Array.
 */
fun Array<DoubleArray>.toNDArray(): D2Array<Double> = Numkt.ndarray(this)
