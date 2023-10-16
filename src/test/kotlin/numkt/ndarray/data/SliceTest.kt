@file:Suppress("EmptyRange")

package numkt.ndarray.data

import com.rarnu.numkt.api.*
import com.rarnu.numkt.ndarray.data.bounds
import com.rarnu.numkt.ndarray.data.get
import com.rarnu.numkt.ndarray.data.rangeTo
import com.rarnu.numkt.ndarray.data.sl
import org.junit.Assert.*
import org.junit.Test


class SliceTest {

    @Test
    fun testSlice1D() {
        val a = Numkt.ndarrayOf(1, 2, 3, 4)

        assertEquals(Numkt.ndarrayOf(2, 3), a[1 until 3])
        assertTrue(a[3 until 1].isEmpty())
        assertEquals(Numkt.ndarrayOf(1, 3), a[sl.bounds..2])
    }

    @Test
    fun testSlice2D() {
        val a = Numkt.d2array(3, 3) { it }

        assertEquals(Numkt.ndarray(Numkt[Numkt[3, 4, 5], Numkt[6, 7, 8]]), a[1 until 3])
        assertEquals(Numkt.ndarray(Numkt[Numkt[2], Numkt[5]]), a[0 until 2, 2 until 3])
        assertEquals(Numkt.ndarrayOf(3), a[1, 0 until 1])
        assertEquals(Numkt.ndarrayOf(5), a[1 until 2, 2])
    }

    @Test
    fun testSlice3D() {
        val a = Numkt.d3array(3, 3, 3) { it }

        assertEquals(Numkt.d3array(2, 3, 3) { it + 9 }, a[1 until 3])
        assertEquals(
            Numkt.ndarray(Numkt[Numkt[Numkt[3, 4, 5], Numkt[6, 7, 8]], Numkt[Numkt[12, 13, 14], Numkt[15, 16, 17]]]),
            a[0 until 2, 1 until 3, sl.bounds]
        )
        assertEquals(Numkt.ndarrayOf(15, 16), a[1, 2, 0 until 2])
        assertEquals(Numkt.ndarrayOf(11, 14), a[1, 0 until 2, 2])
        assertEquals(Numkt.ndarrayOf(5, 14), a[0 until 2, 1, 2])
        assertEquals(Numkt.ndarray(Numkt[Numkt[4, 7], Numkt[13, 16]]), a[0 until 2, 1 until 3, 1])
        assertEquals(Numkt.ndarray(Numkt[Numkt[4, 5], Numkt[13, 14]]), a[0 until 2, 1, 1 until 3])
        assertEquals(Numkt.ndarray(Numkt[Numkt[10, 11], Numkt[13, 14]]), a[1, 0 until 2, 1 until 3])
    }

    @Test
    fun testSlice4D() {
        val a = Numkt.d4array(2, 2, 2, 2) { it }
        assertEquals(a, a[sl.bounds])
        assertEquals(Numkt.ndarray(Numkt[Numkt[Numkt[Numkt[5]]]]), a[0 until 1, 1 until 2, 0 until 1, 1 until 2])
        assertEquals(Numkt.ndarray(Numkt[Numkt[Numkt[5]]]), a[0 until 1, 1, 0 until 1, (1 until 2)..1])
        assertEquals(Numkt.ndarray(Numkt[Numkt[7]]), a[0 until 1, 1, 1, 1 until 2])
        assertEquals(Numkt.ndarrayOf(6), a[0 until 1, 1, 1, 0])
    }

    @Test
    fun testBase() {
        val a = Numkt.ndarrayOf(0, 1, 2, 3, 4, 5)
        val b = a[1 until 5]
        val c = a[1 until 3]
        assertSame(null, a.base)
        assertSame(a, b.base)
        assertSame(a, c.base)

        val a2 = a.reshape(3, 2)
        val b2 = b.reshape(4, 1)
        assertSame(a, a2.base)
        assertSame(null, b2.base)

        val d1 = b2.squeeze()
        val d2 = d1.unsqueeze()
        assertSame(b2, d1.base)
        assertSame(b2, d2.base)

        val e = b2.transpose()
        assertSame(b2, e.base)

        val f = a2[1]
        assertSame(a, f.base)

        val x = b2.deepCopy()
        val y = b2.copy()
        assertSame(null, x.base)
        assertSame(null, y.base)

        val z = a2.asDNArray()
        assertSame(a, z.base)
    }
}