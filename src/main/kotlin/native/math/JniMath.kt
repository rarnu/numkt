package com.rarnu.numkt.native.math

internal object JniMath {
    external fun argMax(arr: Any, offset: Int, size: Int, shape: IntArray, strides: IntArray?, dtype: Int): Int
    external fun argMin(arr: Any, offset: Int, size: Int, shape: IntArray, strides: IntArray?, dtype: Int): Int

    external fun exp(arr: FloatArray, size: Int): Boolean
    external fun exp(arr: DoubleArray, size: Int): Boolean
    external fun expC(arr: FloatArray, size: Int): Boolean
    external fun expC(arr: DoubleArray, size: Int): Boolean

    external fun log(arr: FloatArray, size: Int): Boolean
    external fun log(arr: DoubleArray, size: Int): Boolean
    external fun logC(arr: FloatArray, size: Int): Boolean
    external fun logC(arr: DoubleArray, size: Int): Boolean

    external fun sin(arr: FloatArray, size: Int): Boolean
    external fun sin(arr: DoubleArray, size: Int): Boolean
    external fun sinC(arr: FloatArray, size: Int): Boolean
    external fun sinC(arr: DoubleArray, size: Int): Boolean

    external fun cos(arr: FloatArray, size: Int): Boolean
    external fun cos(arr: DoubleArray, size: Int): Boolean
    external fun cosC(arr: FloatArray, size: Int): Boolean
    external fun cosC(arr: DoubleArray, size: Int): Boolean

    external fun arrayMax(arr: ByteArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Byte
    external fun arrayMax(arr: ShortArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Short
    external fun arrayMax(arr: IntArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Int
    external fun arrayMax(arr: LongArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Long
    external fun arrayMax(arr: FloatArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Float
    external fun arrayMax(arr: DoubleArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Double

    external fun arrayMin(arr: ByteArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Byte
    external fun arrayMin(arr: ShortArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Short
    external fun arrayMin(arr: IntArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Int
    external fun arrayMin(arr: LongArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Long
    external fun arrayMin(arr: FloatArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Float
    external fun arrayMin(arr: DoubleArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Double

    external fun sum(arr: ByteArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Byte
    external fun sum(arr: ShortArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Short
    external fun sum(arr: IntArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Int
    external fun sum(arr: LongArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Long
    external fun sum(arr: FloatArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Float
    external fun sum(arr: DoubleArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?): Double

    external fun cumSum(arr: ByteArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?, out: ByteArray, axis: Int = -1): Boolean
    external fun cumSum(arr: ShortArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?, out: ShortArray, axis: Int = -1): Boolean
    external fun cumSum(arr: IntArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?, out: IntArray, axis: Int = -1): Boolean
    external fun cumSum(arr: LongArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?, out: LongArray, axis: Int = -1): Boolean
    external fun cumSum(arr: FloatArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?, out: FloatArray, axis: Int = -1): Boolean
    external fun cumSum(arr: DoubleArray, offset: Int, size: Int, shape: IntArray, strides: IntArray?, out: DoubleArray, axis: Int = -1): Boolean
}