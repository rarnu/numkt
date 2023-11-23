package com.rarnu.numkt.default.math

import com.rarnu.numkt.api.NativeEngineType
import com.rarnu.numkt.api.math.MathEx
import com.rarnu.numkt.default.DefaultEngineFactory
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.data.Dimension
import com.rarnu.numkt.ndarray.data.MultiArray
import com.rarnu.numkt.ndarray.data.NDArray

object DefaultMathEx : MathEx {

    private val natMathEx = DefaultEngineFactory.getEngine(NativeEngineType).getMath().mathEx

    override fun <T : Number, D : Dimension> exp(a: MultiArray<T, D>): NDArray<Double, D> = natMathEx.exp(a)
    override fun <D : Dimension> expF(a: MultiArray<Float, D>): NDArray<Float, D> = natMathEx.expF(a)
    override fun <D : Dimension> expCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = natMathEx.expCF(a)
    override fun <D : Dimension> expCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = natMathEx.expCD(a)

    override fun <T : Number, D : Dimension> log(a: MultiArray<T, D>): NDArray<Double, D> = natMathEx.log(a)
    override fun <D : Dimension> logF(a: MultiArray<Float, D>): NDArray<Float, D> = natMathEx.logF(a)
    override fun <D : Dimension> logCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = natMathEx.logCF(a)
    override fun <D : Dimension> logCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = natMathEx.logCD(a)

    override fun <T : Number, D : Dimension> sin(a: MultiArray<T, D>): NDArray<Double, D> = natMathEx.sin(a)
    override fun <D : Dimension> sinF(a: MultiArray<Float, D>): NDArray<Float, D> = natMathEx.sinF(a)
    override fun <D : Dimension> sinCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = natMathEx.sinCF(a)
    override fun <D : Dimension> sinCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = natMathEx.sinCD(a)

    override fun <T : Number, D : Dimension> cos(a: MultiArray<T, D>): NDArray<Double, D> = natMathEx.cos(a)
    override fun <D : Dimension> cosF(a: MultiArray<Float, D>): NDArray<Float, D> = natMathEx.cosF(a)
    override fun <D : Dimension> cosCF(a: MultiArray<ComplexFloat, D>): NDArray<ComplexFloat, D> = natMathEx.cosCF(a)
    override fun <D : Dimension> cosCD(a: MultiArray<ComplexDouble, D>): NDArray<ComplexDouble, D> = natMathEx.cosCD(a)
}