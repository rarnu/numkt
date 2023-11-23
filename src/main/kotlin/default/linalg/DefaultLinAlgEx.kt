package com.rarnu.numkt.default.linalg

import com.rarnu.numkt.api.KEEngineType
import com.rarnu.numkt.api.NativeEngineType
import com.rarnu.numkt.api.linalg.LinAlgEx
import com.rarnu.numkt.api.linalg.Norm
import com.rarnu.numkt.default.DefaultEngineFactory
import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.*

object DefaultLinAlgEx : LinAlgEx {

    private val ktLinAlgEx = DefaultEngineFactory.getEngine(KEEngineType).getLinAlg().linAlgEx

    private val natLinAlgEx = DefaultEngineFactory.getEngine(NativeEngineType).getLinAlg().linAlgEx

    override fun <T : Number> inv(mat: MultiArray<T, D2>): NDArray<Double, D2> = natLinAlgEx.inv(mat)

    override fun invF(mat: MultiArray<Float, D2>): NDArray<Float, D2> = natLinAlgEx.invF(mat)

    override fun <T : Complex> invC(mat: MultiArray<T, D2>): NDArray<T, D2> = natLinAlgEx.invC(mat)

    override fun <T : Number, D : Dim2> solve(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<Double, D> = natLinAlgEx.solve(a, b)

    override fun <D : Dim2> solveF(a: MultiArray<Float, D2>, b: MultiArray<Float, D>): NDArray<Float, D> = natLinAlgEx.solveF(a, b)

    override fun <T : Complex, D : Dim2> solveC(a: MultiArray<T, D2>, b: MultiArray<T, D>): NDArray<T, D> = natLinAlgEx.solveC(a, b)

    override fun normF(mat: MultiArray<Float, D2>, norm: Norm): Float = natLinAlgEx.normF(mat, norm)

    override fun norm(mat: MultiArray<Double, D2>, norm: Norm): Double = natLinAlgEx.norm(mat, norm)

    override fun <T : Number> qr(mat: MultiArray<T, D2>): Pair<D2Array<Double>, D2Array<Double>> = natLinAlgEx.qr(mat)

    override fun qrF(mat: MultiArray<Float, D2>): Pair<D2Array<Float>, D2Array<Float>> = natLinAlgEx.qrF(mat)

    override fun <T : Complex> qrC(mat: MultiArray<T, D2>): Pair<D2Array<T>, D2Array<T>> = natLinAlgEx.qrC(mat)

    override fun <T : Number> plu(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D2Array<Double>, D2Array<Double>> = natLinAlgEx.plu(mat)

    override fun pluF(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D2Array<Float>, D2Array<Float>> = natLinAlgEx.pluF(mat)

    override fun <T : Complex> pluC(mat: MultiArray<T, D2>): Triple<D2Array<T>, D2Array<T>, D2Array<T>> = natLinAlgEx.pluC(mat)

    override fun svdF(mat: MultiArray<Float, D2>): Triple<D2Array<Float>, D1Array<Float>, D2Array<Float>> = ktLinAlgEx.svdF(mat)

    override fun <T : Number> svd(mat: MultiArray<T, D2>): Triple<D2Array<Double>, D1Array<Double>, D2Array<Double>> = ktLinAlgEx.svd(mat)

    override fun <T : Complex> svdC(mat: MultiArray<T, D2>): Triple<D2Array<T>, D1Array<T>, D2Array<T>> = ktLinAlgEx.svdC(mat)

    override fun <T : Number> eig(mat: MultiArray<T, D2>): Pair<D1Array<ComplexDouble>, D2Array<ComplexDouble>> = ktLinAlgEx.eig(mat)

    override fun eigF(mat: MultiArray<Float, D2>): Pair<D1Array<ComplexFloat>, D2Array<ComplexFloat>> = ktLinAlgEx.eigF(mat)

    override fun <T : Complex> eigC(mat: MultiArray<T, D2>): Pair<D1Array<T>, D2Array<T>> = ktLinAlgEx.eigC(mat)

    override fun <T : Number> eigVals(mat: MultiArray<T, D2>): D1Array<ComplexDouble> = ktLinAlgEx.eigVals(mat)

    override fun eigValsF(mat: MultiArray<Float, D2>): D1Array<ComplexFloat> = ktLinAlgEx.eigValsF(mat)

    override fun <T : Complex> eigValsC(mat: MultiArray<T, D2>): D1Array<T> = ktLinAlgEx.eigValsC(mat)

    override fun <T : Number> dotMM(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2> = when (a.dtype) {
        DataType.FloatDataType, DataType.DoubleDataType -> natLinAlgEx.dotMM(a, b)
        else -> ktLinAlgEx.dotMM(a, b)
    }

    override fun <T : Complex> dotMMComplex(a: MultiArray<T, D2>, b: MultiArray<T, D2>): NDArray<T, D2> = natLinAlgEx.dotMMComplex(a, b)

    override fun <T : Number> dotMV(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1> = when (a.dtype) {
        DataType.FloatDataType, DataType.DoubleDataType -> natLinAlgEx.dotMV(a, b)
        else -> ktLinAlgEx.dotMV(a, b)
    }

    override fun <T : Complex> dotMVComplex(a: MultiArray<T, D2>, b: MultiArray<T, D1>): NDArray<T, D1> = natLinAlgEx.dotMVComplex(a, b)

    override fun <T : Number> dotVV(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T = when (a.dtype) {
        DataType.FloatDataType, DataType.DoubleDataType -> natLinAlgEx.dotVV(a, b)
        else -> ktLinAlgEx.dotVV(a, b)
    }

    override fun <T : Complex> dotVVComplex(a: MultiArray<T, D1>, b: MultiArray<T, D1>): T = natLinAlgEx.dotVVComplex(a, b)
}