package com.rarnu.numkt.test.native.linalg

import com.rarnu.numkt.api.*
import com.rarnu.numkt.api.linalg.*
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.complex.i
import com.rarnu.numkt.ndarray.data.get
import com.rarnu.numkt.ndarray.data.rangeTo
import com.rarnu.numkt.ndarray.operations.minus
import com.rarnu.numkt.native.*
import com.rarnu.numkt.native.linalg.NativeLinAlg
import com.rarnu.numkt.native.linalg.NativeLinAlgEx
import com.rarnu.numkt.ndarray.complex.plus
import com.rarnu.numkt.test.native.*
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class NativeLinAlgTest {

    private lateinit var data: DataStructure

    @BeforeTest
    fun load() {
        libLoader("numkt_jni").load()
        data = DataStructure(42)
    }

    @Test
    fun solveLinearSystemF() {
        val expected = Numkt.ndarray(Numkt[Numkt[4.1391945f, 1.2361444f, 4.4088345f], Numkt[-3.0071893f, 0.13484901f, -3.9121897f], Numkt[3.2885208f, -0.04077824f, 4.3054614f], Numkt[0.7955365f, 0.57545465f, 0.42709854f], Numkt[-11.024394f, -1.9956491f, -11.173507f]])
        val (a, b) = data.getFloatMM(5, 5, 5, 3)
        assertFloatingNDArray(expected, NativeLinAlg.solve(a, b), epsilon = 1e5f)
    }

    @Test
    fun solveLinearSystemD() {
        val expected = Numkt.ndarray(Numkt[Numkt[4.1391945, 1.2361444, 4.4088345], Numkt[-3.0071893, 0.13484901, -3.9121897], Numkt[3.2885208, -0.04077824, 4.3054614], Numkt[0.7955365, 0.57545465, 0.42709854], Numkt[-11.024394, -1.9956491, -11.173507]])
        val (a, b) = data.getDoubleMM(5, 5, 5, 3)
        assertFloatingNDArray(expected, NativeLinAlg.solve(a, b), epsilon = 1e6)
    }

    @Test
    fun solveLinearSystemComplex() {
        val expected = Numkt.ndarray(Numkt[Numkt[ComplexDouble(-0.30547825, -0.61681154), ComplexDouble(0.41432816, -1.46046786), ComplexDouble(-0.35100211, 0.27240141)], Numkt[ComplexDouble(-0.14282777, 0.52435108), ComplexDouble(-0.14739684, 0.72480181), ComplexDouble(0.75653133, -0.97962391)], Numkt[ComplexDouble(1.15623785, -0.11361717), ComplexDouble(0.65161407, 0.47386083), ComplexDouble(0.51721532, 0.41166838)]])
        val (a, b) = data.getComplexDoubleMM(3, 3, 3, 3)
        assertComplexFloatingNDArray(expected, NativeLinAlg.solve(a, b), epsilon = 1e8)
    }

    @Test
    fun simpleVectorDotTest() {
        val a = Numkt.ndarray(Numkt[1.0, 1.0])
        val b = Numkt.ndarray(Numkt[Numkt[1.0, 2.0], Numkt[3.0, 4.0]])
        assertEquals(3.0, b[0] dot a)
        assertEquals(7.0, b[1] dot a)
    }

    @Test
    fun matrixMatrixDotTestD() {
        val expected = Numkt.ndarray(Numkt[Numkt[1.0853811780469889, 0.6321441231331913, 0.46677507285707914, 0.4892609866360924], Numkt[0.833116624067087, 0.7287671731075991, 0.7973174517659147, 0.8294695934205714], Numkt[0.5426264067593305, 0.4939259489941979, 0.5413707808847182, 0.5183069608507607], Numkt[1.048984456958365, 0.7710348889066437, 0.7189440755132327, 0.6763964597209662]])
        val (matrix1, matrix2) = data.getDoubleMM(4, 3, 3, 4)
        val actual = NativeLinAlg.dot(matrix1, matrix2)
        assertFloatingNDArray(expected, actual)
    }

    @Test
    fun matrixMatrixDotTestComplexDouble() {
        val expected = Numkt.ndarray(Numkt[Numkt[ComplexDouble(-11.0, 79.0), ComplexDouble(-7.0, 59.0), ComplexDouble(-3.0, +39.0)], Numkt[ComplexDouble(-9.0, 111.0), ComplexDouble(-5.0, 83.0), ComplexDouble(-1.0, 55.0)], Numkt[ComplexDouble(-7.0, 143.0), ComplexDouble(-3.0, 107.0), ComplexDouble(1.0, 71.0)]])
        val matrix1 = Numkt.ndarray(Numkt[Numkt[ComplexDouble(1, 2), ComplexDouble(3, 4)], Numkt[ComplexDouble(2, 3), ComplexDouble(4, 5)], Numkt[ComplexDouble(3, 4), ComplexDouble(5, 6)]])
        val matrix2 = Numkt.ndarray(Numkt[Numkt[ComplexDouble(9, 8), ComplexDouble(7, 6), ComplexDouble(5, 4)], Numkt[ComplexDouble(8, 7), ComplexDouble(6, 5), ComplexDouble(4, 3)]])
        val actual = NativeLinAlg.dot(matrix1, matrix2)
        assertEquals(expected, actual)
    }

    @Test
    fun matrixMatrixDotTestF() {
        val expected = Numkt.ndarray(Numkt[Numkt[0.8819745f, 0.64614516f, 0.7936589f, 0.5490592f], Numkt[0.543343f, 0.8133113f, 0.2793616f, 1.0130367f], Numkt[0.98215795f, 0.90664136f, 0.3652947f, 1.1545719f], Numkt[0.79763675f, 0.43727058f, 0.60035574f, 0.36558864f]])
        val (matrix1, matrix2) = data.getFloatMM(4, 3, 3, 4)
        val actual = NativeLinAlg.dot(matrix1, matrix2)
        assertFloatingNDArray(expected, actual)
    }

    @Test
    fun matrixDotMatrixTransposedTest() {
        val (matrix1F, matrix2F) = data.getFloatMM(3, 4, 3, 4)
        val (matrix1D, matrix2D) = data.getDoubleMM(3, 4, 3, 4)

        val matrix1TF = matrix1F.transpose()
        val matrix1TFCopy = matrix1TF.deepCopy()
        val expectedF = NativeLinAlg.dot(matrix1TFCopy, matrix2F)
        val actualF = NativeLinAlg.dot(matrix1TF, matrix2F)
        assertFloatingNDArray(expectedF, actualF)

        val matrix1TD = matrix1D.transpose()
        val matrix1TDCopy = matrix1TD.deepCopy()
        val expectedD = NativeLinAlg.dot(matrix1TDCopy, matrix2D)
        val actualD = NativeLinAlg.dot(matrix1TD, matrix2D)
        assertFloatingNDArray(expectedD, actualD)

        val matrix2TF = matrix2F.transpose()
        val matrix2TFCopy = matrix2TF.deepCopy()
        val expected2F = NativeLinAlg.dot(matrix1F, matrix2TFCopy)
        val actual2F = NativeLinAlg.dot(matrix1F, matrix2TF)
        assertFloatingNDArray(expected2F, actual2F)

        val matrix2TD = matrix2D.transpose()
        val matrix2TDCopy = matrix2TD.deepCopy()
        val expected2D = NativeLinAlg.dot(matrix1D, matrix2TDCopy)
        val actual2D = NativeLinAlg.dot(matrix1D, matrix2TD)
        assertFloatingNDArray(expected2D, actual2D)
    }

    @Test
    fun matrixVectorDotTestD() {
        val expected = Numkt.ndarray(Numkt[0.8120680956454793, 0.676196362161166, 0.5898845530863276])
        val (matrix, vector) = data.getDoubleMV(3)
        val actual = NativeLinAlg.dot(matrix, vector)
        assertFloatingNDArray(expected, actual)
    }

    @Test
    fun matrixVectorDotTestF() {
        val expected = Numkt.ndarray(Numkt[0.86327714f, 0.3244831f, 0.76492393f])
        val (matrix, vector) = data.getFloatMV(3)
        val actual = NativeLinAlg.dot(matrix, vector)
        assertFloatingNDArray(expected, actual)
    }

    @Test
    fun matrixSliceDotVectorTestF() {
        val (matrix, vector) = data.getFloatMV(5)
        val expected = NativeLinAlg.dot(matrix[2 until 5, 0 until 3].deepCopy(), vector[(0 until 5)..2].deepCopy())
        val actual = NativeLinAlg.dot(matrix[2 until 5, 0 until 3], vector[(0 until 5)..2])
        assertFloatingNDArray(expected, actual)
    }

    @Test
    fun vectorVectorDotTestF() {
        val (vector1, vector2) = data.getFloatVV(9)
        val actual = NativeLinAlg.dot(vector1, vector2)
        assertFloatingNumber(2.883776f, actual)
    }

    @Test
    fun vectorVectorDotTestD() {
        val (vector1, vector2) = data.getDoubleVV(9)
        val actual = NativeLinAlg.dot(vector1, vector2)
        assertFloatingNumber(1.9696041133566367, actual)
    }

    @Test
    fun vectorVectorDotTestWithFloatComplexNumbers() {
        val random = Random(42)
        val vector1 = Numkt.d1array(9) { ComplexFloat(random.nextFloat(), random.nextFloat()) }
        val vector2 = Numkt.d1array(9) { ComplexFloat(random.nextFloat(), random.nextFloat()) }
        val actual = NativeLinAlg.dot(vector1, vector2)
        assertFloatingComplexNumber(0.23971967f + 4.6530027f.i, actual)
    }

    @Test
    fun vectorVectorDotTestWithDouble32ComplexNumbers() {
        val random = Random(42)
        val vector1 = Numkt.d1array(9) { ComplexDouble(1.0, 0.0) }
        val vector2 = Numkt.d1array(9) { ComplexDouble(random.nextFloat(), random.nextFloat()) }
        val actual = NativeLinAlg.dot(vector1, vector2)
        assertFloatingComplexNumber(4.174294710159302 + 5.029674530029297.i, actual)
    }

    @Test
    fun vectorVectorDotTestWithDouble64ComplexNumbers() {
        val random = Random(42)
        val vector1 = Numkt.d1array(9) { ComplexDouble(random.nextDouble(), random.nextDouble()) }
        val vector2 = Numkt.d1array(9) { ComplexDouble(random.nextDouble(), random.nextDouble()) }
        val actual = NativeLinAlg.dot(vector1, vector2)
        assertFloatingComplexNumber(0.17520206558121712 + 4.552420480555579.i, actual)
    }

    @Test
    fun computeInverseMatrixOfFloat() {
        val a = data.getFloatM(117)
        val ainv = NativeLinAlg.inv(a)
        assertFloatingNDArray(Numkt.identity(117), NativeLinAlg.dot(a, ainv), 1e-4f)
    }

    @Test
    fun computeInverseMatrixOfDouble() {
        val a = data.getDoubleM(376)
        val ainv = NativeLinAlg.inv(a)
        assertFloatingNDArray(Numkt.identity(376), NativeLinAlg.dot(a, ainv))
    }

    @Test
    fun computeInverseMatrixOfComplexDouble() {
        val a = data.getComplexDoubleM(83)
        val ainv = NativeLinAlg.inv(a)
        assertComplexFloatingNDArray(Numkt.identity(83), NativeLinAlg.dot(a, ainv))
    }

    @Test
    fun computeEigenvalues() {
        val a = Numkt.ndarray(Numkt[Numkt[1.0, .0], Numkt[.0, 1.0]])
        val (w, v) = NativeLinAlgEx.eig(a)
        assertEquals(Numkt.ndarray(Numkt[ComplexDouble.one, ComplexDouble.one]), w)
        assertEquals(Numkt.ndarray(Numkt[Numkt[ComplexDouble.one, ComplexDouble.zero], Numkt[ComplexDouble.zero, ComplexDouble.one]]), v)
    }

    @Test
    fun computeNorm() {
        val a = Numkt.arange<Double>(9) - 4.0
        val b = a.reshape(3, 3)
        assertFloatingNumber(7.745966692414834, NativeLinAlg.norm(b, Norm.Fro))
        assertFloatingNumber(9.0, NativeLinAlg.norm(b, Norm.Inf))
        assertFloatingNumber(7.0, NativeLinAlg.norm(b, Norm.N1))
        assertFloatingNumber(4.0, NativeLinAlg.norm(b, Norm.Max))
    }
}