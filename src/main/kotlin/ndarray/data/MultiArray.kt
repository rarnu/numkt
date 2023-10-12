package com.rarnu.numkt.ndarray.data

/**
 *  A generic ndarray. Methods in this interface support only read-only access to the ndarray.
 *
 *  @property data [MemoryView].
 *  @property offset Offset from the start of a ndarray's data.
 *  @property shape [IntArray] of a ndarray dimensions.
 *  @property strides [IntArray] indices to step in each dimension when iterating a ndarray.
 *  @property size number of elements in a ndarray.
 *  @property dtype [DataType] of an ndarray's data.
 *  @property dim [Dimension].
 *  @property base Base array if [data] is taken from other array. Otherwise - null.
 *  @property consistent indicates whether the array data is homogeneous.
 *  @property indices indices for a one-dimensional ndarray.
 *  @property multiIndices indices for a n-dimensional ndarray.
 */
interface MultiArray<T, D : Dimension> {
    val data: ImmutableMemoryView<T>
    val offset: Int
    val shape: IntArray
    val strides: IntArray
    val size: Int
    val dtype: DataType get() = data.dtype
    val dim: D
    val base: MultiArray<T, out Dimension>?
    val consistent: Boolean
    val indices: IntRange
    val multiIndices: MultiIndexProgression

    /**
     * Returns `true` if the array contains only one element, otherwise `false`.
     */
    fun isScalar(): Boolean

    /**
     * Returns `true` if this ndarray is empty.
     */
    fun isEmpty(): Boolean

    /**
     * Returns `true` if this ndarray is not empty.
     */
    fun isNotEmpty(): Boolean

    /**
     * Returns new [MultiArray] which is a copy of the original ndarray.
     */
    fun copy(): MultiArray<T, D>

    /**
     * Returns new [MultiArray] which is a deep copy of the original ndarray.
     */
    fun deepCopy(): MultiArray<T, D>

    operator fun iterator(): Iterator<T>

    /**
     * Returns new one-dimensional ndarray which is a copy of the original ndarray.
     */
    fun flatten(): MultiArray<T, D1>

    /**
     * Returns a ndarray with a new ([dim1]) shape without changing data.
     */
    fun reshape(dim1: Int): MultiArray<T, D1>

    /**
     * Returns a ndarray with a new ([dim1], [dim2]) shape without changing data.
     */
    fun reshape(dim1: Int, dim2: Int): MultiArray<T, D2>

    /**
     * Returns a ndarray with a new ([dim1], [dim2], [dim3]) shape without changing data.
     */
    fun reshape(dim1: Int, dim2: Int, dim3: Int): MultiArray<T, D3>

    /**
     * Returns a ndarray with a new ([dim1], [dim2], [dim3], [dim4]) shape without changing data.
     */
    fun reshape(dim1: Int, dim2: Int, dim3: Int, dim4: Int): MultiArray<T, D4>

    /**
     * Returns a ndarray with a new ([dim1], [dim2], [dim3], [dim4], [dims]) shape without changing data.
     */
    fun reshape(dim1: Int, dim2: Int, dim3: Int, dim4: Int, vararg dims: Int): MultiArray<T, DN>

    /**
     * Reverse or permute the [axes] of an array.
     */
    fun transpose(vararg axes: Int): MultiArray<T, D>

    /**
     * Returns a ndarray with all axes removed equal to one.
     */
    fun squeeze(vararg axes: Int): MultiArray<T, DN>

    /**
     * Returns a new ndarray with a dimension of size one inserted at the specified [axes].
     */
    fun unsqueeze(vararg axes: Int): MultiArray<T, DN>

    /**
     * Concatenates this ndarray with [other].
     */
    infix fun cat(other: MultiArray<T, D>): NDArray<T, D>

    /**
     * Concatenates this ndarray with [other] along the specified [axis].
     */
    fun cat(other: MultiArray<T, D>, axis: Int = 0): NDArray<T, D>

    /**
     * Concatenates this ndarray with a list of [other] ndarrays.
     */
    fun cat(other: List<MultiArray<T, D>>, axis: Int = 0): NDArray<T, D>
}