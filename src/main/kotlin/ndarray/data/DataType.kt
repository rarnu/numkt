@file:Suppress("NOTHING_TO_INLINE", "UNNECESSARY_NOT_NULL_ASSERTION")

package com.rarnu.numkt.ndarray.data

import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexDouble32
import com.rarnu.numkt.ndarray.complex.ComplexDouble64
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.DataType.*
import kotlin.reflect.KClass

/**
 * Describes the type of elements stored in a [NDArray].
 *
 * @param nativeCode an integer value of the type. Required to define the type in JNI.
 * @param itemSize size of one ndarray element in bytes.
 * @param clazz [KClass] type.
 *
 * @property ByteDataType byte.
 * @property ShortDataType short.
 * @property IntDataType int.
 * @property LongDataType long.
 * @property FloatDataType float.
 * @property DoubleDataType double.
 * @property ComplexFloatDataType complex float.
 * @property ComplexDoubleDataType complex double.
 */
enum class DataType(val nativeCode: Int, val itemSize: Int, val clazz: KClass<out Any>) {

    ByteDataType(1, 1, Byte::class), ShortDataType(2, 2, Short::class), IntDataType(3, 4, Int::class), LongDataType(4, 8, Long::class), FloatDataType(5, 4, Float::class), DoubleDataType(6, 8, Double::class), ComplexFloatDataType(7, 8, ComplexFloat::class), ComplexDoubleDataType(
        8,
        16,
        ComplexDouble::class
    );

    fun isNumber(): Boolean = when (nativeCode) {
        1, 2, 3, 4, 5, 6 -> true
        else -> false
    }

    fun isComplex(): Boolean = !isNumber()

    companion object {

        /**
         * Returns [DataType] by [nativeCode].
         */
        fun of(i: Int): DataType = when (i) {
            1 -> ByteDataType
            2 -> ShortDataType
            3 -> IntDataType
            4 -> LongDataType
            5 -> FloatDataType
            6 -> DoubleDataType
            7 -> ComplexFloatDataType
            8 -> ComplexDoubleDataType
            else -> throw IllegalStateException("One of the primitive types indexes was expected, got $i")
        }


        /**
         * Returns [DataType] by class of [element].
         */
        inline fun <T> of(element: T): DataType {
            element ?: throw IllegalStateException("Element is null cannot find type")
            return when (element!!::class) {
                Byte::class -> ByteDataType
                Short::class -> ShortDataType
                Int::class -> IntDataType
                Long::class -> LongDataType
                Float::class -> FloatDataType
                Double::class -> DoubleDataType
                ComplexFloat::class -> ComplexFloatDataType
                ComplexDouble64::class -> ComplexDoubleDataType
                ComplexDouble32::class -> ComplexDoubleDataType
                else -> throw IllegalStateException("One of the primitive types was expected, got ${element!!::class.simpleName}")
            }
        }


        /**
         * Returns [DataType] by [KClass] of [type]. [T] is `reified` type.
         */
        inline fun <reified T : Any> ofKClass(type: KClass<out T>): DataType = when (type) {
            Byte::class -> ByteDataType
            Short::class -> ShortDataType
            Int::class -> IntDataType
            Long::class -> LongDataType
            Float::class -> FloatDataType
            Double::class -> DoubleDataType
            ComplexFloat::class -> ComplexFloatDataType
            ComplexDouble::class -> ComplexDoubleDataType
            else -> throw IllegalStateException("One of the primitive types was expected, got ${type.simpleName}")
        }
    }

    override fun toString(): String = "DataType(nativeCode=$nativeCode, itemSize=$itemSize, class=$clazz)"

}