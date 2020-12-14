import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * @author Jongkook
 * @date : 2020/12/13
 */

fun main(args: Array<String>) {
    println(
        """
        
        ******************************
        백프레셔 이해
        ******************************
        
    """.trimIndent()
    )

    val observable1 = Observable.just(1, 2, 3, 4, 5)
    val subject = BehaviorSubject.create<Int>()
    // 다른 처리를 빨리 진햋위해 아래 수행을 주석처리
//    subject.observeOn(Schedulers.computation())
//        .subscribe {
//            println("Subs 1 Received $it")
//            runBlocking {
//                delay(200)
//            }
//        }
//
//    subject.observeOn(Schedulers.computation())
//        .subscribe {
//            println("Subs 2 Received $it")
//        }
//    observable1.subscribe(subject)
//    runBlocking {
//        delay(500)
//    }

    observable1.map { MyItem(it) }
        .observeOn(Schedulers.computation())
        .subscribe {
//            println("Received $it")
//            runBlocking {
//                delay(200)
//            }
        }

    // 다른 처리를 빨리 진햋위해 아래 수행을 주석처리
//    runBlocking {
//        delay(1500)
//    }

    println(
        """
        
        ******************************
        플로어블 Flowables
        ******************************
        
    """.trimIndent()
    )

//    Observable.range(1, 1000)
//        .map { MyItem(it) }
//        .observeOn(Schedulers.computation())
//        .subscribe({
//            print("Received $it\t")
//            runBlocking {
//                delay(50)
//            }
//        }, {
//            it.printStackTrace()
//        })
//    runBlocking { delay(10000) }

//    Flowable.range(1, 1000)
//        .map { MyItem(it) }
//        .observeOn(Schedulers.io())
//        .subscribe({
//            print("Received $it\t")
//            runBlocking {
//                delay(50)
//            }
//        }, {
//            it.printStackTrace()
//        })
//    runBlocking { delay(5000) }


    println(
        """
        
        ******************************
        플로어블과 구독자
        ******************************
        
    """.trimIndent()
    )


//    Flowable.range(1, 1000)
//        .map { MyItem(it) }
//        .observeOn(Schedulers.io())
//        .subscribe(object : Subscriber<MyItem> {
//            override fun onSubscribe(s: Subscription?) {
//                s!!.request(Long.MAX_VALUE)
//            }
//
//            override fun onNext(t: MyItem?) {
//                runBlocking { delay(50) }
//                println("Subscriber received + $t")
//            }
//
//            override fun onError(t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onComplete() {
//                println("Done!!!")
//                runBlocking {
//                    delay(5000)
//                }
//            }
//        })


//    Flowable.range(1, 15)
//        .map { MyItem(it) }
//        .observeOn(Schedulers.io())
//        .subscribe(object : Subscriber<MyItem> {
//
//            lateinit var subscription: Subscription
//
//            override fun onSubscribe(s: Subscription?) {
//                this.subscription = s!!
//                subscription.request(5)
//            }
//
//            override fun onNext(t: MyItem?) {
//                runBlocking {
//                    delay(50)
//                }
//
//                println("Subscriber received $t")
//                if (t!!.id == 5) {
//                    println("Requesting two more")
//                    subscription.request(2)
//                }
//            }
//
//            override fun onError(t: Throwable?) {
//                t!!.printStackTrace()
//            }
//
//            override fun onComplete() {
//                println("Done!!!")
//            }
//        })
//
//    runBlocking { delay(2000) }


    println(
        """
        
        ******************************
        처음부터 플로어블 생성하기
        ******************************
        
    """.trimIndent()
    )

//    val observer2 = object : Observer<Int> {
//        override fun onSubscribe(d: Disposable) {
//            println("[ New Subscription ]")
//        }
//
//        override fun onNext(t: Int) {
//            println("Next >> $t")
//        }
//
//        override fun onError(e: Throwable) {
//            println("Error occured >> ${e.message}")
//        }
//
//        override fun onComplete() {
//            println("All completed")
//        }
//    } // Observer 생성
//
//    val observable2 = Observable.create<Int> {
//        for (i in 1..10) {
//            it.onNext(i)
//        }
//        it.onComplete()
//    }
//
////    observable2.subscribe(observer2)
//
//    val subscriber2 = object : Subscriber<Int> {
//        override fun onSubscribe(s: Subscription?) {
//            println("[ New Subscription ]")
//            s!!.request(10)
//        }
//
//        override fun onNext(t: Int) {
//            println("Next >> $t")
//        }
//
//        override fun onError(e: Throwable) {
//            println("Error occured >> ${e.message}")
//        }
//
//        override fun onComplete() {
//            println("All completed")
//        }
//    }
//
//    val flowable = Flowable.create<Int> ({
//        for (i in 1..10) {
//            it.onNext(i)
//        }
//        it.onComplete()
//    }, BackpressureStrategy.BUFFER)
//
//    flowable.observeOn(Schedulers.io())
//        .subscribe(subscriber2)
//
//    runBlocking { delay(3000) }


    println(
        """
        
        ******************************
        옵저버블로 플로어블 만들기
        ******************************
        
    """.trimIndent()
    )

    val source1 = Observable.range(1,500)
    source1.toFlowable(BackpressureStrategy.DROP)
        .map { MyItem(it) }
        .observeOn(Schedulers.io())
        .subscribe {
            print("Rec. $it;\t")
            runBlocking { delay(20) }
        }
    runBlocking { delay(5000) }


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

data class MyItem(val id: Int) {
    init {
        println("MyItem created $id")
    }
}