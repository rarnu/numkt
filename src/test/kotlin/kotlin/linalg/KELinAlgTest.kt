@file:Suppress("UNCHECKED_CAST")

package numkt_kotlin.linAlg


import com.rarnu.numkt.api.*
import com.rarnu.numkt.api.linalg.Norm
import com.rarnu.numkt.api.linalg.dot
import com.rarnu.numkt.api.linalg.norm
import com.rarnu.numkt.kotlin.linalg.*
import com.rarnu.numkt.kotlin.linalg.KELinAlgEx.solve
import com.rarnu.numkt.kotlin.linalg.KELinAlgEx.solveC
import com.rarnu.numkt.ndarray.complex.Complex
import com.rarnu.numkt.ndarray.complex.ComplexDouble
import com.rarnu.numkt.ndarray.complex.ComplexFloat
import com.rarnu.numkt.ndarray.complex.toComplexDouble
import com.rarnu.numkt.ndarray.data.*
import com.rarnu.numkt.ndarray.operations.map
import com.rarnu.numkt.ndarray.operations.minus
import com.rarnu.numkt.ndarray.operations.plus
import org.junit.Assert.*
import org.junit.Test
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

class KELinAlgTest {

    @Test
    fun vector_vectorDotTestWithFloatComplexNumbers() {
        val random = Random(42)
        val vector1 = Numkt.d1array(9) { ComplexFloat(random.nextFloat(), random.nextFloat()) }
        val vector2 = Numkt.d1array(9) { ComplexFloat(random.nextFloat(), random.nextFloat()) }

        val actual = KELinAlg.dot(vector1, vector2)
        println(actual)
//        assertFloatingComplexNumber(0.23971967f + 4.6530027f.i, actual)
    }

    @Test
    fun vector_vectorDotTestWithDouble32ComplexNumbers() {
        val random = Random(42)
        val vector1 = Numkt.d1array(9) { ComplexDouble(1.0, 0.0) }
        val vector2 = Numkt.d1array(9) { ComplexDouble(random.nextFloat(), random.nextFloat()) }

        val actual = KELinAlg.dot(vector1, vector2)
        println(actual)
//        assertFloatingComplexNumber(4.174294710159302 + 5.029674530029297.i, actual)
    }

    @Test
    fun vector_vectorDotTestWithDouble64ComplexNumbers() {
        val random = Random(42)
        val vector1 = Numkt.d1array(9) { ComplexDouble(random.nextDouble(), random.nextDouble()) }
        val vector2 = Numkt.d1array(9) { ComplexDouble(random.nextDouble(), random.nextDouble()) }

        val actual = KELinAlg.dot(vector1, vector2)
        println(actual)
//        assertFloatingComplexNumber(0.17520206558121712 + 4.552420480555579.i, actual)
    }

    @Test
    fun test_of_norm_function_with_p_equals_1() {
        val d2arrayDouble1 = Numkt.ndarray(Numkt[Numkt[1.0, 2.0], Numkt[3.0, 4.0]])
        val d2arrayDouble2 = Numkt.ndarray(Numkt[Numkt[-1.0, -2.0], Numkt[-3.0, -4.0]])

        var doubleDiff = abs(5.477225575051661 - Numkt.linalg.norm(d2arrayDouble1))
        assertTrue(doubleDiff < 1e-8)
        doubleDiff = abs(5.477225575051661 - Numkt.linalg.norm(d2arrayDouble2))
        assertTrue(doubleDiff < 1e-8)

        val d2arrayFloat1 = Numkt.ndarray(Numkt[Numkt[1f, 2f], Numkt[3f, 4f]])
        val d2arrayFloat2 = Numkt.ndarray(Numkt[Numkt[-1f, -2f], Numkt[-3f, -4f]])

        var floatDiff = abs(5.477226f - Numkt.linalg.norm(d2arrayFloat1))
        assertTrue(floatDiff < 1e-6f)
        assertEquals(6f, Numkt.linalg.norm(d2arrayFloat1, Norm.N1))
        assertEquals(4f, Numkt.linalg.norm(d2arrayFloat1, Norm.Max))
        assertEquals(7f, Numkt.linalg.norm(d2arrayFloat1, Norm.Inf))
        floatDiff = abs(5.477226f - Numkt.linalg.norm(d2arrayFloat2))
        assertTrue(floatDiff < 1e-6f)
        assertEquals(6f, Numkt.linalg.norm(d2arrayFloat2, Norm.N1))
        assertEquals(4f, Numkt.linalg.norm(d2arrayFloat2, Norm.Max))
        assertEquals(7f, Numkt.linalg.norm(d2arrayFloat2, Norm.Inf))
    }

    @Test
    fun test_plu() {

        // Number case
        val procedurePrecision = 1e-5
        val rnd = Random(424242)

        val iters = 1000
        val sideFrom = 1
        val sideUntil = 100
        for (all in 0 until iters) {
            val m = rnd.nextInt(sideFrom, sideUntil)
            val n = rnd.nextInt(sideFrom, sideUntil)

            val a = Numkt.d2array(m, n) { rnd.nextDouble() }

            val (P, L, U) = KELinAlgEx.plu(a)
            assertTriangular(L, isLowerTriangular = true, requireUnitsOnDiagonal = true)
            assertTriangular(U, isLowerTriangular = false, requireUnitsOnDiagonal = false)
            // assertClose(KELinAlg.dot(P, KELinAlg.dot(L, U)), a, procedurePrecision)
        }

        // Complex case
        //ComplexDouble
        for (all in 0 until iters) {
            val m = rnd.nextInt(sideFrom, sideUntil)
            val n = rnd.nextInt(sideFrom, sideUntil)

            val aRe = Numkt.d2array(m, n) { rnd.nextDouble() }
            val aIm = Numkt.d2array(m, n) { rnd.nextDouble() }
            val a = composeComplexDouble(aRe, aIm)

            val (P, L, U) = KELinAlgEx.pluC(a)
            assertTriangularComplexDouble(L, isLowerTriangular = true, requireUnitsOnDiagonal = true)
            assertTriangularComplexDouble(U, isLowerTriangular = false, requireUnitsOnDiagonal = false)
            // assertCloseComplex(KELinAlgEx.dotMMComplex(P, KELinAlgEx.dotMMComplex(L, U)), a, procedurePrecision)
        }
        //ComplexFloat
        for (all in 0 until iters) {
            val m = rnd.nextInt(sideFrom, sideUntil)
            val n = rnd.nextInt(sideFrom, sideUntil)

            val aRe = Numkt.d2array(m, n) { rnd.nextFloat() }
            val aIm = Numkt.d2array(m, n) { rnd.nextFloat() }
            val a = composeComplexFloat(aRe, aIm)

            val (P, L, U) = KELinAlgEx.pluC(a)


            assertTriangularComplexFloat(L, isLowerTriangular = true, requireUnitsOnDiagonal = true)
            assertTriangularComplexFloat(U, isLowerTriangular = false, requireUnitsOnDiagonal = false)
            assertCloseComplex(KELinAlgEx.dotMMComplex(P, KELinAlgEx.dotMMComplex(L, U)), a, procedurePrecision)
        }

    }

    @Test
    fun solve_test() {
        // double case
        val procedurePrecision = 1e-5
        val rnd = Random(424242)

        // corner cases
        val a11 = Numkt.ndarray(Numkt[Numkt[4.0]])
        val b11 = Numkt.ndarray(Numkt[Numkt[3.0]])
        val b1 = Numkt.ndarray(Numkt[5.0])
        val b3 = Numkt.ndarray(Numkt[3.0, 4.0, 5.0])
        val b13 = Numkt.ndarray(Numkt[Numkt[3.0, 4.0, 5.0]])
        // assertClose(solve(a11, b11), Numkt.ndarray(Numkt[Numkt[0.75]]), procedurePrecision)
        assertClose(solve(a11, b1), Numkt.ndarray(Numkt[1.25]), procedurePrecision)
        assertThrows(IllegalArgumentException::class.java) { solve(a11, b3) }
        assertClose(solve(a11, b13), Numkt.ndarray(Numkt[Numkt[0.75, 1.0, 1.25]]), procedurePrecision)

        for (iteration in 0 until 1000) {
            //test when second argument is d2 array
            val maxlen = 100
            val n = rnd.nextInt(1, maxlen)
            val m = rnd.nextInt(1, maxlen)
            val a = Numkt.d2array(n, n) { rnd.nextDouble() }
            val b = Numkt.d2array(n, m) { rnd.nextDouble() }
            assertClose(b, KELinAlg.dot(a, solve(a, b)), procedurePrecision)


            //test when second argument is d1 vector
            val bd1 = Numkt.d1array(n) { rnd.nextDouble() }
            val sol = solve(a, bd1)
            assertClose(KELinAlg.dot(a, sol.reshape(sol.shape[0], 1)).reshape(a.shape[0]), bd1, procedurePrecision)
        }

        // complexDouble case
        val c11 = Numkt.ndarray(Numkt[Numkt[ComplexDouble(4.0, 7.0)]])
        val d11 = Numkt.ndarray(Numkt[Numkt[ComplexDouble(3.0, 8.0)]])
        val d1 = Numkt.ndarray(Numkt[ComplexDouble(5.0, 9.0)])
        val d3 = Numkt.ndarray(Numkt[ComplexDouble(3.0, 11.0), ComplexDouble(4.0, 13.0), ComplexDouble(5.0, 17.0)])
        val d13 = Numkt.ndarray(Numkt[Numkt[ComplexDouble(3.0, 7.0), ComplexDouble(4.0, 8.0), ComplexDouble(5.0, 22.0)]])
        // d11 / c11
        assertCloseComplex(solveC(c11, d11), Numkt.ndarray(Numkt[Numkt[ComplexDouble(1.04615384615384, 0.1692307692307692)]]), procedurePrecision)
        // d1 / c11
        assertCloseComplex(solveC(c11, d1), Numkt.ndarray(Numkt[ComplexDouble(1.27692307692307, 0.01538461538461533)]), procedurePrecision)
        assertThrows(IllegalArgumentException::class.java) { solveC(c11, d3) }
        // d13 / c11
        assertCloseComplex(solveC(c11, d13), Numkt.ndarray(Numkt[Numkt[ComplexDouble(0.9384615384615385, 0.1076923076923077), ComplexDouble(1.1076923076923078, 0.06153846153846152), ComplexDouble(2.676923076923077, 0.8153846153846155)]]), procedurePrecision)

        for (iteration in 0 until 1000) {
            //test when second argument is d2 array
            val maxlen = 100
            val n = rnd.nextInt(1, maxlen)
            val m = rnd.nextInt(1, maxlen)

            val a = Numkt.zeros<ComplexDouble>(n, n)
            for (i in 0 until n) {
                for (j in 0 until n) {
                    a[i, j] = ComplexDouble(rnd.nextDouble(), rnd.nextDouble())
                }
            }

            val bRe = Numkt.d2array(n, m) { rnd.nextDouble() }
            val bIm = Numkt.d2array(n, m) { rnd.nextDouble() }
            val b = composeComplexDouble(bRe, bIm)
            assertCloseComplex(b, KELinAlg.dot(a, solveC(a, b)), procedurePrecision)


            //test when second argument is d1 vector
            val bd1Re = Numkt.d1array(n) { rnd.nextDouble() }
            val bd1Im = Numkt.d1array(n) { rnd.nextDouble() }
            val bd1 = composeComplexDouble(bd1Re, bd1Im)

            val sol = solveC(a, bd1)
            assertCloseComplex(KELinAlg.dot(a, sol.reshape(sol.shape[0], 1)).reshape(a.shape[0]), bd1, procedurePrecision)
        }

        // complexFloat case
        val c11ComplexFloat = Numkt.ndarray(Numkt[Numkt[ComplexFloat(4.0, 7.0)]])
        val d1ComplexFloat1ComplexFloat = Numkt.ndarray(Numkt[Numkt[ComplexFloat(3.0, 8.0)]])
        val d1ComplexFloat = Numkt.ndarray(Numkt[ComplexFloat(5.0, 9.0)])
        val d3ComplexFloat = Numkt.ndarray(Numkt[ComplexFloat(3.0, 11.0), ComplexFloat(4.0, 13.0), ComplexFloat(5.0, 17.0)])
        val d1ComplexFloat3 = Numkt.ndarray(Numkt[Numkt[ComplexFloat(3.0, 7.0), ComplexFloat(4.0, 8.0), ComplexFloat(5.0, 22.0)]])
        // d1ComplexFloat1ComplexFloat / c11ComplexFloat
        assertCloseComplex(solveC(c11ComplexFloat, d1ComplexFloat1ComplexFloat), Numkt.ndarray(Numkt[Numkt[ComplexFloat(1.04615384615384, 0.1692307692307692)]]), procedurePrecision)
        // d1ComplexFloat / c11ComplexFloat
        assertCloseComplex(solveC(c11ComplexFloat, d1ComplexFloat), Numkt.ndarray(Numkt[ComplexFloat(1.27692307692307, 0.01538461538461533)]), procedurePrecision)
        assertThrows(IllegalArgumentException::class.java) { solveC(c11ComplexFloat, d3ComplexFloat) }
        // d1ComplexFloat3 / c11ComplexFloat
        assertCloseComplex(solveC(c11ComplexFloat, d1ComplexFloat3), Numkt.ndarray(Numkt[Numkt[ComplexFloat(0.9384615384615385, 0.1076923076923077), ComplexFloat(1.1076923076923078, 0.06153846153846152), ComplexFloat(2.676923076923077, 0.8153846153846155)]]), procedurePrecision)

        for (iteration in 0 until 1000) {
            //test when second argument is d2 array
            val maxlen = 100
            val n = rnd.nextInt(1, maxlen)
            val m = rnd.nextInt(1, maxlen)

            val a = Numkt.zeros<ComplexFloat>(n, n)
            for (i in 0 until n) {
                for (j in 0 until n) {
                    a[i, j] = ComplexFloat(rnd.nextFloat(), rnd.nextFloat())
                }
            }

            val bRe = Numkt.d2array(n, m) { rnd.nextFloat() }
            val bIm = Numkt.d2array(n, m) { rnd.nextFloat() }
            val b = composeComplexFloat(bRe, bIm)
            assertCloseComplex(b, KELinAlg.dot(a, solveC(a, b)), 1e-2)


            //test when second argument is d1ComplexFloat vector
            val bd1ComplexFloatRe = Numkt.d1array(n) { rnd.nextFloat() }
            val bd1ComplexFloatIm = Numkt.d1array(n) { rnd.nextFloat() }
            val bd1ComplexFloat = composeComplexFloat(bd1ComplexFloatRe, bd1ComplexFloatIm)

            val sol = solveC(a, bd1ComplexFloat)
            assertCloseComplex(KELinAlg.dot(a, sol.reshape(sol.shape[0], 1)).reshape(a.shape[0]), bd1ComplexFloat, 1e-2)
        }


    }

    @Test
    fun testKEDot() {
        // random matrices pool
        val mat1 = Numkt.ndarray(Numkt[Numkt[4, -3, 2], Numkt[-6, -9, -7], Numkt[3, 6, 5]])
        val mat2 = Numkt.ndarray(Numkt[Numkt[-9, 4, -8], Numkt[-8, 2, 6], Numkt[3, 8, 7]])
        val mat3 = Numkt.ndarray(Numkt[Numkt[8, -2, -1], Numkt[7, -9, -1], Numkt[-9, -9, -2]])
        val mat4 = Numkt.ndarray(Numkt[Numkt[-8, 9, -10], Numkt[-6, 8, -9], Numkt[5, -5, 3]])
        val vec1 = Numkt.ndarray(Numkt[5, -1, 6])
        val vec2 = Numkt.ndarray(Numkt[5, -9, 1])
        val vec3 = Numkt.ndarray(Numkt[5, -10, 1])
        val vec4 = Numkt.ndarray(Numkt[9, -6, 3])

        // true operation results
        val mat1_x_mat1 = Numkt.ndarray(Numkt[Numkt[40, 27, 39], Numkt[9, 57, 16], Numkt[-9, -33, -11]])
        val mat1_x_mat2 = Numkt.ndarray(Numkt[Numkt[-6, 26, -36], Numkt[105, -98, -55], Numkt[-60, 64, 47]])
        val mat1_x_mat3 = Numkt.ndarray(Numkt[Numkt[-7, 1, -5], Numkt[-48, 156, 29], Numkt[21, -105, -19]])
        val mat1_x_mat4 = Numkt.ndarray(Numkt[Numkt[-4, 2, -7], Numkt[67, -91, 120], Numkt[-35, 50, -69]])

        val mat2_x_mat2 = Numkt.ndarray(Numkt[Numkt[25, -92, 40], Numkt[74, 20, 118], Numkt[-70, 84, 73]])
        val mat2_x_mat3 = Numkt.ndarray(Numkt[Numkt[28, 54, 21], Numkt[-104, -56, -6], Numkt[17, -141, -25]])
        val mat2_x_mat4 = Numkt.ndarray(Numkt[Numkt[8, -9, 30], Numkt[82, -86, 80], Numkt[-37, 56, -81]])

        val mat3_x_mat3 = Numkt.ndarray(Numkt[Numkt[59, 11, -4], Numkt[2, 76, 4], Numkt[-117, 117, 22]])
        val mat3_x_mat4 = Numkt.ndarray(Numkt[Numkt[-57, 61, -65], Numkt[-7, -4, 8], Numkt[116, -143, 165]])

        val mat1_x_vec1 = Numkt.ndarray(Numkt[35, -63, 39])
        val mat1_x_vec2 = Numkt.ndarray(Numkt[49, 44, -34])
        val mat2_x_vec1 = Numkt.ndarray(Numkt[-97, -6, 49])
        val mat2_x_vec2 = Numkt.ndarray(Numkt[-89, -52, -50])

        val vec1_x_vec1 = 62
        val vec1_x_vec2 = 40
        val vec1_x_vec3 = 41
        val vec1_x_vec4 = 69
        val vec2_x_vec2 = 107
        val vec2_x_vec3 = 116
        val vec2_x_vec4 = 102
        val vec3_x_vec3 = 126
        val vec3_x_vec4 = 108
        val vec4_x_vec4 = 126


        //Start test cases

        //Byte

        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toByte() }, mat1.map { it.toByte() }).data, mat1_x_mat1.map { it.toByte() }.data)
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toByte() }, mat2.map { it.toByte() }).data, mat1_x_mat2.map { it.toByte() }.data)
        assertEquals(KELinAlgEx.dotMV(mat1.map { it.toByte() }, vec1.map { it.toByte() }).data, mat1_x_vec1.map { it.toByte() }.data)
        assertEquals(KELinAlgEx.dotVV(vec1.map { it.toByte() }, vec2.map { it.toByte() }), vec1_x_vec2.toByte())

        //Short
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toShort() }, mat1.map { it.toShort() }).data, mat1_x_mat1.map { it.toShort() }.data)
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toShort() }, mat2.map { it.toShort() }).data, mat1_x_mat2.map { it.toShort() }.data)
        assertEquals(KELinAlgEx.dotMV(mat1.map { it.toShort() }, vec1.map { it.toShort() }).data, mat1_x_vec1.map { it.toShort() }.data)
        assertEquals(KELinAlgEx.dotVV(vec1.map { it.toShort() }, vec2.map { it.toShort() }), vec1_x_vec2.toShort())

        //int
        assertEquals(KELinAlgEx.dotMM(mat1, mat1).data, mat1_x_mat1.data)
        assertEquals(KELinAlgEx.dotMM(mat1, mat2).data, mat1_x_mat2.data)
        assertEquals(KELinAlgEx.dotMV(mat1, vec1).data, mat1_x_vec1.data)
        assertEquals(KELinAlgEx.dotVV(vec1, vec2), vec1_x_vec2)

        //Long
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toLong() }, mat1.map { it.toLong() }).data, mat1_x_mat1.map { it.toLong() }.data)
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toLong() }, mat2.map { it.toLong() }).data, mat1_x_mat2.map { it.toLong() }.data)
        assertEquals(KELinAlgEx.dotMV(mat1.map { it.toLong() }, vec1.map { it.toLong() }).data, mat1_x_vec1.map { it.toLong() }.data)
        assertEquals(KELinAlgEx.dotVV(vec1.map { it.toLong() }, vec2.map { it.toLong() }), vec1_x_vec2.toLong())

        //Float
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toFloat() }, mat1.map { it.toFloat() }).data, mat1_x_mat1.map { it.toFloat() }.data)
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toFloat() }, mat2.map { it.toFloat() }).data, mat1_x_mat2.map { it.toFloat() }.data)
        assertEquals(KELinAlgEx.dotMV(mat1.map { it.toFloat() }, vec1.map { it.toFloat() }).data, mat1_x_vec1.map { it.toFloat() }.data)
        assertEquals(KELinAlgEx.dotVV(vec1.map { it.toFloat() }, vec2.map { it.toFloat() }), vec1_x_vec2.toFloat())

        //Double
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toDouble() }, mat1.map { it.toDouble() }).data, mat1_x_mat1.map { it.toDouble() }.data)
        assertEquals(KELinAlgEx.dotMM(mat1.map { it.toDouble() }, mat2.map { it.toDouble() }).data, mat1_x_mat2.map { it.toDouble() }.data)
        assertEquals(KELinAlgEx.dotMV(mat1.map { it.toDouble() }, vec1.map { it.toDouble() }).data, mat1_x_vec1.map { it.toDouble() }.data)

        //ComplexDouble
        assertEquals(
            KELinAlgEx.dotMMComplex(composeComplexDouble(mat1, mat2), composeComplexDouble(mat3, mat4)).data, composeComplexDouble(mat1_x_mat3 - mat2_x_mat4, mat1_x_mat4 + mat2_x_mat3).data
        )

        assertEquals(
            KELinAlgEx.dotMVComplex(composeComplexDouble(mat1, mat2), composeComplexDouble(vec1, vec2)).data, composeComplexDouble(mat1_x_vec1 - mat2_x_vec2, mat1_x_vec2 + mat2_x_vec1).data
        )
        assertEquals(
            KELinAlgEx.dotVVComplex(composeComplexDouble(vec1, vec2), composeComplexDouble(vec3, vec4)), ComplexDouble(vec1_x_vec3 - vec2_x_vec4, vec1_x_vec4 + vec2_x_vec3)
        )

        //ComplexFloat
        assertEquals(
            KELinAlgEx.dotMMComplex(composeComplexFloat(mat1, mat2), composeComplexFloat(mat3, mat4)).data, composeComplexFloat(mat1_x_mat3 - mat2_x_mat4, mat1_x_mat4 + mat2_x_mat3).data
        )

        assertEquals(
            KELinAlgEx.dotMVComplex(composeComplexFloat(mat1, mat2), composeComplexFloat(vec1, vec2)).data, composeComplexFloat(mat1_x_vec1 - mat2_x_vec2, mat1_x_vec2 + mat2_x_vec1).data
        )
        assertEquals(
            KELinAlgEx.dotVVComplex(composeComplexFloat(vec1, vec2), composeComplexFloat(vec3, vec4)), ComplexFloat(vec1_x_vec3 - vec2_x_vec4, vec1_x_vec4 + vec2_x_vec3)
        )

    }

    @Test
    fun test_upper_hessenberg_form() {
        val n = 300
        val mat = getRandomMatrixComplexDouble(n, n)
        val (Q, H) = upperHessenbergDouble(mat.deepCopy())

        // check H is upper Hessenberg
        for (i in 2 until H.shape[0]) {
            for (j in 0 until i - 1) {
                assertEquals(H[i, j], ComplexDouble.zero)
            }
        }

        // assert Q is unitary
        val approxId = dotMatrixComplex(Q, Q.conjTranspose())
        val Id = Numkt.zeros<ComplexDouble>(n, n)

        // assertCloseComplex(approxId, idComplexDouble(n), 1e-5)

        // assert decomposition is valid
        val approxmat = dotMatrixComplex(dotMatrixComplex(Q, H), Q.conjTranspose())
        // assertCloseComplex(approxmat, mat, 1e-5)
    }


    @Test
    fun test_qr() {
        val n = 100
        val mat = getRandomMatrixComplexDouble(n, n)
        val (q, r) = qrComplexDouble(mat)

        // assert decomposition is valid
        // assertCloseComplex(dotMatrixComplex(q, r), mat, 1e-5)

        // assert q is unitary
        // assertCloseComplex(dotMatrixComplex(q, q.conjTranspose()), idComplexDouble(n), 1e-5)

        // assert r is upper triangular
        for (i in 1 until r.shape[0]) {
            for (j in 0 until i) {
                if (r[i, j] != ComplexDouble.zero) {
                    assertEquals(r[i, j], ComplexDouble.zero)
                }
            }
        }
    }

    @Test
    fun test_Schur_decomposition() {
        for (attempt in 0 until 5) {

            val n = when (attempt) {
                0 -> 1
                1 -> 2
                2 -> 5
                3 -> 10
                4 -> 100
                else -> 100
            }

            val mat = getRandomMatrixComplexDouble(n, n)
            val (q, r) = schurDecomposition(mat.deepCopy())

            // assert decomposition is valid
            // assertCloseComplex(dotMatrixComplex(dotMatrixComplex(q, r), q.conjTranspose()), mat, 1e-5)

            // assert q is unitary
            // assertCloseComplex(dotMatrixComplex(q, q.conjTranspose()), idComplexDouble(n), 1e-5)

            // assert r is upper triangular
            for (i in 1 until r.shape[0]) {
                for (j in 0 until i) {
                    if (r[i, j] != ComplexDouble.zero) {
                        assertEquals(r[i, j], ComplexDouble.zero)
                    }
                }
            }
        }
    }


    @Test
    fun test_eigenvalues() {
        val precision = 1e-2
        val n = 50
        val R = getRandomMatrixComplexDouble(n, n, -1000.0, 1000.0)
        for (i in 0 until R.shape[0]) {
            for (j in 0 until i) {
                R[i, j] = ComplexDouble.zero
            }
        }
        val Q = gramShmidtComplexDouble(getRandomMatrixComplexDouble(n, n))

        // assertCloseComplex(dotMatrixComplex(Q, Q.conjTranspose()), idComplexDouble(n), precision = 1e-5)

        val mat = dotMatrixComplex(dotMatrixComplex(Q, R), Q.conjTranspose())

        var trueEigavals = List(n) { i -> R[i, i] }

        val eigs = KELinAlgEx.eigValsC(mat)

        var testedEigenvals = List(n) { i -> eigs[i] }

        trueEigavals = trueEigavals.sortedWith(compareBy({ it.re }, { it.im }))
        testedEigenvals = testedEigenvals.sortedWith(compareBy({ it.re }, { it.im }))



        for (i in 0 until n) {
            assertTrue("${trueEigavals[i]} =/= ${testedEigenvals[i]}", (trueEigavals[i] - testedEigenvals[i]).abs() < precision )
        }

    }
}


private fun <T : Complex, D : Dim2> assertCloseComplex(a: MultiArray<T, D>, b: MultiArray<T, D>, precision: Double) {
    assertEquals("matrices have different dimensions", a.dim.d, b.dim.d)
    assertEquals(a.dtype, b.dtype)

    var maxabs = 0.0
    if (a.dim.d == 1) {
        when (a.dtype) {
            DataType.ComplexDoubleDataType -> {
                a as D1Array<ComplexDouble>
                b as D1Array<ComplexDouble>
                for (i in 0 until a.size) maxabs = max((a[i] - b[i]).abs(), maxabs)
            }

            DataType.ComplexFloatDataType -> {
                a as D1Array<ComplexFloat>
                b as D1Array<ComplexFloat>
                for (i in 0 until a.size) maxabs = max((a[i] - b[i]).abs().toDouble(), maxabs)
            }

            else -> {
                throw UnsupportedOperationException()
            }
        }
        assertTrue("matrices not close", maxabs < precision)
        return
    }
    when (a.dtype) {
        DataType.ComplexDoubleDataType -> {
            a as D2Array<ComplexDouble>
            b as D2Array<ComplexDouble>
            for (i in 0 until a.shape[0]) {
                for (j in 0 until a.shape[1]) {
                    maxabs = max((a[i, j] - b[i, j]).abs(), maxabs)
                }
            }
        }

        DataType.ComplexFloatDataType -> {
            a as D2Array<ComplexFloat>
            b as D2Array<ComplexFloat>
            for (i in 0 until a.shape[0]) {
                for (j in 0 until a.shape[1]) {
                    maxabs = max((a[i, j] - b[i, j]).abs().toDouble(), maxabs)
                }
            }
        }

        else -> {
            throw UnsupportedOperationException()
        }
    }
    assertTrue("matrices not close", maxabs < precision)
    return

}


private fun <T : Number, D : Dim2> assertClose(a: MultiArray<T, D>, b: MultiArray<T, D>, precision: Double) {
    assertEquals("matrices have different dimensions", a.dim.d, b.dim.d)
    var maxabs = 0.0
    if (a.dim.d == 1) {
        a as D1Array<T>
        b as D1Array<T>
        for (i in 0 until a.size) maxabs = max(abs(a[i].toDouble() - b[i].toDouble()), maxabs)
    } else {
        a as D2Array<T>
        b as D2Array<T>
        for (i in 0 until a.shape[0]) {
            for (j in 0 until a.shape[1]) {
                maxabs = max(abs(a[i, j].toDouble() - b[i, j].toDouble()), maxabs)
            }
        }
    }
    assertTrue("matrices not close", maxabs < precision)
}

private fun <T : Number> assertTriangular(a: MultiArray<T, D2>, isLowerTriangular: Boolean, requireUnitsOnDiagonal: Boolean) {
    if (requireUnitsOnDiagonal) {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            if (a[i, i].toDouble() != 1.0) throw AssertionError("element at position [$i, $i] of matrix \n$a\n is not unit")
        }
    }
    if (isLowerTriangular) {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            for (j in i + 1 until a.shape[1]) {
                if (a[i, j].toDouble() != 0.0) throw AssertionError("element at position [$i, $j] of matrix \n$a\n is not zero")
            }
        }
    } else {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            for (j in 0 until i) {
                if (a[i, j].toDouble() != 0.0) throw AssertionError("element at position [$i, $j] of matrix \n$a\n is not zero")
            }
        }
    }
}

private fun assertTriangularComplexDouble(a: MultiArray<ComplexDouble, D2>, isLowerTriangular: Boolean, requireUnitsOnDiagonal: Boolean) {
    if (requireUnitsOnDiagonal) {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            if (a[i, i] != ComplexDouble.one) throw AssertionError("element at position [$i, $i] of matrix \n$a\n is not unit")
        }
    }
    if (isLowerTriangular) {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            for (j in i + 1 until a.shape[1]) {
                if (a[i, j] != ComplexDouble.zero) throw AssertionError("element at position [$i, $j] of matrix \n$a\n is not zero")
            }
        }
    } else {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            for (j in 0 until i) {
                if (a[i, j] != ComplexDouble.zero) throw AssertionError("element at position [$i, $j] of matrix \n$a\n is not zero")
            }
        }
    }
}

private fun assertTriangularComplexFloat(a: MultiArray<ComplexFloat, D2>, isLowerTriangular: Boolean, requireUnitsOnDiagonal: Boolean) {
    if (requireUnitsOnDiagonal) {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            if (a[i, i] != ComplexFloat.one) throw AssertionError("element at position [$i, $i] of matrix \n$a\n is not unit")
        }
    }
    if (isLowerTriangular) {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            for (j in i + 1 until a.shape[1]) {
                if (a[i, j] != ComplexFloat.zero) throw AssertionError("element at position [$i, $j] of matrix \n$a\n is not zero")
            }
        }
    } else {
        for (i in 0 until min(a.shape[0], a.shape[1])) {
            for (j in 0 until i) {
                if (a[i, j] != ComplexFloat.zero) throw AssertionError("element at position [$i, $j] of matrix \n$a\n is not zero")
            }
        }
    }
}


private fun getRandomMatrixComplexDouble(n: Int, m: Int, from: Double = 0.0, to: Double = 1.0, rnd: Random = Random(424242)): D2Array<ComplexDouble> {
    val a = Numkt.zeros<ComplexDouble>(n, m)

    for (i in 0 until n) {
        for (j in 0 until m) {
            a[i, j] = ComplexDouble(rnd.nextDouble() * (to - from) + from, rnd.nextDouble() * (to - from) + from)
        }
    }
    return a
}

private fun idComplexDouble(n: Int): D2Array<ComplexDouble> {
    val ans = Numkt.zeros<ComplexDouble>(n, n)
    for (i in 0 until n) {
        ans[i, i] = 1.0.toComplexDouble()
    }
    return ans
}


private fun <T : Number, D : Dim2> composeComplexDouble(rePart: NDArray<T, D>, imPart: NDArray<T, D>): NDArray<ComplexDouble, D> {
    if (rePart.dim.d == 1) {
        rePart as D1Array<T>
        imPart as D1Array<T>
        val ans = Numkt.zeros<ComplexDouble>(rePart.shape[0])
        for (i in 0 until ans.shape[0]) {
            ans[i] = ComplexDouble(rePart[i].toDouble(), imPart[i].toDouble())
        }
        return ans as NDArray<ComplexDouble, D>
    }

    rePart as D2Array<T>
    imPart as D2Array<T>
    val ans = Numkt.zeros<ComplexDouble>(rePart.shape[0], rePart.shape[1])
    for (i in 0 until ans.shape[0]) {
        for (j in 0 until ans.shape[1]) {
            ans[i, j] = ComplexDouble(rePart[i, j].toDouble(), imPart[i, j].toDouble())
        }
    }
    return ans as NDArray<ComplexDouble, D>
}

private fun <T : Number, D : Dim2> composeComplexFloat(rePart: NDArray<T, D>, imPart: NDArray<T, D>): NDArray<ComplexFloat, D> {
    if (rePart.dim.d == 1) {
        rePart as D1Array<T>
        imPart as D1Array<T>
        val ans = Numkt.zeros<ComplexFloat>(rePart.shape[0])
        for (i in 0 until ans.shape[0]) {
            ans[i] = ComplexFloat(rePart[i].toFloat(), imPart[i].toFloat())
        }
        return ans as NDArray<ComplexFloat, D>
    }

    rePart as D2Array<T>
    imPart as D2Array<T>
    val ans = Numkt.zeros<ComplexFloat>(rePart.shape[0], rePart.shape[1])
    for (i in 0 until ans.shape[0]) {
        for (j in 0 until ans.shape[1]) {
            ans[i, j] = ComplexFloat(rePart[i, j].toFloat(), imPart[i, j].toFloat())
        }
    }
    return ans as NDArray<ComplexFloat, D>
}