@file:Suppress("NOTHING_TO_INLINE", "DuplicatedCode")

package com.rarnu.numkt.ndarray.data

import kotlin.jvm.JvmName


internal inline fun <T> MultiArray<T, D1>.unsafeIndex(index: Int): Int = offset + strides.first() * index

internal inline fun <T> MultiArray<T, D2>.unsafeIndex(ind1: Int, ind2: Int): Int = offset + strides[0] * ind1 + strides[1] * ind2

internal inline fun <T> MultiArray<T, D3>.unsafeIndex(ind1: Int, ind2: Int, ind3: Int): Int = offset + strides[0] * ind1 + strides[1] * ind2 + strides[2] * ind3

internal inline fun <T> MultiArray<T, D4>.unsafeIndex(ind1: Int, ind2: Int, ind3: Int, ind4: Int): Int = offset + strides[0] * ind1 + strides[1] * ind2 + strides[2] * ind3 + strides[3] * ind4

internal inline fun <T> MultiArray<T, *>.unsafeIndex(indices: IntArray): Int = strides.foldIndexed(offset) { i, acc, stride -> acc + indices[i] * stride }


class ReadableView<T>(private val base: MultiArray<T, DN>) {
    operator fun get(vararg indices: Int): MultiArray<T, DN> = indices.fold(this.base) { m, pos -> m.view(pos) }
}

fun <T, D : Dimension, M : Dimension> MultiArray<T, D>.view(index: Int, axis: Int = 0): MultiArray<T, M> {
    checkBounds(index in 0 until shape[axis], index, axis, axis)
    return NDArray(data, offset + strides[axis] * index, shape.remove(axis), strides.remove(axis), dimensionOf(this.dim.d - 1), base ?: this)
}

fun <T, D : Dimension, M : Dimension> MultiArray<T, D>.view(indices: IntArray, axes: IntArray): MultiArray<T, M> {
    for ((ind, axis) in indices.zip(axes)) {
        checkBounds(ind in 0 until this.shape[axis], ind, axis, this.shape[axis])
    }
    val newShape = shape.filterIndexed { i, _ -> !axes.contains(i) }.toIntArray()
    val newStrides = strides.filterIndexed { i, _ -> !axes.contains(i) }.toIntArray()
    var newOffset = offset
    for (i in axes.indices) {
        newOffset += strides[axes[i]] * indices[i]
    }
    return NDArray(data, newOffset, newShape, newStrides, dimensionOf(this.dim.d - axes.size), base ?: this)
}

@JvmName("viewD2")
fun <T> MultiArray<T, D2>.view(index: Int, axis: Int = 0): MultiArray<T, D1> = view<T, D2, D1>(index, axis)

@JvmName("viewD3")
fun <T> MultiArray<T, D3>.view(index: Int, axis: Int = 0): MultiArray<T, D2> = view<T, D3, D2>(index, axis)

@JvmName("viewD3toD1")
fun <T> MultiArray<T, D3>.view(ind1: Int, ind2: Int, axis1: Int = 0, axis2: Int = 1): MultiArray<T, D1> = view(intArrayOf(ind1, ind2), intArrayOf(axis1, axis2))

@JvmName("viewD4")
fun <T> MultiArray<T, D4>.view(index: Int, axis: Int = 0): MultiArray<T, D3> = view<T, D4, D3>(index, axis)

@JvmName("viewD4toD2")
fun <T> MultiArray<T, D4>.view(ind1: Int, ind2: Int, axis1: Int = 0, axis2: Int = 1): MultiArray<T, D2> = view(intArrayOf(ind1, ind2), intArrayOf(axis1, axis2))

@JvmName("viewD4toD1")
fun <T> MultiArray<T, D4>.view(ind1: Int, ind2: Int, ind3: Int, axis1: Int = 0, axis2: Int = 1, axis3: Int = 2): MultiArray<T, D1> = view(intArrayOf(ind1, ind2, ind3), intArrayOf(axis1, axis2, axis3))

@JvmName("viewDN")
fun <T> MultiArray<T, DN>.view(index: Int, axis: Int = 0): MultiArray<T, DN> = view<T, DN, DN>(index, axis)

@JvmName("viewDN")
fun <T> MultiArray<T, DN>.view(index: IntArray, axes: IntArray): MultiArray<T, DN> = view<T, DN, DN>(index, axes)

val <T> MultiArray<T, DN>.V: ReadableView<T> get() = ReadableView(this)

//____________________________________________________Get_______________________________________________________________

@JvmName("getView1")
operator fun <T> MultiArray<T, D2>.get(index: Int): MultiArray<T, D1> = view(index, 0)

@JvmName("getView2")
operator fun <T> MultiArray<T, D3>.get(index: Int): MultiArray<T, D2> = view(index, 0)

@JvmName("getView3")
operator fun <T> MultiArray<T, D3>.get(ind1: Int, ind2: Int): MultiArray<T, D1> = view(ind1, ind2, 0, 1)

@JvmName("getView4")
operator fun <T> MultiArray<T, D4>.get(index: Int): MultiArray<T, D3> = view(index, 0)

@JvmName("getView5")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: Int): MultiArray<T, D2> = view(ind1, ind2, 0, 1)

@JvmName("getView6")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: Int, ind3: Int): MultiArray<T, D1> = view(ind1, ind2, ind3, 0, 1, 2)


fun <T, D : Dimension, O : Dimension> MultiArray<T, D>.slice(inSlice: ClosedRange<Int>, axis: Int = 0): NDArray<T, O> {
    require(axis in 0 until this.dim.d) { "axis out of bounds: $axis" }

    val slice = inSlice.toSlice()

    val actualFrom = if (slice.start != -1) {
        check(slice.start > -1) { "slicing start index must be positive, but was ${slice.start}" }
        slice.start
    } else 0
    val actualTo = if (slice.stop != -1) {
        check(slice.stop <= shape[axis]) { "slicing end index out of bounds: ${slice.stop} > ${shape[axis]}" }
        slice.stop
    } else {
        check(shape[axis] > actualFrom) { "slicing start index out of bounds: $actualFrom >= ${shape[axis]}" }
        shape[axis] - 1
    }

    val sliceStrides = strides.copyOf().apply { this[axis] *= slice.step }
    val sliceShape = if (actualFrom > actualTo) {
        intArrayOf(0)
    } else {
        shape.copyOf().apply { this[axis] = (actualTo - actualFrom + slice.step) / slice.step }
    }
    return NDArray(data, offset + actualFrom * strides[axis], sliceShape, sliceStrides, dimensionOf(sliceShape.size), base ?: this)
}


fun <T, D : Dimension, O : Dimension> MultiArray<T, D>.slice(indexing: Map<Int, Indexing>): NDArray<T, O> {
    var newOffset = offset
    var newShape: IntArray = shape.copyOf()
    var newStrides: IntArray = strides.copyOf()
    val removeAxes = mutableListOf<Int>()
    for (ind in indexing) {
        require(ind.key in 0 until this.dim.d) { "axis out of bounds: ${ind.key}" }
        when (ind.value) {
            is RInt -> {
                val index = (ind.value as RInt).data
                require(index in 0 until shape[ind.key]) { "Index $index out of bounds at [0, ${shape[ind.key] - 1}]" }

                newOffset += newStrides[ind.key] * index
                removeAxes.add(ind.key)
            }

            is Slice -> {
                val index = ind.value as Slice

                val actualFrom = if (index.start != -1) {
                    check(index.start > -1) { "slicing start index must be positive, but was ${index.start}" }
                    index.start
                } else 0
                val actualTo = if (index.stop != -1) {
                    check(index.stop <= shape[ind.key]) { "slicing end index out of bounds: ${index.stop} > ${shape[ind.key]}" }
                    index.stop
                } else {
                    check(shape[ind.key] > index.start) { "slicing start index out of bounds: $actualFrom >= ${shape[ind.key]}" }
                    shape[ind.key] - 1
                }

                newOffset += actualFrom * newStrides[ind.key]
                newShape[ind.key] = if (actualFrom > actualTo) 0 else (actualTo - actualFrom + index.step) / index.step
                newStrides[ind.key] *= index.step
            }
        }
    }

    newShape = newShape.removeAll(removeAxes)
    newStrides = newStrides.removeAll(removeAxes)
    return NDArray(this.data, newOffset, newShape, newStrides, dimensionOf(newShape.size), base ?: this)
}

@JvmName("getView7")
operator fun <T> MultiArray<T, D1>.get(index: ClosedRange<Int>): MultiArray<T, D1> = slice(index)

@JvmName("getView8")
operator fun <T> MultiArray<T, D2>.get(index: ClosedRange<Int>): MultiArray<T, D2> = slice(index)

@JvmName("getView9")
operator fun <T> MultiArray<T, D2>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice()))

@JvmName("getView10")
operator fun <T> MultiArray<T, D2>.get(ind1: Int, ind2: ClosedRange<Int>): MultiArray<T, D1> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice()))

@JvmName("getView11")
operator fun <T> MultiArray<T, D2>.get(ind1: ClosedRange<Int>, ind2: Int): MultiArray<T, D1> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r))

@JvmName("getView12")
operator fun <T> MultiArray<T, D3>.get(index: ClosedRange<Int>): MultiArray<T, D3> = slice(index)

@JvmName("getView13")
operator fun <T> MultiArray<T, D3>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice()))

@JvmName("getView14")
operator fun <T> MultiArray<T, D3>.get(ind1: Int, ind2: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice()))

@JvmName("getView15")
operator fun <T> MultiArray<T, D3>.get(ind1: ClosedRange<Int>, ind2: Int): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r))

@JvmName("getView16")
operator fun <T> MultiArray<T, D3>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.toSlice()))

@JvmName("getView17")
operator fun <T> MultiArray<T, D3>.get(ind1: Int, ind2: Int, ind3: ClosedRange<Int>): MultiArray<T, D1> = slice(mapOf(0 to ind1.r, 1 to ind2.r, 2 to ind3.toSlice()))

@JvmName("getView18")
operator fun <T> MultiArray<T, D3>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: Int): MultiArray<T, D1> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.r))

@JvmName("getView19")
operator fun <T> MultiArray<T, D3>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: Int): MultiArray<T, D1> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.r))

@JvmName("getView20")
operator fun <T> MultiArray<T, D3>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.toSlice()))

@JvmName("getView21")
operator fun <T> MultiArray<T, D3>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: Int): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.r))

@JvmName("getView22")
operator fun <T> MultiArray<T, D3>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.toSlice()))

@JvmName("getView23")
operator fun <T> MultiArray<T, D4>.get(index: ClosedRange<Int>): MultiArray<T, D4> = slice(index)

@JvmName("getView24")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>): MultiArray<T, D4> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice()))

@JvmName("getView25")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice()))

@JvmName("getView26")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: Int): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r))

@JvmName("getView27")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>): MultiArray<T, D4> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.toSlice()))

@JvmName("getView28")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: Int, ind3: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.r, 1 to ind2.r, 2 to ind3.toSlice()))

@JvmName("getView29")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: Int): MultiArray<T, D2> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.r))

@JvmName("getView30")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: Int): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.r))

@JvmName("getView31")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.toSlice()))

@JvmName("getView32")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: Int): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.r))

@JvmName("getView33")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.toSlice()))

@JvmName("getView34")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>, ind4: ClosedRange<Int>): MultiArray<T, D4> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.toSlice(), 3 to ind4.toSlice()))

@JvmName("getView35")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: Int, ind3: Int, ind4: ClosedRange<Int>): MultiArray<T, D1> = slice(mapOf(0 to ind1.r, 1 to ind2.r, 2 to ind3.r, 3 to ind4.toSlice()))

@JvmName("getView36")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: Int, ind3: ClosedRange<Int>, ind4: Int): MultiArray<T, D1> = slice(mapOf(0 to ind1.r, 1 to ind2.r, 2 to ind3.toSlice(), 3 to ind4.r))

@JvmName("getView37")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: Int, ind4: Int): MultiArray<T, D1> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.r, 3 to ind4.r))

@JvmName("getView38")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: Int, ind4: Int): MultiArray<T, D1> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.r, 3 to ind4.r))

@JvmName("getView39")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: Int, ind3: ClosedRange<Int>, ind4: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.r, 1 to ind2.r, 2 to ind3.toSlice(), 3 to ind4.toSlice()))

@JvmName("getView40")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>, ind4: Int): MultiArray<T, D2> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.toSlice(), 3 to ind4.r))

@JvmName("getView41")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: Int, ind4: Int): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.r, 3 to ind4.r))

@JvmName("getView42")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: Int, ind4: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.r, 3 to ind4.toSlice()))

@JvmName("getView43")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: Int, ind4: ClosedRange<Int>): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.r, 3 to ind4.toSlice()))

@JvmName("getView44")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: ClosedRange<Int>, ind4: Int): MultiArray<T, D2> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.toSlice(), 3 to ind4.r))

@JvmName("getView45")
operator fun <T> MultiArray<T, D4>.get(ind1: Int, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>, ind4: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.r, 1 to ind2.toSlice(), 2 to ind3.toSlice(), 3 to ind4.toSlice()))

@JvmName("getView46")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: Int, ind3: ClosedRange<Int>, ind4: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.r, 2 to ind3.toSlice(), 3 to ind4.toSlice()))

@JvmName("getView47")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: Int, ind4: ClosedRange<Int>): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.r, 3 to ind4.toSlice()))

@JvmName("getView48")
operator fun <T> MultiArray<T, D4>.get(ind1: ClosedRange<Int>, ind2: ClosedRange<Int>, ind3: ClosedRange<Int>, ind4: Int): MultiArray<T, D3> = slice(mapOf(0 to ind1.toSlice(), 1 to ind2.toSlice(), 2 to ind3.toSlice(), 3 to ind4.r))

fun <T> MultiArray<T, DN>.slice(map: Map<Int, Indexing>): MultiArray<T, DN> = slice<T, DN, DN>(map)

class WritableView<T>(private val base: MutableMultiArray<T, DN>) {

    operator fun get(vararg indices: Int): MutableMultiArray<T, DN> = indices.fold(this.base) { m, pos -> m.mutableView(pos) }

    companion object
}

inline fun <T, D : Dimension, reified M : Dimension> MultiArray<T, D>.writableView(index: Int, axis: Int = 0): MutableMultiArray<T, M> {
    checkBounds(index in 0 until shape[axis], index, axis, axis)
    return NDArray(data, offset + strides[axis] * index, shape.remove(axis), strides.remove(axis), dimensionClassOf(this.dim.d - 1), base ?: this)
}

inline fun <T, D : Dimension, reified M : Dimension> MultiArray<T, D>.writableView(indices: IntArray, axes: IntArray): MutableMultiArray<T, M> {
    for ((ind, axis) in indices.zip(axes)) {
        checkBounds(ind in 0 until this.shape[axis], ind, axis, this.shape[axis])
    }
    val newShape = shape.filterIndexed { i, _ -> !axes.contains(i) }.toIntArray()
    val newStrides = strides.filterIndexed { i, _ -> !axes.contains(i) }.toIntArray()
    var newOffset = offset
    for (i in axes.indices) {
        newOffset += strides[axes[i]] * indices[i]
    }
    return NDArray(data, newOffset, newShape, newStrides, dimensionOf(this.dim.d - axes.size), base ?: this)
}

inline fun <T, D : Dimension, reified M : Dimension> MutableMultiArray<T, D>.mutableView(index: Int, axis: Int = 0): MutableMultiArray<T, M> = this.writableView(index, axis)

inline fun <T, D : Dimension, reified M : Dimension> MutableMultiArray<T, D>.mutableView(index: IntArray, axes: IntArray): MutableMultiArray<T, M> = this.writableView(index, axes)

@JvmName("mutableViewD2")
fun <T> MutableMultiArray<T, D2>.mutableView(index: Int, axis: Int = 0): MutableMultiArray<T, D1> = mutableView<T, D2, D1>(index, axis)

@JvmName("mutableViewD3")
fun <T> MutableMultiArray<T, D3>.mutableView(index: Int, axis: Int = 0): MutableMultiArray<T, D2> = mutableView<T, D3, D2>(index, axis)

@JvmName("mutableViewD3toD1")
fun <T> MutableMultiArray<T, D3>.mutableView(ind1: Int, ind2: Int, axis1: Int = 0, axis2: Int = 1): MutableMultiArray<T, D1> = mutableView(intArrayOf(ind1, ind2), intArrayOf(axis1, axis2))

@JvmName("mutableViewD4")
fun <T> MutableMultiArray<T, D4>.mutableView(index: Int, axis: Int = 0): MutableMultiArray<T, D3> = mutableView<T, D4, D3>(index, axis)

@JvmName("mutableViewD4toD2")
fun <T> MutableMultiArray<T, D4>.mutableView(ind1: Int, ind2: Int, axis1: Int = 0, axis2: Int = 1): MutableMultiArray<T, D2> = mutableView(intArrayOf(ind1, ind2), intArrayOf(axis1, axis2))

@JvmName("mutableViewD4toD1")
fun <T> MutableMultiArray<T, D4>.mutableView(ind1: Int, ind2: Int, ind3: Int, axis1: Int = 0, axis2: Int = 1, axis3: Int = 2): MutableMultiArray<T, D1> = mutableView(intArrayOf(ind1, ind2, ind3), intArrayOf(axis1, axis2, axis3))

@JvmName("mutableViewDN")
fun <T> MutableMultiArray<T, DN>.mutableView(index: Int, axis: Int = 0): MutableMultiArray<T, DN> = mutableView<T, DN, DN>(index, axis)

@JvmName("mutableViewDN")
fun <T> MutableMultiArray<T, DN>.mutableView(index: IntArray, axes: IntArray): MutableMultiArray<T, DN> = mutableView<T, DN, DN>(index, axes)

val <T> MutableMultiArray<T, DN>.W: WritableView<T> get() = WritableView(this)

@JvmName("set1")
operator fun <T> MutableMultiArray<T, D2>.set(index: Int, value: MultiArray<T, D1>) {
    val ret = this.mutableView(index, 0)
    requireArraySizes(ret.size, value.size)
    for (i in ret.indices) {
        ret[i] = value[i]
    }
}

@JvmName("set2")
operator fun <T> MutableMultiArray<T, D3>.set(index: Int, value: MultiArray<T, D2>) {
    val ret = this.mutableView(index, 0)
    requireArraySizes(ret.size, value.size)
    for ((i, j) in ret.multiIndices) {
        ret[i, j] = value[i, j]
    }
}

@JvmName("set3")
operator fun <T> MutableMultiArray<T, D3>.set(ind1: Int, ind2: Int, value: MultiArray<T, D1>) {
    val ret = this.mutableView(ind1, ind2, 0, 1)
    requireArraySizes(ret.size, value.size)
    for (i in ret.indices) {
        ret[i] = value[i]
    }
}

@JvmName("set4")
operator fun <T> MutableMultiArray<T, D4>.set(index: Int, value: MultiArray<T, D3>) {
    val ret = this.mutableView(index, 0)
    requireArraySizes(ret.size, value.size)
    for ((i, j, k) in ret.multiIndices) {
        ret[i, j, k] = value[i, j, k]
    }
}

@JvmName("set5")
operator fun <T> MutableMultiArray<T, D4>.set(ind1: Int, ind2: Int, value: MultiArray<T, D2>) {
    val ret = this.mutableView(ind1, ind2, 0, 1)
    requireArraySizes(ret.size, value.size)
    for ((i, j) in ret.multiIndices) {
        ret[i, j] = value[i, j]
    }
}

@JvmName("set6")
operator fun <T> MutableMultiArray<T, D4>.set(ind1: Int, ind2: Int, ind3: Int, value: MultiArray<T, D1>) {
    val ret = this.mutableView(ind1, ind2, ind3, 0, 1, 2)
    requireArraySizes(ret.size, value.size)
    for (i in ret.indices) {
        ret[i] = value[i]
    }
}
