@file:Suppress("DuplicatedCode")

package com.rarnu.numkt.ndarray.complex

class ComplexFloatArray(val size: Int = 0) {

    private val _size: Int = size * 2

    private val data: FloatArray = FloatArray(_size)

    constructor(size: Int, init: (Int) -> ComplexFloat) : this(size) {
        for (i in 0 until size) {
            val (re, im) = init(i)
            val index = i * 2
            this.data[index] = re
            this.data[index + 1] = im
        }
    }

    operator fun get(index: Int): ComplexFloat {
        checkElementIndex(index, size)
        val i = index shl 1
        return ComplexFloat(data[i], data[i + 1])
    }

    operator fun set(index: Int, value: ComplexFloat) {
        checkElementIndex(index, size)
        val i = index shl 1
        data[i] = value.re
        data[i + 1] = value.im
    }

    fun getFlatArray(): FloatArray = data

    /** Creates an iterator over the elements of the array. */
    operator fun iterator(): ComplexFloatIterator = iterator(this)

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other is ComplexFloatArray -> this.contentEquals(other)
        else -> false
    }

    override fun hashCode(): Int = this.contentHashCode()

    override fun toString(): String {
        val sb = StringBuilder(2 + _size * 3)
        sb.append("[")
        var i = 0
        while (i < _size) {
            if (i > 0) sb.append(", ")
            sb.append("${data[i]} + ${data[++i]}i")
            i++
        }
        sb.append("]")
        return sb.toString()
    }
}

class ComplexDoubleArray(val size: Int = 0) {

    private val _size: Int = size * 2

    private val data: DoubleArray = DoubleArray(this._size)

    constructor(size: Int, init: (Int) -> ComplexDouble) : this(size) {
        for (i in 0 until size) {
            val (re, im) = init(i)
            val index = i * 2
            this.data[index] = re
            this.data[index + 1] = im
        }
    }

    operator fun get(index: Int): ComplexDouble {
        checkElementIndex(index, size)
        val i = index shl 1
        return ComplexDouble(data[i], data[i + 1])
    }

    operator fun set(index: Int, value: ComplexDouble) {
        checkElementIndex(index, size)
        val i = index shl 1
        data[i] = value.re
        data[i + 1] = value.im
    }

    fun getFlatArray(): DoubleArray = data

    /** Creates an iterator over the elements of the array. */
    operator fun iterator(): ComplexDoubleIterator = iterator(this)

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other is ComplexDoubleArray -> this.contentEquals(other)
        else -> false
    }

    override fun hashCode(): Int = this.contentHashCode()

    override fun toString(): String {
        val sb = StringBuilder(2 + _size * 3)
        sb.append("[")
        var i = 0
        while (i < _size) {
            if (i > 0) sb.append(", ")
            sb.append("${data[i]} + ${data[++i]}i")
            i++
        }
        sb.append("]")
        return sb.toString()
    }
}

private fun checkElementIndex(index: Int, size: Int) {
    if (index < 0 || index >= size) throw IndexOutOfBoundsException("index: $index, size: $size")
}