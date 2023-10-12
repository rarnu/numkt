@file:Suppress("DuplicatedCode")

package com.rarnu.numkt.ndarray.data

import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat

operator fun <T> MultiArray<T, D1>.get(index: Int): T {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return data[unsafeIndex(index)]
}

operator fun <T> MultiArray<T, D2>.get(ind1: Int, ind2: Int): T {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return data[unsafeIndex(ind1, ind2)]
}

operator fun <T> MultiArray<T, D3>.get(ind1: Int, ind2: Int, ind3: Int): T {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return data[unsafeIndex(ind1, ind2, ind3)]
}

operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): T {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return data[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun <T> MultiArray<T, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): T = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun <T> MultiArray<T, *>.get(indices: IntArray): T {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return data[unsafeIndex(indices)]
}

operator fun <T> MutableMultiArray<T, D1>.set(index: Int, value: T) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    data[unsafeIndex(index)] = value
}

operator fun <T> MutableMultiArray<T, D2>.set(ind1: Int, ind2: Int, value: T) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    data[unsafeIndex(ind1, ind2)] = value
}

operator fun <T> MutableMultiArray<T, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: T) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    data[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun <T> MutableMultiArray<T, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: T) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    data[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun <T> MutableMultiArray<T, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: T) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun <T> MutableMultiArray<T, *>.set(indices: IntArray, value: T) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    data[unsafeIndex(indices)] = value
}

operator fun MultiArray<Byte, D1>.get(index: Int): Byte {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewByteArray)[unsafeIndex(index)]
}

operator fun MultiArray<Byte, D2>.get(ind1: Int, ind2: Int): Byte {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewByteArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<Byte, D3>.get(ind1: Int, ind2: Int, ind3: Int): Byte {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewByteArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<Byte, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): Byte {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewByteArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<Byte, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): Byte = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<Byte, *>.get(indices: IntArray): Byte {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewByteArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<Byte, D1>.set(index: Int, value: Byte) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewByteArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<Byte, D2>.set(ind1: Int, ind2: Int, value: Byte) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewByteArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<Byte, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: Byte) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewByteArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<Byte, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: Byte) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewByteArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<Byte, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: Byte) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<Byte, *>.set(indices: IntArray, value: Byte) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    (data as MemoryViewByteArray)[unsafeIndex(indices)] = value
}


operator fun MultiArray<Short, D1>.get(index: Int): Short {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewShortArray)[unsafeIndex(index)]
}

operator fun MultiArray<Short, D2>.get(ind1: Int, ind2: Int): Short {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewShortArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<Short, D3>.get(ind1: Int, ind2: Int, ind3: Int): Short {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewShortArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<Short, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): Short {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewShortArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<Short, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): Short = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<Short, *>.get(indices: IntArray): Short {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewShortArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<Short, D1>.set(index: Int, value: Short) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewShortArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<Short, D2>.set(ind1: Int, ind2: Int, value: Short) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewShortArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<Short, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: Short) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewShortArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<Short, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: Short) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewShortArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<Short, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: Short) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<Short, *>.set(indices: IntArray, value: Short) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    (data as MemoryViewShortArray)[unsafeIndex(indices)] = value
}

operator fun MultiArray<Int, D1>.get(index: Int): Int {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewIntArray)[unsafeIndex(index)]
}

operator fun MultiArray<Int, D2>.get(ind1: Int, ind2: Int): Int {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewIntArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<Int, D3>.get(ind1: Int, ind2: Int, ind3: Int): Int {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewIntArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<Int, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): Int {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewIntArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<Int, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): Int = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<Int, *>.get(indices: IntArray): Int {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewIntArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<Int, D1>.set(index: Int, value: Int) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewIntArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<Int, D2>.set(ind1: Int, ind2: Int, value: Int) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewIntArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<Int, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: Int) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewIntArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<Int, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: Int) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewIntArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<Int, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: Int) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<Int, *>.set(indices: IntArray, value: Int) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    (data as MemoryViewIntArray)[unsafeIndex(indices)] = value
}


operator fun MultiArray<Long, D1>.get(index: Int): Long {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewLongArray)[unsafeIndex(index)]
}

operator fun MultiArray<Long, D2>.get(ind1: Int, ind2: Int): Long {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewLongArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<Long, D3>.get(ind1: Int, ind2: Int, ind3: Int): Long {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewLongArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<Long, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): Long {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewLongArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<Long, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): Long = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<Long, *>.get(indices: IntArray): Long {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewLongArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<Long, D1>.set(index: Int, value: Long) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewLongArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<Long, D2>.set(ind1: Int, ind2: Int, value: Long) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewLongArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<Long, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: Long) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewLongArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<Long, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: Long) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewLongArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<Long, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: Long) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<Long, *>.set(indices: IntArray, value: Long) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    (data as MemoryViewLongArray)[unsafeIndex(indices)] = value
}


operator fun MultiArray<Float, D1>.get(index: Int): Float {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewFloatArray)[unsafeIndex(index)]
}

operator fun MultiArray<Float, D2>.get(ind1: Int, ind2: Int): Float {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewFloatArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<Float, D3>.get(ind1: Int, ind2: Int, ind3: Int): Float {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewFloatArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<Float, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): Float {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewFloatArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<Float, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): Float = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<Float, *>.get(indices: IntArray): Float {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewFloatArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<Float, D1>.set(index: Int, value: Float) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewFloatArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<Float, D2>.set(ind1: Int, ind2: Int, value: Float) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewFloatArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<Float, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: Float) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewFloatArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<Float, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: Float) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewFloatArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<Float, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: Float) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<Float, *>.set(indices: IntArray, value: Float) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    (data as MemoryViewFloatArray)[unsafeIndex(indices)] = value
}

operator fun MultiArray<Double, D1>.get(index: Int): Double {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewDoubleArray)[unsafeIndex(index)]
}

operator fun MultiArray<Double, D2>.get(ind1: Int, ind2: Int): Double {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewDoubleArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<Double, D3>.get(ind1: Int, ind2: Int, ind3: Int): Double {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewDoubleArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<Double, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): Double {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewDoubleArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<Double, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): Double = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<Double, *>.get(indices: IntArray): Double {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewDoubleArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<Double, D1>.set(index: Int, value: Double) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewDoubleArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<Double, D2>.set(ind1: Int, ind2: Int, value: Double) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewDoubleArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<Double, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: Double) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewDoubleArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<Double, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: Double) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewDoubleArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<Double, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: Double) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<Double, *>.set(indices: IntArray, value: Double) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    (data as MemoryViewDoubleArray)[unsafeIndex(indices)] = value
}

operator fun MultiArray<ComplexFloat, D1>.get(index: Int): ComplexFloat {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewComplexFloatArray)[unsafeIndex(index)]
}

operator fun MultiArray<ComplexFloat, D2>.get(ind1: Int, ind2: Int): ComplexFloat {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewComplexFloatArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<ComplexFloat, D3>.get(ind1: Int, ind2: Int, ind3: Int): ComplexFloat {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewComplexFloatArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<ComplexFloat, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): ComplexFloat {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewComplexFloatArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<ComplexFloat, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): ComplexFloat = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<ComplexFloat, *>.get(indices: IntArray): ComplexFloat {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewComplexFloatArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<ComplexFloat, D1>.set(index: Int, value: ComplexFloat) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewComplexFloatArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<ComplexFloat, D2>.set(ind1: Int, ind2: Int, value: ComplexFloat) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewComplexFloatArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<ComplexFloat, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: ComplexFloat) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewComplexFloatArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<ComplexFloat, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: ComplexFloat) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewComplexFloatArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<ComplexFloat, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: ComplexFloat) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<ComplexFloat, *>.set(indices: IntArray, value: ComplexFloat) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    (data as MemoryViewComplexFloatArray)[unsafeIndex(indices)] = value
}

operator fun MultiArray<ComplexDouble, D1>.get(index: Int): ComplexDouble {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    return (data as MemoryViewComplexDoubleArray)[unsafeIndex(index)]
}

operator fun MultiArray<ComplexDouble, D2>.get(ind1: Int, ind2: Int): ComplexDouble {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    return (data as MemoryViewComplexDoubleArray)[unsafeIndex(ind1, ind2)]
}

operator fun MultiArray<ComplexDouble, D3>.get(ind1: Int, ind2: Int, ind3: Int): ComplexDouble {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    return (data as MemoryViewComplexDoubleArray)[unsafeIndex(ind1, ind2, ind3)]
}

operator fun MultiArray<ComplexDouble, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int): ComplexDouble {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    return (data as MemoryViewComplexDoubleArray)[unsafeIndex(ind1, ind2, ind3, ind4)]
}

operator fun MultiArray<ComplexDouble, *>.get(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int): ComplexDouble = this[intArrayOf(ind1, ind2, ind3, ind4) + indices]

operator fun MultiArray<ComplexDouble, *>.get(indices: IntArray): ComplexDouble {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    return (data as MemoryViewComplexDoubleArray)[unsafeIndex(indices)]
}

operator fun MutableMultiArray<ComplexDouble, D1>.set(index: Int, value: ComplexDouble) {
    checkBounds(index in 0 until this.shape[0], index, 0, this.shape[0])
    (data as MemoryViewComplexDoubleArray)[unsafeIndex(index)] = value
}

operator fun MutableMultiArray<ComplexDouble, D2>.set(ind1: Int, ind2: Int, value: ComplexDouble) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    (data as MemoryViewComplexDoubleArray)[unsafeIndex(ind1, ind2)] = value
}

operator fun MutableMultiArray<ComplexDouble, D3>.set(ind1: Int, ind2: Int, ind3: Int, value: ComplexDouble) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    (data as MemoryViewComplexDoubleArray)[unsafeIndex(ind1, ind2, ind3)] = value
}

operator fun MutableMultiArray<ComplexDouble, D4>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, value: ComplexDouble) {
    checkBounds(ind1 in 0 until this.shape[0], ind1, 0, this.shape[0])
    checkBounds(ind2 in 0 until this.shape[1], ind2, 1, this.shape[1])
    checkBounds(ind3 in 0 until this.shape[2], ind3, 2, this.shape[2])
    checkBounds(ind4 in 0 until this.shape[3], ind4, 3, this.shape[3])
    (data as MemoryViewComplexDoubleArray)[unsafeIndex(ind1, ind2, ind3, ind4)] = value
}

operator fun MutableMultiArray<ComplexDouble, *>.set(ind1: Int, ind2: Int, ind3: Int, ind4: Int, vararg indices: Int, value: ComplexDouble) {
    set(intArrayOf(ind1, ind2, ind3, ind4) + indices, value)
}

operator fun MutableMultiArray<ComplexDouble, *>.set(indices: IntArray, value: ComplexDouble) {
    check(indices.size == dim.d) { "number of indices doesn't match dimension: ${indices.size} != ${dim.d}" }
    for (i in indices.indices) {
        checkBounds(indices[i] in 0 until this.shape[i], indices[i], i, this.shape[i])
    }
    (data as MemoryViewComplexDoubleArray)[unsafeIndex(indices)] = value
}


