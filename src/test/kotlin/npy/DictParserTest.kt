package npy

import com.rarnu.numkt.api.bio.npy.parseDict
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class TestParseDict(private val expected: Map<String, Any>, private val s: String) {

    @Test
    fun parse() {
        assertEquals(expected, parseDict(s))
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{1}")
        fun `data`() = listOf(
            arrayOf(emptyMap<String, String>(), "{}"),
            arrayOf(mapOf("foo" to "bar"), "{'foo': 'bar'}"),
            arrayOf(mapOf("foo" to false), "{'foo': False}"),
            arrayOf(mapOf("foo" to "False"), "{'foo': 'False'}"),
            arrayOf(mapOf("foo" to true), "{'foo': True}"),
            arrayOf(mapOf("foo" to emptyList<Int>()), "{'foo': ()}"),
            arrayOf(mapOf("foo" to listOf(1, 2)), "{'foo': (1, 2, )}"),
            arrayOf(mapOf("foo" to emptyList<Int>(), "boo" to "bar"), "{'foo': (), 'boo': 'bar'}"),
            arrayOf(mapOf("foo" to emptyList<Int>(), "boo" to "bar"), "{'foo'  :   ()  ,    'boo': 'bar'}")
        )
    }
}