@file:Suppress("UNCHECKED_CAST")

package com.rarnu.numkt.ndarray.data

import com.rarnu.numkt.ndarray.complex.*

/**
 * View for storing data in a [NDArray] and working them in a uniform style.
 *
 * @property data one of the primitive arrays.
 * @property dtype type of elements in array
 */
interface ImmutableMemoryView<T> : Iterable<T> {

    val data: Any
    val dtype: DataType
    var size: Int

    /**
     * Returns the value at [index].
     *
     * Note: Indexing takes place according to the initial data, so if you did any manipulations with the ndarray
     * (ex. reshape), then `get` from the ndarray with the same index will return another value.
     */
    operator fun get(index: Int): T

    /**
     * [data] iterator
     */
    override fun iterator(): Iterator<T>

    /**
     * Returns a new instance with a copied primitive array.
     */
    fun copyOf(): ImmutableMemoryView<T>

    /**
     *
     */
    fun copyInto(destination: MemoryView<T>, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = size): MemoryView<T>

    /**
     * Returns [ByteArray] if it is [MemoryViewByteArray].
     */
    fun getByteArray(): ByteArray

    /**
     * Returns [ShortArray] if it is [MemoryViewShortArray].
     */
    fun getShortArray(): ShortArray

    /**
     * Returns [IntArray] if it is [MemoryViewIntArray].
     */
    fun getIntArray(): IntArray

    /**
     * Returns [LongArray] if it is [MemoryViewLongArray].
     */
    fun getLongArray(): LongArray

    /**
     * Returns [FloatArray] if it is [MemoryViewFloatArray].
     *
     * Note: For [MemoryViewComplexFloatArray], an array will be returned storing real and imaginary parts continuously.
     */
    fun getFloatArray(): FloatArray

    /**
     * Returns [DoubleArray].
     *
     * Note: For [MemoryViewComplexDoubleArray], an array will be returned storing real and imaginary parts continuously.
     */
    fun getDoubleArray(): DoubleArray

    /**
     * Returns [ComplexFloatArray] if it is [MemoryViewFloatArray].
     */
    fun getComplexFloatArray(): ComplexFloatArray

    /**
     * Returns [ComplexDoubleArray] if it is [MemoryViewComplexDoubleArray].
     */
    fun getComplexDoubleArray(): ComplexDoubleArray
}

/**
 * Extends [ImmutableMemoryView].
 *
 * @property size number of elements in [data].
 * @property indices indices of [data].
 * @property lastIndex last index in [data].
 */
sealed class MemoryView<T> : ImmutableMemoryView<T> {

    abstract var indices: IntRange

    abstract var lastIndex: Int

    /**
     * Replaces the element at the given [index] with the specified [value].
     *
     * Note: Indexing takes place according to the initial data.
     */
    abstract operator fun set(index: Int, value: T)

    abstract override fun copyOf(): MemoryView<T>

    override fun getByteArray(): ByteArray = throw UnsupportedOperationException()

    override fun getShortArray(): ShortArray = throw UnsupportedOperationException()

    override fun getIntArray(): IntArray = throw UnsupportedOperationException()

    override fun getLongArray(): LongArray = throw UnsupportedOperationException()

    override fun getFloatArray(): FloatArray = throw UnsupportedOperationException()

    override fun getDoubleArray(): DoubleArray = throw UnsupportedOperationException()

    override fun getComplexFloatArray(): ComplexFloatArray = throw UnsupportedOperationException()

    override fun getComplexDoubleArray(): ComplexDoubleArray = throw UnsupportedOperationException()

    operator fun plusAssign(other: MemoryView<T>) {
        when {
            this is MemoryViewFloatArray && other is MemoryViewFloatArray -> this += other
            this is MemoryViewIntArray && other is MemoryViewIntArray -> this += other
            this is MemoryViewDoubleArray && other is MemoryViewDoubleArray -> this += other
            this is MemoryViewLongArray && other is MemoryViewLongArray -> this += other
            this is MemoryViewShortArray && other is MemoryViewShortArray -> this += other
            this is MemoryViewByteArray && other is MemoryViewByteArray -> this += other
            this is MemoryViewComplexFloatArray && other is MemoryViewComplexFloatArray -> this += other
            this is MemoryViewComplexDoubleArray && other is MemoryViewComplexDoubleArray -> this += other
        }
    }

    open operator fun plusAssign(other: T) {
        when {
            this is MemoryViewFloatArray && other is Float -> this += other
            this is MemoryViewIntArray && other is Int -> this += other
            this is MemoryViewDoubleArray && other is Double -> this += other
            this is MemoryViewLongArray && other is Long -> this += other
            this is MemoryViewShortArray && other is Short -> this += other
            this is MemoryViewByteArray && other is Byte -> this += other
            this is MemoryViewComplexFloatArray && other is ComplexFloat -> this += other
            this is MemoryViewComplexDoubleArray && other is ComplexDouble -> this += other
        }
    }

    operator fun minusAssign(other: MemoryView<T>) {
        when {
            this is MemoryViewFloatArray && other is MemoryViewFloatArray -> this -= other
            this is MemoryViewIntArray && other is MemoryViewIntArray -> this -= other
            this is MemoryViewDoubleArray && other is MemoryViewDoubleArray -> this -= other
            this is MemoryViewLongArray && other is MemoryViewLongArray -> this -= other
            this is MemoryViewShortArray && other is MemoryViewShortArray -> this -= other
            this is MemoryViewByteArray && other is MemoryViewByteArray -> this -= other
            this is MemoryViewComplexFloatArray && other is MemoryViewComplexFloatArray -> this -= other
            this is MemoryViewComplexDoubleArray && other is MemoryViewComplexDoubleArray -> this -= other
        }
    }

    open operator fun minusAssign(other: T) {
        when {
            this is MemoryViewFloatArray && other is Float -> this -= other
            this is MemoryViewIntArray && other is Int -> this -= other
            this is MemoryViewDoubleArray && other is Double -> this -= other
            this is MemoryViewLongArray && other is Long -> this -= other
            this is MemoryViewShortArray && other is Short -> this -= other
            this is MemoryViewByteArray && other is Byte -> this -= other
            this is MemoryViewComplexFloatArray && other is ComplexFloat -> this -= other
            this is MemoryViewComplexDoubleArray && other is ComplexDouble -> this -= other
        }
    }

    operator fun timesAssign(other: MemoryView<T>) {
        when {
            this is MemoryViewFloatArray && other is MemoryViewFloatArray -> this *= other
            this is MemoryViewIntArray && other is MemoryViewIntArray -> this *= other
            this is MemoryViewDoubleArray && other is MemoryViewDoubleArray -> this *= other
            this is MemoryViewLongArray && other is MemoryViewLongArray -> this *= other
            this is MemoryViewShortArray && other is MemoryViewShortArray -> this *= other
            this is MemoryViewByteArray && other is MemoryViewByteArray -> this *= other
            this is MemoryViewComplexFloatArray && other is MemoryViewComplexFloatArray -> this *= other
            this is MemoryViewComplexDoubleArray && other is MemoryViewComplexDoubleArray -> this *= other
        }
    }

    open operator fun timesAssign(other: T) {
        when {
            this is MemoryViewFloatArray && other is Float -> this *= other
            this is MemoryViewIntArray && other is Int -> this *= other
            this is MemoryViewDoubleArray && other is Double -> this *= other
            this is MemoryViewLongArray && other is Long -> this *= other
            this is MemoryViewShortArray && other is Short -> this *= other
            this is MemoryViewByteArray && other is Byte -> this *= other
            this is MemoryViewComplexFloatArray && other is ComplexFloat -> this *= other
            this is MemoryViewComplexDoubleArray && other is ComplexDouble -> this *= other
        }
    }

    operator fun divAssign(other: MemoryView<T>) {
        when {
            this is MemoryViewFloatArray && other is MemoryViewFloatArray -> this /= other
            this is MemoryViewIntArray && other is MemoryViewIntArray -> this /= other
            this is MemoryViewDoubleArray && other is MemoryViewDoubleArray -> this /= other
            this is MemoryViewLongArray && other is MemoryViewLongArray -> this /= other
            this is MemoryViewShortArray && other is MemoryViewShortArray -> this /= other
            this is MemoryViewByteArray && other is MemoryViewByteArray -> this /= other
            this is MemoryViewComplexFloatArray && other is MemoryViewComplexFloatArray -> this /= other
            this is MemoryViewComplexDoubleArray && other is MemoryViewComplexDoubleArray -> this /= other
        }
    }

    open operator fun divAssign(other: T) {
        when {
            this is MemoryViewFloatArray && other is Float -> this /= other
            this is MemoryViewIntArray && other is Int -> this /= other
            this is MemoryViewDoubleArray && other is Double -> this /= other
            this is MemoryViewLongArray && other is Long -> this /= other
            this is MemoryViewShortArray && other is Short -> this /= other
            this is MemoryViewByteArray && other is Byte -> this /= other
            this is MemoryViewComplexFloatArray && other is ComplexFloat -> this /= other
            this is MemoryViewComplexDoubleArray && other is ComplexDouble -> this /= other
        }
    }
}

/**
 * View for [ByteArray].
 */
class MemoryViewByteArray(override val data: ByteArray) : MemoryView<Byte>() {

    override val dtype: DataType = DataType.ByteDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): Byte = data[index]

    override fun set(index: Int, value: Byte) {
        data[index] = value
    }

    override fun getByteArray(): ByteArray = data

    override fun iterator(): Iterator<Byte> = data.iterator()

    override fun copyOf(): MemoryView<Byte> = MemoryViewByteArray(data.copyOf())

    override fun copyInto(destination: MemoryView<Byte>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<Byte> {
        this.data.copyInto(destination.getByteArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewByteArray -> false
        size != other.size -> false
        else -> this.data.contentEquals(other.data)
    }

    override fun hashCode(): Int = (0 until size).fold(1) { acc, r ->
        31 * acc + data[r].hashCode()
    }

    operator fun plusAssign(other: MemoryViewByteArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] + other.data[i]).toByte()
        }
    }

    override operator fun plusAssign(other: Byte) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] + other).toByte()
        }
    }

    operator fun minusAssign(other: MemoryViewByteArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] - other.data[i]).toByte()
        }
    }

    override operator fun minusAssign(other: Byte) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] - other).toByte()
        }
    }

    operator fun timesAssign(other: MemoryViewByteArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] * other.data[i]).toByte()
        }
    }

    override operator fun timesAssign(other: Byte) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] * other).toByte()
        }
    }

    operator fun divAssign(other: MemoryViewByteArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] / other.data[i]).toByte()
        }
    }

    override operator fun divAssign(other: Byte) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] / other).toByte()
        }
    }
}

/**
 * View for [ShortArray].
 */
class MemoryViewShortArray(override val data: ShortArray) : MemoryView<Short>() {
    override val dtype: DataType = DataType.ShortDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): Short = data[index]

    override fun set(index: Int, value: Short) {
        data[index] = value
    }

    override fun getShortArray(): ShortArray = data

    override fun iterator(): Iterator<Short> = data.iterator()

    override fun copyOf(): MemoryView<Short> = MemoryViewShortArray(data.copyOf())

    override fun copyInto(destination: MemoryView<Short>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<Short> {
        this.data.copyInto(destination.getShortArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewShortArray -> false
        size != other.size -> false
        else -> this.data.contentEquals(other.data)
    }

    override fun hashCode(): Int = (0 until size).fold(1) { acc, r ->
        31 * acc + data[r].hashCode()
    }

    operator fun plusAssign(other: MemoryViewShortArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] + other.data[i]).toShort()
        }
    }

    override operator fun plusAssign(other: Short) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] + other).toShort()
        }
    }

    operator fun minusAssign(other: MemoryViewShortArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] - other.data[i]).toShort()
        }
    }

    override operator fun minusAssign(other: Short) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] - other).toShort()
        }
    }

    operator fun timesAssign(other: MemoryViewShortArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] * other.data[i]).toShort()
        }
    }

    override operator fun timesAssign(other: Short) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] * other).toShort()
        }
    }

    operator fun divAssign(other: MemoryViewShortArray) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] / other.data[i]).toShort()
        }
    }

    override operator fun divAssign(other: Short) {
        for (i in this.indices) {
            this.data[i] = (this.data[i] / other).toShort()
        }
    }
}

/**
 * View for [IntArray].
 */
class MemoryViewIntArray(override val data: IntArray) : MemoryView<Int>() {

    override val dtype: DataType = DataType.IntDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): Int = data[index]

    override fun set(index: Int, value: Int) {
        data[index] = value
    }

    override fun getIntArray(): IntArray = data

    override fun iterator(): Iterator<Int> = data.iterator()

    override fun copyOf(): MemoryView<Int> = MemoryViewIntArray(data.copyOf())

    override fun copyInto(destination: MemoryView<Int>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<Int> {
        this.data.copyInto(destination.getIntArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewIntArray -> false
        size != other.size -> false
        else -> this.data.contentEquals(other.data)
    }


    override fun hashCode(): Int = (0 until size).fold(1) { acc, r ->
        31 * acc + data[r].hashCode()
    }

    operator fun plusAssign(other: MemoryViewIntArray) {
        for (i in this.indices) {
            this.data[i] += other.data[i]
        }
    }

    override operator fun plusAssign(other: Int) {
        for (i in this.indices) {
            this.data[i] += other
        }
    }

    operator fun minusAssign(other: MemoryViewIntArray) {
        for (i in this.indices) {
            this.data[i] -= other.data[i]
        }
    }

    override operator fun minusAssign(other: Int) {
        for (i in this.indices) {
            this.data[i] -= other
        }
    }

    operator fun timesAssign(other: MemoryViewIntArray) {
        for (i in this.indices) {
            this.data[i] *= other.data[i]
        }
    }

    override operator fun timesAssign(other: Int) {
        for (i in this.indices) {
            this.data[i] *= other
        }
    }

    operator fun divAssign(other: MemoryViewIntArray) {
        for (i in this.indices) {
            this.data[i] /= other.data[i]
        }
    }

    override operator fun divAssign(other: Int) {
        for (i in this.indices) {
            this.data[i] += other
        }
    }
}

/**
 * View for [LongArray].
 */
class MemoryViewLongArray(override val data: LongArray) : MemoryView<Long>() {

    override val dtype: DataType = DataType.LongDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): Long = data[index]

    override fun set(index: Int, value: Long) {
        data[index] = value
    }

    override fun getLongArray(): LongArray = data

    override fun iterator(): Iterator<Long> = data.iterator()

    override fun copyOf(): MemoryView<Long> = MemoryViewLongArray(data.copyOf())

    override fun copyInto(destination: MemoryView<Long>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<Long> {
        this.data.copyInto(destination.getLongArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewLongArray -> false
        size != other.size -> false
        else -> this.data.contentEquals(other.data)
    }

    override fun hashCode(): Int = (0 until size).fold(1) { acc, r ->
        31 * acc + data[r].hashCode()
    }

    operator fun plusAssign(other: MemoryViewLongArray) {
        for (i in this.indices) {
            this.data[i] += other.data[i]
        }
    }

    override operator fun plusAssign(other: Long) {
        for (i in this.indices) {
            this.data[i] += other
        }
    }

    operator fun minusAssign(other: MemoryViewLongArray) {
        for (i in this.indices) {
            this.data[i] -= other.data[i]
        }
    }

    override operator fun minusAssign(other: Long) {
        for (i in this.indices) {
            this.data[i] -= other
        }
    }

    operator fun timesAssign(other: MemoryViewLongArray) {
        for (i in this.indices) {
            this.data[i] *= other.data[i]
        }
    }

    override operator fun timesAssign(other: Long) {
        for (i in this.indices) {
            this.data[i] *= other
        }
    }

    operator fun divAssign(other: MemoryViewLongArray) {
        for (i in this.indices) {
            this.data[i] /= other.data[i]
        }
    }

    override operator fun divAssign(other: Long) {
        for (i in this.indices) {
            this.data[i] /= other
        }
    }
}

/**
 * View for [FloatArray].
 */
class MemoryViewFloatArray(override val data: FloatArray) : MemoryView<Float>() {

    override val dtype: DataType = DataType.FloatDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): Float = data[index]

    override fun set(index: Int, value: Float) {
        data[index] = value
    }

    override fun getFloatArray(): FloatArray = data

    override fun iterator(): Iterator<Float> = data.iterator()

    override fun copyOf(): MemoryView<Float> = MemoryViewFloatArray(data.copyOf())

    override fun copyInto(destination: MemoryView<Float>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<Float> {
        this.data.copyInto(destination.getFloatArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewFloatArray -> false
        size != other.size -> false
        else -> this.data.contentEquals(other.data)
    }

    override fun hashCode(): Int = (0 until size).fold(1) { acc, r ->
        31 * acc + data[r].hashCode()
    }

    operator fun plusAssign(other: MemoryViewFloatArray) {
        for (i in this.indices) {
            this.data[i] += other.data[i]
        }
    }

    override operator fun plusAssign(other: Float) {
        for (i in this.indices) {
            this.data[i] += other
        }
    }

    operator fun minusAssign(other: MemoryViewFloatArray) {
        for (i in this.indices) {
            this.data[i] -= other.data[i]
        }
    }

    override operator fun minusAssign(other: Float) {
        for (i in this.indices) {
            this.data[i] -= other
        }
    }

    operator fun timesAssign(other: MemoryViewFloatArray) {
        for (i in this.indices) {
            this.data[i] *= other.data[i]
        }
    }

    override operator fun timesAssign(other: Float) {
        for (i in this.indices) {
            this.data[i] *= other
        }
    }

    operator fun divAssign(other: MemoryViewFloatArray) {
        for (i in this.indices) {
            this.data[i] /= other.data[i]
        }
    }

    override operator fun divAssign(other: Float) {
        for (i in this.indices) {
            this.data[i] /= other
        }
    }
}

/**
 * View for [DoubleArray].
 */
class MemoryViewDoubleArray(override val data: DoubleArray) : MemoryView<Double>() {

    override val dtype: DataType = DataType.DoubleDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): Double = data[index]

    override fun set(index: Int, value: Double) {
        data[index] = value
    }

    override fun getDoubleArray(): DoubleArray = data

    override fun iterator(): Iterator<Double> = data.iterator()

    override fun copyOf(): MemoryView<Double> = MemoryViewDoubleArray(data.copyOf())

    override fun copyInto(destination: MemoryView<Double>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<Double> {
        this.data.copyInto(destination.getDoubleArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewDoubleArray -> false
        size != other.size -> false
        else -> this.data.contentEquals(other.data)
    }

    override fun hashCode(): Int = (0 until size).fold(1) { acc, r ->
        31 * acc + data[r].hashCode()
    }

    operator fun plusAssign(other: MemoryViewDoubleArray) {
        for (i in this.indices) {
            this.data[i] += other.data[i]
        }
    }

    override operator fun plusAssign(other: Double) {
        for (i in this.indices) {
            this.data[i] += other
        }
    }

    operator fun minusAssign(other: MemoryViewDoubleArray) {
        for (i in this.indices) {
            this.data[i] -= other.data[i]
        }
    }

    override operator fun minusAssign(other: Double) {
        for (i in this.indices) {
            this.data[i] -= other
        }
    }

    operator fun timesAssign(other: MemoryViewDoubleArray) {
        for (i in this.indices) {
            this.data[i] *= other.data[i]
        }
    }

    override operator fun timesAssign(other: Double) {
        for (i in this.indices) {
            this.data[i] *= other
        }
    }

    operator fun divAssign(other: MemoryViewDoubleArray) {
        for (i in this.indices) {
            this.data[i] /= other.data[i]
        }
    }

    override operator fun divAssign(other: Double) {
        for (i in this.indices) {
            this.data[i] /= other
        }
    }
}

/**
 * View for [ComplexFloatArray].
 */
class MemoryViewComplexFloatArray(override val data: ComplexFloatArray) : MemoryView<ComplexFloat>() {

    override val dtype: DataType = DataType.ComplexFloatDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): ComplexFloat = data[index]

    override fun set(index: Int, value: ComplexFloat) {
        data[index] = value
    }

    override fun getFloatArray(): FloatArray = data.getFlatArray()

    override fun getComplexFloatArray(): ComplexFloatArray = data

    override fun iterator(): Iterator<ComplexFloat> = data.iterator()

    override fun copyOf(): MemoryView<ComplexFloat> = MemoryViewComplexFloatArray(data.copyOf())

    override fun copyInto(destination: MemoryView<ComplexFloat>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<ComplexFloat> {
        this.data.copyInto(destination.getComplexFloatArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewComplexFloatArray -> false
        size != other.size -> false
        else -> this.data == other.data
    }

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + dtype.hashCode()
        result = 31 * result + size
        return result
    }

    operator fun plusAssign(other: MemoryViewComplexFloatArray) {
        for (i in this.indices) {
            this.data[i] += other.data[i]
        }
    }

    override operator fun plusAssign(other: ComplexFloat) {
        for (i in this.indices) {
            this.data[i] += other
        }
    }

    operator fun minusAssign(other: MemoryViewComplexFloatArray) {
        for (i in this.indices) {
            this.data[i] -= other.data[i]
        }
    }

    override operator fun minusAssign(other: ComplexFloat) {
        for (i in this.indices) {
            this.data[i] -= other
        }
    }

    operator fun timesAssign(other: MemoryViewComplexFloatArray) {
        for (i in this.indices) {
            this.data[i] *= other.data[i]
        }
    }

    override operator fun timesAssign(other: ComplexFloat) {
        for (i in this.indices) {
            this.data[i] *= other
        }
    }

    operator fun divAssign(other: MemoryViewComplexFloatArray) {
        for (i in this.indices) {
            this.data[i] /= other.data[i]
        }
    }

    override operator fun divAssign(other: ComplexFloat) {
        for (i in this.indices) {
            this.data[i] /= other
        }
    }
}

/**
 * View for [ComplexDoubleArray].
 */
class MemoryViewComplexDoubleArray(override val data: ComplexDoubleArray) : MemoryView<ComplexDouble>() {

    override val dtype: DataType = DataType.ComplexDoubleDataType

    override var size: Int = data.size

    override var indices: IntRange = data.indices

    override var lastIndex: Int = data.lastIndex

    override fun get(index: Int): ComplexDouble = data[index]

    override fun set(index: Int, value: ComplexDouble) {
        data[index] = value
    }

    override fun getDoubleArray(): DoubleArray = data.getFlatArray()

    override fun getComplexDoubleArray(): ComplexDoubleArray = data

    override fun iterator(): Iterator<ComplexDouble> = data.iterator()

    override fun copyOf(): MemoryView<ComplexDouble> = MemoryViewComplexDoubleArray(data.copyOf())

    override fun copyInto(destination: MemoryView<ComplexDouble>, destinationOffset: Int, startIndex: Int, endIndex: Int): MemoryView<ComplexDouble> {
        this.data.copyInto(destination.getComplexDoubleArray(), destinationOffset, startIndex, endIndex)
        return destination
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other != null && this::class != other::class -> false
        other !is MemoryViewComplexDoubleArray -> false
        size != other.size -> false
        else -> this.data == other.data
    }

    override fun hashCode(): Int {
        var result = data.hashCode()
        result = 31 * result + dtype.hashCode()
        result = 31 * result + size
        return result
    }

    operator fun plusAssign(other: MemoryViewComplexDoubleArray) {
        for (i in this.indices) {
            this.data[i] += other.data[i]
        }
    }

    override operator fun plusAssign(other: ComplexDouble) {
        for (i in this.indices) {
            this.data[i] += other
        }
    }

    operator fun minusAssign(other: MemoryViewComplexDoubleArray) {
        for (i in this.indices) {
            this.data[i] -= other.data[i]
        }
    }

    override operator fun minusAssign(other: ComplexDouble) {
        for (i in this.indices) {
            this.data[i] -= other
        }
    }

    operator fun timesAssign(other: MemoryViewComplexDoubleArray) {
        for (i in this.indices) {
            this.data[i] *= other.data[i]
        }
    }

    override operator fun timesAssign(other: ComplexDouble) {
        for (i in this.indices) {
            this.data[i] *= other
        }
    }

    operator fun divAssign(other: MemoryViewComplexDoubleArray) {
        for (i in this.indices) {
            this.data[i] /= other.data[i]
        }
    }

    override operator fun divAssign(other: ComplexDouble) {
        for (i in this.indices) {
            this.data[i] /= other
        }
    }
}

inline fun <reified T : Any> initMemoryView(size: Int): MemoryView<T> = initMemoryView(size, DataType.ofKClass(T::class))

/**
 * Creates a [MemoryView] based [size] and [dataType].
 */
fun <T> initMemoryView(size: Int, dataType: DataType): MemoryView<T> = when (dataType) {
    DataType.ByteDataType -> MemoryViewByteArray(ByteArray(size))
    DataType.ShortDataType -> MemoryViewShortArray(ShortArray(size))
    DataType.IntDataType -> MemoryViewIntArray(IntArray(size))
    DataType.LongDataType -> MemoryViewLongArray(LongArray(size))
    DataType.FloatDataType -> MemoryViewFloatArray(FloatArray(size))
    DataType.DoubleDataType -> MemoryViewDoubleArray(DoubleArray(size))
    DataType.ComplexFloatDataType -> MemoryViewComplexFloatArray(ComplexFloatArray(size))
    DataType.ComplexDoubleDataType -> MemoryViewComplexDoubleArray(ComplexDoubleArray(size))
} as MemoryView<T>

/**
 * Create a [MemoryView] based [size] and [dataType], where each element will be initialized according
 * to the given [init] function.
 */
fun <T> initMemoryView(size: Int, dataType: DataType, init: (Int) -> T): MemoryView<T> = when (dataType) {
    DataType.ByteDataType -> MemoryViewByteArray(ByteArray(size, init as (Int) -> Byte))
    DataType.ShortDataType -> MemoryViewShortArray(ShortArray(size, init as (Int) -> Short))
    DataType.IntDataType -> MemoryViewIntArray(IntArray(size, init as (Int) -> Int))
    DataType.LongDataType -> MemoryViewLongArray(LongArray(size, init as (Int) -> Long))
    DataType.FloatDataType -> MemoryViewFloatArray(FloatArray(size, init as (Int) -> Float))
    DataType.DoubleDataType -> MemoryViewDoubleArray(DoubleArray(size, init as (Int) -> Double))
    DataType.ComplexFloatDataType -> MemoryViewComplexFloatArray(ComplexFloatArray(size, init as (Int) -> ComplexFloat))
    DataType.ComplexDoubleDataType -> MemoryViewComplexDoubleArray(ComplexDoubleArray(size, init as (Int) -> ComplexDouble))
} as MemoryView<T>


fun <T> List<T>.toViewPrimitiveArray(dataType: DataType): MemoryView<T> = when (dataType) {
    DataType.ByteDataType -> MemoryViewByteArray((this as List<Byte>).toByteArray())
    DataType.ShortDataType -> MemoryViewShortArray((this as List<Short>).toShortArray())
    DataType.IntDataType -> MemoryViewIntArray((this as List<Int>).toIntArray())
    DataType.LongDataType -> MemoryViewLongArray((this as List<Long>).toLongArray())
    DataType.FloatDataType -> MemoryViewFloatArray((this as List<Float>).toFloatArray())
    DataType.DoubleDataType -> MemoryViewDoubleArray((this as List<Double>).toDoubleArray())
    DataType.ComplexFloatDataType -> MemoryViewComplexFloatArray((this as List<ComplexFloat>).toComplexFloatArray())
    DataType.ComplexDoubleDataType -> MemoryViewComplexDoubleArray((this as List<ComplexDouble>).toComplexDoubleArray())
} as MemoryView<T>
