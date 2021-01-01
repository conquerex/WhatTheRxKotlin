package rxtest

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.rxkotlin.toFlowable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
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

    @Test
    fun testWithBlockingLast() {
        val observable = listOf(3, 6, 4, 11, 8, 9).toObservable()
            .sorted()
        val firstItem = observable.blockingLast()
        assertEquals(11, firstItem)
    }

    @Test
    fun testWithBlockingIterable() {
        val list = listOf(3, 6, 4, 11, 8, 9)
        val observable = list.toObservable().sorted()

        val iterable = observable.blockingIterable()
        assertEquals(list.sorted(), iterable.toList())
    }

    @Test
    fun testWithBlockingForEach() {
        val list = listOf(5, 4, 3, 6, 7, 8, 1, 2, 11, 14, 13, 12, 9, 10)
        val observable = list.toObservable().filter {
            it % 2 == 0
        }
        observable.forEach {
            assertTrue(it % 2 == 0)
        }
    }

    @Test
    fun testWithTestObserver() {
        val list = listOf(5, 4, 3, 6, 7, 8, 1, 2, 11, 14, 13, 12, 9, 10)
        val observable = list.toObservable().sorted()

        val testObserver = TestObserver<Int>()

        // Observable을 구독
        observable.subscribe(testObserver)

        // 구독이 성공적이었는지 테스트를 통해 검증
        testObserver.assertSubscribed()

        // Observable이 실행을 완료할 때까지 스레드를 차단
        // 이 최종(터미널) 이벤트는 onComplete 또는 onError 일 수 있다.
        testObserver.awaitTerminalEvent()
        // 구독중에 오류가 발생하지 않았는지를 테스트
        testObserver.assertNoErrors()
        // 프로듀서가 성공적으로 완료됐는지 테스트
        testObserver.assertComplete()
        // 수신한 전체 배출이 14개인지 테스트 (목록에 14개 항목이 있음)
        testObserver.assertValueCount(14)
        // 각 배출의 예상 값과 실제 값을 순서대로 테스트
        testObserver.assertValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
    }

    @Test
    fun testWithTestSubscriber() {
        val list = listOf(5, 4, 3, 6, 7, 8, 1, 2, 11, 14, 13, 12, 9, 10)
        val flowable = list.toFlowable().sorted()

        val testSubscriber = TestSubscriber<Int>()

        flowable.subscribe(testSubscriber)

        testSubscriber.assertSubscribed()

        testSubscriber.awaitTerminalEvent()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValueCount(14)
        testSubscriber.assertValues(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14)
    }

}