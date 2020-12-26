import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * @author Jongkook
 * @date : 2020/12/23
 */

fun main(args: Array<String>) {

    println(
        """
        
        ******************************
        startWith 연산자
        ******************************
        
    """.trimIndent()
    )

    Observable.range(5, 10)
        .startWith(listOf(1, 2, 3, 4))
        .subscribe {
            println("Rec $it")
        }

    println("startWith another source Producer")

    Observable.range(5, 10)
        .startWith(Observable.just(1, 2, 3, 4))
        .subscribe {
            println("Rec $it")
        }



    println(
        """
        
        ******************************
        배출을 집핑하기 : zip 연산자
        ******************************
        
    """.trimIndent()
    )

    val observable1 = Observable.range(1, 5)
    val observable2 = Observable.range(11, 5)
    Observable.zip(observable1, observable2, BiFunction { t1, t2 -> t1 + t2 })
        .subscribe {
            println("Rec $it")
        }


    val observable3 = listOf("aa", "bb", "c", "ddd", "e").toObservable()
    observable1.zipWith(observable3, { e1: Int, e2: String -> "$e2 $e1" })
        .subscribe { println("Rec $it") }


    println(
        """
        
        ******************************
        combineLatest 연산자
        ******************************
        
    """.trimIndent()
    )

//    val observable5 = Observable.interval(100, TimeUnit.MILLISECONDS)
//    val observable6 = Observable.interval(250, TimeUnit.MILLISECONDS)
//
//    Observable.zip(observable5, observable6, BiFunction { t1, t2 -> "$t1 $t2" })
//        .subscribe { println("Rec $it") }
//
//    runBlocking { delay(1100) }

    val observable5 = Observable.interval(100, TimeUnit.MILLISECONDS)
    val observable6 = Observable.interval(250, TimeUnit.MILLISECONDS)

//    Observable.combineLatest(observable5, observable6, BiFunction { t1, t2 -> "$t1 $t2" })
//        .subscribe { println("Rec $it") }
//
//    runBlocking { delay(1100) }


    println(
        """
        
        ******************************
        옵저버블/플로어블 병합 : merge 연산자
        ******************************
        
    """.trimIndent()
    )

    val observable7 = listOf("aa", "bbb", "c", "dd").toObservable()
    val observable8 = listOf("qq", "wwww", "ee", "r").toObservable()

    Observable.merge(observable7, observable8)
        .subscribe { println("Rec $it") }


    val ob1 = Observable.interval(500, TimeUnit.MILLISECONDS)
        .map { "Observable 1 $it" }
    val ob2 = Observable.interval(100, TimeUnit.MILLISECONDS)
        .map { "Observable 2 $it" }
//    Observable.merge(ob1, ob2)
//        .subscribe { println(">> Rec $it") }
//    runBlocking { delay(1500) }

    val test1 = listOf("a", "b", "c").toObservable()
    val test2 = listOf("d", "e", "f", "g").toObservable()
    val test3 = listOf("h", "i", "j", "k").toObservable()
    val test4 = listOf("l", "m", "n", "o").toObservable()
    val test5 = listOf("p", "q", "r", "s").toObservable()
    val test6 = listOf("t", "u").toObservable()
    Observable.mergeArray(test1, test2, test3, test4, test5, test6)
        .subscribe { println("Rec $it") }

    val ob3 = listOf("aa", "bb", "c").toObservable()
    val ob4 = listOf("ddd", "e", "f").toObservable()
    ob3.mergeWith(ob4).subscribe {
        println(">> Rec $it")
    }


    println(
        """
        
        ******************************
        프로듀서 이어 붙이기(옵저버블/플로어블)
        ******************************
        
    """.trimIndent()
    )





    println(
        """
        
        ******************************
        프로듀서 이어 붙이기(옵저버블/플로어블)
        ******************************
        
    """.trimIndent()
    )

//    val ob5 = Observable.interval(500, TimeUnit.MILLISECONDS)
//        .take(2)
//        .map {
//            "Observable 1 > $it"
//        }
//    val ob6 = Observable.interval(100, TimeUnit.MILLISECONDS)
//        .map {
//            "Observable 2 > $it"
//        }

//    Observable.concat(ob5, ob6)
//        .subscribe {
//            println("Rec $it")
//        }
//    runBlocking { delay(1500) }


    println(
        """
        
        ******************************
        프로듀서 임의 결합
        ******************************
        
    """.trimIndent()
    )

    val ob5 = Observable.interval(500, TimeUnit.MILLISECONDS)
        .map {
            "Observable 1 > $it"
        }
    val ob6 = Observable.interval(100, TimeUnit.MILLISECONDS)
        .map {
            "Observable 2 > $it"
        }

//    Observable.amb(listOf(ob5, ob6))
//        .subscribe { println("Rec $it") }
//    runBlocking { delay(1500) }


    println(
        """
        
        ******************************
        그룹핑        
        ******************************
        
    """.trimIndent()
    )

    val ob7 = Observable.range(1, 20)
    ob7.groupBy { it % 5 }
        .blockingSubscribe {
            println("Key ${it.key} ")
            it.subscribe { println("Rec --> $it") }
        }


    println(
        """
        
        ******************************
        flatMap, concatMap 세부 사항
        ******************************
        
    """.trimIndent()
    )

    Observable.range(1, 10)
        .flatMap {
            val randDelay = Random.nextInt(10)
            return@flatMap Observable.just(it)
                .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)
        }.blockingSubscribe {
            println("Rec $it")
        }

    println("----")

    Observable.range(1, 10)
        .concatMap {
            val randDelay = Random.nextInt(10)
            return@concatMap Observable.just(it)
                .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)
        }.blockingSubscribe {
            println("Rec $it")
        }



    println(
        """
        
        ******************************
        switchMap 연산자 이해
        ******************************
        
    """.trimIndent()
    )

//    Observable.range(1, 10)
//        .switchMap {
//            val randDelay = Random.nextInt(10)
//            return@switchMap Observable.just(it)
//        }.blockingSubscribe {
//            println("Rec $it")
//        }
//
//    println("--- with delay ---")
//
//    Observable.range(1, 10)
//        .switchMap {
//            val randDelay = Random.nextInt(10)
//            return@switchMap Observable.just(it)
//                .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)
//        }.blockingSubscribe {
//            println("Rec $it")
//        }

    Observable.range(1, 10)
        .switchMap {
            val randDelay = Random.nextInt(10)
            if (it % 3 == 0)
                Observable.just(it)
            else
                Observable.just(it)
                    .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)
        }.blockingSubscribe {
            println("Rec $it")
        }



    println(
        """
        
        ******************************
        배출 건너뛰기 (skip, skipLast, skipUntil, skipWhile)
        ******************************
        
    """.trimIndent()
    )

//    val obs1 = Observable.range(1, 10)
//    obs1.skip(5)
//        .subscribe(object : Observer<Int> {
//            override fun onSubscribe(d: Disposable) {
//                println(">>>>> starting skip(count)")
//            }
//
//            override fun onNext(t: Int) {
//                println("Rec $t")
//            }
//
//            override fun onError(e: Throwable) {
//                println("Error $e")
//            }
//
//            override fun onComplete() {
//                println("Complete <<<<")
//            }
//        })
//
//    val obs2 = Observable.interval(100, TimeUnit.MILLISECONDS)
//    obs2.skip(400, TimeUnit.MILLISECONDS)
//        .subscribe(object : Observer<Long> {
//            override fun onSubscribe(d: Disposable) {
//                println(">>>>> starting skip(time)")
//            }
//
//            override fun onNext(t: Long) {
//                println("Rec $t")
//            }
//
//            override fun onError(e: Throwable) {
//                println("Error $e")
//            }
//
//            override fun onComplete() {
//                println("Complete <<<<")
//            }
//        })

//    runBlocking { delay(1000) }
//
//    val obs3 = Observable.range(1, 10)
//    obs3.skipLast(5)
//        .subscribe(object : Observer<Int> {
//            override fun onSubscribe(d: Disposable) {
//                println(">>>>> starting skipLast(count)")
//            }
//
//            override fun onNext(t: Int) {
//                println("Rec $t")
//            }
//
//            override fun onError(e: Throwable) {
//                println("Error $e")
//            }
//
//            override fun onComplete() {
//                println("Complete <<<<")
//            }
//        })
//
//    val obs4 = Observable.range(1, 10)
//    obs4.skipWhile { item -> item < 3 }
//        .subscribe(object : Observer<Int> {
//            override fun onSubscribe(d: Disposable) {
//                println(">>>>> starting skipWhile(count)")
//            }
//
//            override fun onNext(t: Int) {
//                println("Rec $t")
//            }
//
//            override fun onError(e: Throwable) {
//                println("Error $e")
//            }
//
//            override fun onComplete() {
//                println("Complete <<<<")
//            }
//        })

    val obs5 = Observable.interval(100, TimeUnit.MILLISECONDS)
    val obs6 = Observable.timer(400, TimeUnit.MILLISECONDS)

//    obs5.skipUntil(obs6)
//        .subscribe(object : Observer<Long> {
//            override fun onSubscribe(d: Disposable) {
//                println(">>>>> starting skipUntil")
//            }
//
//            override fun onNext(t: Long) {
//                println("Received $t")
//            }
//
//            override fun onError(e: Throwable) {
//                println("Error $e")
//            }
//
//            override fun onComplete() {
//                println("Complete <<<<")
//            }
//        })
//
//    runBlocking { delay(1200) }


    println(
        """
        
        ******************************
        take 연산자 (take, takeLast, takeWhile, takeUntil)
        ******************************
        
    """.trimIndent()
    )

    val sample1 = Observable.range(1, 10)
    sample1.take(5)
        .subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                println(">>>>> starting take(5)")
            }

            override fun onNext(t: Int) {
                println("Rec $t")
            }

            override fun onError(e: Throwable) {
                println("Error $e")
            }

            override fun onComplete() {
                println("Complete <<<<")
            }
        })

    val sample2 = Observable.interval(100, TimeUnit.MILLISECONDS)
//    sample2.take(400, TimeUnit.MILLISECONDS)
//        .subscribe(object : Observer<Long> {
//            override fun onSubscribe(d: Disposable) {
//                println(">>>>> starting take(300, TimeUnit.MILLISECONDS)")
//            }
//
//            override fun onNext(t: Long) {
//                println("Rec $t")
//            }
//
//            override fun onError(e: Throwable) {
//                println("Error $e")
//            }
//
//            override fun onComplete() {
//                println("Complete <<<<")
//            }
//        })
//
//    runBlocking { delay(1000) }

    val sample3 = Observable.range(1, 10)
    sample3.takeLast(3)
        .subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                println(">>>>> starting takeLast(3)")
            }

            override fun onNext(t: Int) {
                println("Rec $t")
            }

            override fun onError(e: Throwable) {
                println("Error $e")
            }

            override fun onComplete() {
                println("Complete <<<<")
            }
        })

    val sample4 = Observable.range(1, 10)
    sample4.takeWhile { item -> item < 4 }
        .subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                println(">>>>> starting takeWhile")
            }

            override fun onNext(t: Int) {
                println("Rec $t")
            }

            override fun onError(e: Throwable) {
                println("Error $e")
            }

            override fun onComplete() {
                println("Complete <<<<")
            }
        })



    println(
        """
        
        ******************************
        에러 처리 연산자
        ******************************
        
    """.trimIndent()
    )

//    Observable.just(1, 2, 3, 4, 5)
//        .map { it / (3 - it) }
//        .subscribe { println("Rec -- $it") }

//    Observable.just(1, 2, 3, 4, 5)
//        .map { it / (3 - it) }
//        .onErrorReturn { -1 }
//        .subscribe { println("Rec -- $it") }
//
//    Observable.just(1, 2, 3, 4, 5)
//        .map { it / (3 - it) }
//        .onErrorResumeNext(Observable.range(10, 5))
//        .subscribe { println("Rec -- $it") }

    Observable.just(1, 2, 3, 4, 5)
        .map { it / (3 - it) }
        .retry(3)
        .subscribeBy(
            onNext = { println("Rec -- $it") },
            onError = { println("Error") }
        )

    println("\n ===== With predicate ===== \n")

    var retryCount = 0;
    Observable.just(1, 2, 3, 4, 5)
        .map { it / (3 - it) }
        .retry { _, _ ->
            (++retryCount) < 3
        }
        .subscribeBy(
            onNext = { println("Rec -- $it") },
            onError = { println("Error") }
        )


    println(
        """
        
        ******************************
        HTTP 예제
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