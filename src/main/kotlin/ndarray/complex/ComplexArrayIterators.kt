package com.rarnu.numkt.ndarray.complex

private class ArrayComplexFloatIterator(private val array: ComplexFloatArray) : ComplexFloatIterator() {

    private var index = 0

    override fun hasNext(): Boolean = index < array.size

    override fun nextComplexFloat(): ComplexFloat = try {
        array[index++]
    } catch (e: IndexOutOfBoundsException) {
        index -= 1; throw NoSuchElementException(e.message)
    }
}

private class ArrayComplexDoubleIterator(private val array: ComplexDoubleArray) : ComplexDoubleIterator() {

    private var index = 0

    override fun hasNext(): Boolean = index < array.size

    override fun nextComplexDouble(): ComplexDouble = try {
        array[index++]
    } catch (e: IndexOutOfBoundsException) {
        index -= 1; throw NoSuchElementException(e.message)
    }
}

fun iterator(array: ComplexFloatArray): ComplexFloatIterator = ArrayComplexFloatIterator(array)
fun iterator(array: ComplexDoubleArray): ComplexDoubleIterator = ArrayComplexDoubleIterator(array)