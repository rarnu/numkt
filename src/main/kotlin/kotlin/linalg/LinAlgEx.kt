@file:Suppress("UNCHECKED_CAST")

package com.rarnu.numkt.kotlin.linalg

import com.rarnu.numkt.api.Numkt
import com.rarnu.numkt.api.identity
import com.rarnu.numkt.api.linalg.LinAlgEx
import com.rarnu.numkt.api.linalg.Norm
import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.*
import com.rarnu.numkt.ndarray.operations.CopyStrategy
import com.rarnu.numkt.ndarray.operations.toType

internal object KELinAlgEx : LinAlgEx {
    override fun <T : Number> inv(mat: MultiArray<T, D2>): NDArray<Double, D2> = solveCommon(mat, Numkt.identity(mat.shape[0], mat.dtype), mat.dtype)

    override fun invF(mat: MultiArray<Float, D2>): NDArray<Float, D2> = solveCommon(mat, Numkt.identity(mat.shape[0], mat.dtype), mat.dtype)

    override fun <T : Complex> invC(mat: MultiArray<T, D2>): NDArray<T, D2> = solveCommon(mat, Numkt.identity(mat.shape[0], mat.dtype), mat.dtype)

    override fun <T : Number, D : Dim2> solve(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<Double, D> = solveCommon(a, b, DataType.DoubleDataType)

    override fun <D : Dim2> solveF(a: MultiArray<Float, D2>, b: MultiArray<Float, D>): NDArray<Float, D> = solveCommon(a, b, DataType.FloatDataType)

    override fun <T : Complex, D : Dim2> solveC(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<T, D> = solveCommon(a, b, a.dtype)

    private fun <T, O : Any, D : Dim2> solveCommon(a: MultiArray<T, D2>, b: MultiArray<T, D>, dtype: DataType): NDArray<O, D> {
        requireSquare(a.shape)
        requireDotShape(a.shape, b.shape)

        val a2 = a.toType<T, O, D2>(dtype, CopyStrategy.MEANINGFUL)
        val bTyped = if (dtype == DataType.DoubleDataType) b.toType<T, O, D>(dtype, CopyStrategy.MEANINGFUL) else b
        val b2 = (if (bTyped.dim.d == 2) bTyped else bTyped.reshape(bTyped.shape[0], 1)) as MultiArray<T, D2>

        val ans = when (dtype) {
            DataType.DoubleDataType -> solveDouble(a2 as D2Array<Double>, b2 as D2Array<Double>)
            DataType.FloatDataType -> solveFloat(a2 as D2Array<Float>, b2 as D2Array<Float>)
            DataType.ComplexDoubleDataType -> solveComplexDouble(a2 as D2Array<ComplexDouble>, b2 as D2Array<ComplexDouble>)
            DataType.ComplexFloatDataType -> solveComplexFloat(a2 as D2Array<ComplexFloat>, b2 as D2Array<ComplexFloat>)
            else -> throw UnsupportedOperationException()
        }
        return (if (b.dim.d == 2) ans else ans.reshape(ans.shape[0])) as NDArray<O, D>
    }

    override fun normF(mat: MultiArray<Float, D2>, norm: Norm): Float = when (norm) {
        Norm.Fro -> normFro(mat.data.getFloatArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1], 2)
        Norm.N1 -> norm1(mat.data.getFloatArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1])
        Norm.Max -> normMax(mat.data.getFloatArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1])
        Norm.Inf -> normInf(mat.data.getFloatArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1])
    }

    override fun norm(mat: MultiArray<Double, D2>, norm: Norm): Double = when (norm) {
        Norm.Fro -> normFro(mat.data.getDoubleArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1], 2)
        Norm.N1 -> norm1(mat.data.getDoubleArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1])
        Norm.Max -> normMax(mat.data.getDoubleArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1])
        Norm.Inf -> normInf(mat.data.getDoubleArray(), mat.offset, mat.strides, mat.shape[0], mat.shape[1])
    }

    override fun <T : Number> qr(mat: MultiArray<T, D2>): Pair<D2Array<Double>, D2Array<Double>> = qrDouble(mat.toType())

    override fun qrF(mat: MultiArray<Float, D2>): Pair<D2Array<Float>, D2Array<Float>> = qrFloat(mat)

    override fun <T : Complex> qrC(mat: MultiArray<T, D2>): Pair<D2Array<T>, D2Array<T>> = when (mat.dtype) {
        DataType.ComplexFloatDataType -> qrComplexFloat(mat as MultiArray<ComplexFloat, D2>)
        DataType.ComplexDoubleDataType -> qrComplexDouble(mat as MultiArray<ComplexDouble, D2>)
        else -> throw UnsupportedOperationException("Matrix should be complex")
    } as Pair<D2Array<T>, D2Array<T>>

    override fun <T : Number> plu(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D2Array<Double>, D2Array<Double>> = pluCommon(mat, DataType.DoubleDataType)

    override fun pluF(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D2Array<Float>, D2Array<Float>> = pluCommon(mat, DataType.FloatDataType)

    override fun <T : Complex> pluC(mat: MultiArray<T, D2>): Triple<D2Array<T>, D2Array<T>, D2Array<T>> = pluCommon(mat, mat.dtype)

    override fun svdF(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D1Array<Float>, D2Array<Float>> {
        throw RuntimeException("svdF is implemented in Native Engine")
    }

    override fun <T : Number> svd(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D1Array<Double>, D2Array<Double>> {
        throw RuntimeException("svd is implemented in Native Engine")
    }

    override fun <T : Complex> svdC(mat: MultiArray<T, D2>): Triple<D2Array<T>, D1Array<T>, D2Array<T>> {
        throw RuntimeException("svdC is implemented in Native Engine")
    }

    override fun <T : Number> eig(mat: MultiArray<T, D2>): Pair<D1Array<ComplexDouble>, D2Array<ComplexDouble>> {
        throw RuntimeException("eig is implemented in Native Engine")
    }

    override fun eigF(mat: MultiArray<Float, D2>): Pair<D1Array<ComplexFloat>, D2Array<ComplexFloat>> {
        throw RuntimeException("eigF is implemented in Native Engine")
    }

    override fun <T : Complex> eigC(mat: MultiArray<T, D2>): Pair<D1Array<T>, D2Array<T>> {
        throw RuntimeException("eigC is implemented in Native Engine")
    }

    override fun <T : Number> eigVals(mat: MultiArray<T, D2>): D1Array<ComplexDouble> = eigenValuesCommon(mat, DataType.ComplexDoubleDataType)

    override fun eigValsF(mat: MultiArray<Float, D2>): D1Array<ComplexFloat> = eigenValuesCommon(mat, DataType.ComplexFloatDataType)

    override fun <T : Complex> eigValsC(mat: MultiArray<T, D2>): D1Array<T> = eigenValuesCommon(mat, mat.dtype)

    private fun <T, O : Any> pluCommon(mat: MultiArray<T, D2>, dtype: DataType): Triple<D2Array<O>, D2Array<O>, D2Array<O>> {
        val a = mat.toType<T, O, D2>(dtype, CopyStrategy.MEANINGFUL)
        val (perm, l, u) = pluCompressed(a)

        val p = Numkt.identity<O>(a.shape[0], dtype)

        for (i in perm.indices.reversed()) {
            if (perm[i] != 0) {
                for (k in 0 until p.shape[1]) {
                    p[i, k] = p[i + perm[i], k].also { p[i + perm[i], k] = p[i, k] }
                }
            }
        }

        return Triple(p, l, u)
    }

    override fun <T : Number> dotMM(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2> = dotMatrix(a, b)

    override fun <T : Complex> dotMMComplex(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2> = dotMatrixComplex(a, b)

    override fun <T : Number> dotMV(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1> = dotMatrixToVector(a, b)

    override fun <T : Complex> dotMVComplex(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1> = dotMatrixToVectorComplex(a, b)

    override fun <T : Number> dotVV(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T = dotVecToVec(a, b)

    override fun <T : Complex> dotVVComplex(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T = dotVecToVecComplex(a, b)
}