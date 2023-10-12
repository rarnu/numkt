package com.rarnu.numkt.ndarray.data

/**
 * Multidimensional index.
 *
 * @param first a start array indices.
 * @param last a final array indices.
 * @param step index traversal step, can be negative.
 */
class MultiIndexProgression(val first: IntArray, val last: IntArray, val step: Int = 1) {

    init {
        require(step != 0) { "Step must be non-zero." }
        require(step != Int.MIN_VALUE) { "Step must be greater than Int.MIN_VALUE to avoid overflow on negation." }
        require(first.size == last.size) { "Sizes first and last must be identical." }
    }

    val reverse: MultiIndexProgression by lazy { last downTo first }

    operator fun iterator(): Iterator<IntArray> = MultiIndexIterator(first, last, step)

    override fun equals(other: Any?): Boolean = other is MultiIndexProgression && (first.contentEquals(other.first) && last.contentEquals(other.last))

    override fun hashCode(): Int = (first + last).hashCode()

    override fun toString(): String = "${first.joinToString(prefix = "(", postfix = ")")}..${last.joinToString(prefix = "(", postfix = ")")}"

}

internal class MultiIndexIterator(first: IntArray, last: IntArray, private val step: Int) : Iterator<IntArray> {
    private val startElement: IntArray = first.copyOf()
    private val finalElement: IntArray = last.copyOf()
    private var hasNext: Boolean = if (step > 0) first <= last else first >= last

    private val next: IntArray = if (hasNext) first.copyOf().apply { this[lastIndex] -= step } else finalElement

    override fun hasNext(): Boolean = hasNext

    override fun next(): IntArray {
        if (step > 0) {
            next += step
            if (next >= finalElement) {
                if (!hasNext) throw NoSuchElementException()
                hasNext = false
            }
        } else {
            next -= -step
            if (next <= finalElement) {
                if (!hasNext) throw NoSuchElementException()
                hasNext = false
            }
        }
        return next
    }

    private operator fun IntArray.plusAssign(value: Int) {
        for (i in this.size - 1 downTo 0) {
            val t = this[i] + value
            if (t > finalElement[i] && i != 0) {
                this[i] = startElement[i]
            } else {
                this[i] = t
                break
            }
        }
    }

    private operator fun IntArray.minusAssign(value: Int) {
        for (i in this.size - 1 downTo 0) {
            val t = this[i] - value
            if (t < finalElement[i] && i != 0) {
                this[i] = startElement[i]
            } else {
                this[i] = t
                break
            }
        }
    }

    private operator fun IntArray.compareTo(other: IntArray): Int = when {
        this === other || (this.isEmpty() && other.isEmpty()) -> 0
        this.isEmpty() -> -1
        other.isEmpty() -> 1
        else -> {
            var ret = 0
            for (index in this.indices) {
                if (this[index] < other[index]) {
                    ret = -1
                    break
                }
                if (this[index] > other[index]) {
                    ret = 1
                    break
                }
            }
            ret
        }
    }

}

/**
 * Returns a multidimensional index based on given arrays.
 */
operator fun IntArray.rangeTo(other: IntArray): MultiIndexProgression = MultiIndexProgression(this, other)

/**
 * Returns a multidimensional index with a given [step].
 */
infix fun MultiIndexProgression.step(step: Int): MultiIndexProgression = MultiIndexProgression(first, last, step)

/**
 * Returns multidimensional index from highest to lowest in the step of -1.
 */
infix fun IntArray.downTo(to: IntArray): MultiIndexProgression = MultiIndexProgression(this, to, -1)


inline fun MultiIndexProgression.forEach(operation: (IntArray) -> Unit) {
    for (element in this) {
        operation(element)
    }
}
