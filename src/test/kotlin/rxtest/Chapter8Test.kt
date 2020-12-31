package rxtest

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
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

    @Test
    fun checkEmissionsCount() {
        val emissionsCount = AtomicInteger()
        Observable.range(1, 10)
            .subscribeOn(Schedulers.computation())
            .blockingSubscribe {
                emissionsCount.incrementAndGet()
            }
        assertEquals(10, emissionsCount.get())
    }

    @Test
    fun testWithBlickingFirst() {
        val observable = listOf(3, 6, 4, 11, 8, 9).toObservable()
            .sorted()
        val firstItem = observable.blockingFirst()
        assertEquals(3, firstItem)
    }

    @Test
    fun testSingleWithBlockingGet() {
        val observable = listOf(3, 6, 4, 11, 8, 9).toObservable()
            .sorted()
        val firstElement = observable.first(0)
        val firstItem = firstElement.blockingGet()
        assertEquals(3, firstItem)
    }

    @Test
    fun testMaybeWithBlockingGet() {
        val observable = listOf(3, 6, 4, 11, 8, 9).toObservable()
            .sorted()
        val firstElement = observable.firstElement()
        val firstItem = firstElement.blockingGet()
        assertEquals(3, firstItem)
    }

}