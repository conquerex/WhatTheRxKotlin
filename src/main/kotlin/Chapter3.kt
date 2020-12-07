import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable
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

    println("""
        
        ******************************
        Observable.from 메서드 이해
        ******************************
        
    """.trimIndent())

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

    println("""
        
        ******************************
        toObservable의 확장 함수 이해
        ******************************
        
    """.trimIndent())
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

    println("""
        
        ******************************
        Observable.just 메서드 이해
        ******************************
        
    """.trimIndent())


}