import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Callable
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

/**
 * @author Jongkook
 * @date : 2020/12/05
 */

fun main(args: Array<String>) {
    /**
     * 옵저버블이 동작하는 방법
     */
    val observer = object : Observer<Any> { // Observer 인스턴스를 Any 타입으로 지정
        override fun onComplete() {
            // observable이 오류없이 모든 아이템을 처리하면 호출
            println("All completed")
        }

        override fun onNext(t: Any) {
            // 옵저버블이 내보내는 각 아이템에 대해 호출
            // 데이터를 콘솔에 호출
            println("Next $t")
        }

        override fun onError(e: Throwable) {
            // 옵저버블에 오류가 발생 했을 때
            println("Error occured $e")
        }

        override fun onSubscribe(d: Disposable) {
            // 옵저버가 옵저버블을 구독할 때마다 호출
            println("Subscribed to $d")
        }
    }

    // list를 통해 옵저버블을 생성
    val observable = listOf("One", 2, 4.5, 6.0f).toObservable()

    // observer가 observable을 구독
    observable.subscribe(observer)

    // 옵저버블을 다시 생성 - 이 객체는 목록을 아이템으로 갖고 있다.
    val observableOnList = Observable.just(
        listOf("One", 2, 4.5, 6.0f),
        listOf("List with Single item"),
        listOf(1, 2, 3, 4, 8, 9)
    )

    observableOnList.subscribe(observer)

    println(
        """
        
        ******************************
        Observable.create 메서드 이해
        ******************************
        
    """.trimIndent()
    )

    // Observer 생성
    val ob = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }

        override fun onNext(t: String) {
            println("Next $t")
        }

        override fun onError(e: Throwable) {
            println("Error occured ${e.message}")
        }

        override fun onComplete() {
            println("All completed")
        }
    }

    val observable2 = Observable.create<String> {
        it.onNext("Emit 1")
        it.onNext("Emit 2")
        it.onNext("Emit 3")
        it.onNext("Emit 5")
        it.onComplete()
    }

    observable2.subscribe(ob)

    val observable3 = Observable.create<String> {
        it.onNext("Emit 2")
        it.onNext("Emit 3")
        it.onNext("Emit 5")
        it.onNext("Emit 6")
        it.onError(Exception("My custom exception"))
    }

    observable3.subscribe(ob)

    println(
        """
        
        ******************************
        Observable.from 메서드 이해
        ******************************
        
    """.trimIndent()
    )

    val observer4 = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }

        override fun onNext(t: String) {
            println("Next >> $t")
        }

        override fun onError(e: Throwable) {
            println("Error occured >> ${e.message}")
        }

        override fun onComplete() {
            println("All completed")
        }
    }

    val list = listOf("String 1", "String 2", "String 3", "String 7")
    val observableFromIterable = Observable.fromIterable(list)
    observableFromIterable.subscribe(observer4)

    val callable = Callable { "From Callable" }
    val observableFromCallable = Observable.fromCallable(callable)
    observableFromCallable.subscribe(observer4)

    val future = object : Future<String> {
        override fun cancel(p0: Boolean): Boolean = false

        override fun isCancelled(): Boolean = false

        override fun isDone(): Boolean = true

        override fun get(): String = "Hello From future / get()"

        override fun get(p0: Long, p1: TimeUnit): String = "Hello From future / get(p0, p1)"
    }
    val observableFromFuture = Observable.fromFuture(future)
    observableFromFuture.subscribe(observer4)

    println(
        """
        
        ******************************
        toObservable의 확장 함수 이해
        ******************************
        
    """.trimIndent()
    )
    val observer5 = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }

        override fun onNext(t: String) {
            println("Next >> $t")
        }

        override fun onError(e: Throwable) {
            println("Error occured >> ${e.message}")
        }

        override fun onComplete() {
            println("All completed")
        }
    }

    val myList = listOf("String 4", "String 5", "String 11", "String 22")
    val observable5 = myList.toObservable()
    observable5.subscribe(observer5)

    println(
        """
        
        ******************************
        Observable.just 메서드 이해
        ******************************
        
    """.trimIndent()
    )

    val observer6 = object : Observer<Any> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }

        override fun onNext(t: Any) {
            println("Next >> $t")
        }

        override fun onError(e: Throwable) {
            println("Error occured >> ${e.message}")
        }

        override fun onComplete() {
            println("All completed")
        }
    }

    Observable.just("A String").subscribe(observer6)
    Observable.just(345).subscribe(observer6)
    Observable.just(
        listOf("String 7", "String 8", "String 11", "String 22")
    ).subscribe(observer6)
    Observable.just(
        mapOf(
            Pair("key 1", "value 1"),
            Pair("key 2", "value 3"),
            Pair("key 9", "value 9")
        )
    ).subscribe(observer6)
    Observable.just(arrayListOf(3, 4, 7, 8, 9)).subscribe(observer6)
    Observable.just("aa", "bbb", "ggg").subscribe(observer6)

    println(
        """
        
        ******************************
        Observable의 다른 팩토리 메서드
        ******************************
        
    """.trimIndent()
    )

    val observer7 = object : Observer<Any> {
        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }

        override fun onNext(t: Any) {
            println("Next >> $t")
        }

        override fun onError(e: Throwable) {
            println("Error occured >> ${e.message}")
        }

        override fun onComplete() {
            println("All completed")
        }
    }

    Observable.range(1, 10).subscribe(observer7)
    Observable.empty<String>().subscribe(observer7)

    // 다른 처리를 빨리 진햋위해 아래 수행을 주석처리
//    runBlocking {
//        Observable.interval(300, TimeUnit.MILLISECONDS).subscribe(observer7)
//        delay(1400)
//        Observable.timer(400, TimeUnit.MILLISECONDS).subscribe(observer7)
//        delay(400)
//    }

    println(
        """
        
        ******************************
        구독과 해지        
        ******************************
        
    """.trimIndent()
    )

    val observable8 = Observable.range(1, 5)

    observable8.subscribe(
        { println("Next >> $it") },
        { println("Error occured >> ${it.message}") },
        { println("Done") },
        { println("[ New Subscription ]") }
    )

    val observer8 = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            println("[ New Subscription ]")
        }

        override fun onNext(t: Int) {
            println("Next >> $t")
        }

        override fun onError(e: Throwable) {
            println("Error occured >> ${e.message}")
        }

        override fun onComplete() {
            println("All completed")
        }
    }
    observable8.subscribe(observer8)

    // Disposable 인터페이스의 인스턴스를 사용해 주어진 시간에 배출을 멈출 수 있다.
    runBlocking {
        val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
        val observer = object : Observer<Long> {

            lateinit var disposable: Disposable

            override fun onNext(t: Long) {
                println("Received $t")
                if (t >= 10 && !disposable.isDisposed) {
                    disposable.dispose()
                    println("Disposed")
                }
            }

            override fun onError(e: Throwable) {
                println("Error occured >> ${e.message}")
            }

            override fun onComplete() {
                println("All completed")
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                println("[ New Subscription ]")
            }
        }

        // 다른 처리를 빨리 진햋위해 아래 수행을 주석처리
//        observable.subscribe(observer)
//        delay(1400)
    }

    println(
        """
        
        ******************************
        콜드 옵저버블
        ******************************
        
    """.trimIndent()
    )

    val oble = listOf("aaa", "ccc", "eeee", "hhhh").toObservable()
    oble.subscribe(
        { println("Next >> $it") },
        { println("Error occured >> ${it.message}") },
        { println("Done") },
        { println("[ New Subscription ]") })

    oble.subscribe(
        { println("Next >> $it") },
        { println("Error occured >> ${it.message}") },
        { println("Done") },
        { println("[ New Subscription ] 2") }
    )

    println(
        """
        
        ******************************
        핫 옵저버블
        ******************************
        
    """.trimIndent()
    )
/*
    val connectableObservable = listOf("qwww", "weee", "errr", "rtt").toObservable().publish()
    connectableObservable.subscribe {
        println("Subcription 1 : $it")
    }
    connectableObservable.map(String::reversed).subscribe {
        println("Subcription 2 : $it")
    }
    connectableObservable.connect()
    connectableObservable.subscribe {
        println("Subcription 3 : $it")
    }

    val connObservable = Observable.interval(100, TimeUnit.MILLISECONDS).publish()
    connObservable.subscribe {
        println("Subcription 1 > $it")
    }
    connObservable.subscribe {
        println("Subcription 2 > $it")
    }
    connObservable.connect()
    runBlocking {
        delay(500)
    }
    connObservable.subscribe {
        println("Subcription 3 > $it")
    }
    runBlocking {
        delay(400)
    }
*/

    // Subjects
    val subjectObservable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val subject = PublishSubject.create<Long>()
    subjectObservable.subscribe(subject)
    // 다른 처리를 빨리 진햋위해 아래 수행을 주석처리
//    subject.subscribe {
//        println("Received 1 >> $it")
//    }
//    runBlocking {
//        delay(300)
//    }
//    subject.subscribe {
//        println("Received 2 >> $it")
//    }
//    runBlocking {
//        delay(200)
//    }

    println(
        """
        
        ******************************
        AsuncSubject
        ******************************
        
    """.trimIndent()
    )

//    val oble1 = Observable.just(1, 2, 4, 5)
    val subject1 = AsyncSubject.create<Int>()
    subject1.onNext(8)
    subject1.onNext(9)
    subject1.onNext(0)
    // subject와 함께 옵저버블 인스턴스에 가입한 다음
//    oble1.subscribe(subject1)
    // Subject 인스턴스에 람다를 사용해 가입
    subject1.subscribe(
        { println("1 Next  >> $it") },
        { println("1 Error >> ${it.printStackTrace()}") },
        { println("1 Done  <<") }
    )
    subject1.onNext(11)
    subject1.onNext(12)
    subject1.subscribe(
        { println("2 Next  >> $it") },
        { println("2 Error >> ${it.printStackTrace()}") },
        { println("2 Done  <<") }
    )
    subject1.onComplete()

    println(
        """
        
        ******************************
        BehaviorSubject
        ******************************
        
    """.trimIndent()
    )

    val subject2 = BehaviorSubject.create<Int>()
    subject2.onNext(4)
    subject2.onNext(5)
    subject2.onNext(6)
    subject2.onNext(7)
    subject2.subscribe(
        { println("1 Next  >> $it") },
        { println("1 Error >> ${it.printStackTrace()}") },
        { println("1 Done  <<") }
    )

    subject2.onNext(8)
    subject2.onNext(9)
    subject2.subscribe(
        { println("2 Next  >> $it") },
        { println("2 Error >> ${it.printStackTrace()}") },
        { println("2 Done  <<") }
    )
    subject2.onNext(10)
    subject2.onNext(11)
    subject2.onComplete()

    println(
        """
        
        ******************************
        ReplaySubject 이해
        ******************************
        
    """.trimIndent()
    )

    val subject3 = ReplaySubject.create<Int>()
    subject3.onNext(5)
    subject3.onNext(6)
    subject3.onNext(7)
    subject3.subscribe(
        { println("1 Next  >> $it") },
        { println("1 Error >> ${it.printStackTrace()}") },
        { println("1 Done  <<") }
    )

    subject3.onNext(8)
    subject3.onNext(9)
    subject3.subscribe(
        { println("2 Next  >> $it") },
        { println("2 Error >> ${it.printStackTrace()}") },
        { println("2 Done  <<") }
    )
    subject3.onNext(10)
    subject3.onNext(11)
    subject3.onComplete()

    println(
        """
        
        ******************************
        
        ******************************
        
    """.trimIndent()
    )


    println(
        """
        
        ******************************
        
        ******************************
        
    """.trimIndent()
    )

}