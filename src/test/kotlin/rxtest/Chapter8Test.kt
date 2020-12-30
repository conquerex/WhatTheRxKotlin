package rxtest

import org.junit.Test
import kotlin.random.Random
import kotlin.test.*

/**
 * @author Jongkook
 * @date : 2020/12/31
 */

internal class Chapter8Test {
//    @Test
//    fun `my first test`() {
//        assertEquals(3, 1 + 21, "Error~!!!")
//    }

    @Test
    fun testAdd() {
        val sample = Chapter8()
        assertEquals(2 + 3, sample.add(2, 3))
        assertEquals(3 * 4, mult(3, 4))
    }

    @Test
    fun expectBlockEvaluation() {
        expect(10, {
            val x = 5
            val y = 2
            x * y
        })
    }

    @Test
    fun assertIllegalValue() {
        assertNotEquals(-1, Random.nextInt())
    }

    @Test
    fun assertTrueBooleanValue() {
        assertTrue(true)
    }

    @Test
    fun assertThatPassedValueIsNull() {
        assertNull(null)
    }
}