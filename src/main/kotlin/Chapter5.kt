import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * @author Jongkook
 * @date : 2020/12/20
 */

fun main(args: Array<String>) {

    println(
        """
        
        ******************************
        debounce 연산자
        ******************************
        
    """.trimIndent()
    )

    // 다른 처리를 빨리 진햋위해 아래 수행을 주석처리
//    createObservable()
//        .debounce(200, TimeUnit.MILLISECONDS)
//        .subscribe { println(it) }


    println(
        """
        
        ******************************
        distinct 연산자 : distinct, distinctUntilChanged
        ******************************
        
    """.trimIndent()
    )

    listOf(1, 1, 5, 5, 8, 8, 7, 1, 5)
        .toObservable()
//        .distinct()
        .distinctUntilChanged()
        .subscribe { println("Rec $it") }


    println(
        """
        
        ******************************
        elementAt 연산자
        ******************************
        
    """.trimIndent()
    )

    val observable1 = listOf(10, 1, 2, 6, 7, 8, 3, 4)
        .toObservable()

    observable1.elementAt(5)
        .subscribe { println("Rec $it") }

    observable1.elementAt(50)
        .subscribe { println("Rec $it") }


    println(
        """
        
        ******************************
        배출 필터링하기 : filter 연산자
        ******************************
        
    """.trimIndent()
    )

    Observable.range(1, 20)
        .filter {
            it % 2 == 0
        }.subscribe {
            println("Rec $it")
        }


    println(
        """
        
        ******************************
        first, last 연산자
        ******************************
        
    """.trimIndent()
    )

    val observable2 = Observable.range(1, 10)
    observable2.first(2)
        .subscribeBy { item -> println("Rec $item") }

    observable2.last(2)
        .subscribeBy { item -> println("Rec $item") }

    Observable.empty<Int>().first(2)
        .subscribeBy { item -> println("Rec $item") }



    println(
        """
        
        ******************************
        ignoreElements 연산자
        ******************************
        
    """.trimIndent()
    )

    val observable3 = Observable.range(1, 10)
    observable3.ignoreElements()
        .subscribe { println("Complete!!") }


    println(
        """
        
        ******************************
        map 연산자
        ******************************
        
    """.trimIndent()
    )

    val observable4 = listOf(9, 8, 7, 6, 5, 4, 3, 2, 1).toObservable()
    observable4.map { number ->
        "Int to String $number"
    }.subscribe { item ->
        println("Rec $item")
    }


    println(
        """
        
        ******************************
        배출 캐스팅 : cast 연산자
        ******************************
        
    """.trimIndent()
    )

    val list5 = listOf<MyItemInherit>(
        MyItemInherit(1),
        MyItemInherit(2),
        MyItemInherit(5),
        MyItemInherit(6),
        MyItemInherit(7),
        MyItemInherit(8)
    )

    list5.toObservable()
        .map { it as TestItem }
        .subscribe { println(it) }

    println("----- cast -----")

    list5.toObservable()
        .cast(TestItem::class.java)
        .subscribe {
            println(it)
        }


    println(
        """
        
        ******************************
        flatMap 연산자
        ******************************
        
    """.trimIndent()
    )

    val observable5 = listOf(8, 7, 6, 5, 4, 3, 2, 1).toObservable()
    observable5.flatMap { number ->
        Observable.just("Int to String $number")
    }.subscribe { item ->
        println("Rec $item")
    }

    val observable6 = listOf(8, 7, 6, 5, 4, 3, 2, 1).toObservable()
    observable6.flatMap { number ->
        Observable.create<String> {
            it.onNext("The number $number")
            it.onNext("number/2 ${number / 2}")
            it.onComplete()
        }
    }.subscribeBy(
        onNext = { item ->
            println("Rec $item")
        }, onComplete = {
            println("Complete!!!")
        }
    )


    println(
        """
        
        ******************************
        defaultIfEmpty 연산자
        ******************************
        
    """.trimIndent()
    )

    Observable.range(0, 10)
        .filter { it > 15 }
        .subscribe {
            println("Rec $it")
        }

    Observable.range(0, 10)
        .filter { it > 15 }
        .defaultIfEmpty(15)
        .subscribe {
            println("Rec $it")
        }


    println(
        """
        
        ******************************
        switchIfEmpty 연산자
        ******************************
        
    """.trimIndent()
    )

    Observable.range(0, 10)
        .filter { it > 15 }
        .switchIfEmpty(Observable.range(11, 10))
        .subscribe {
            println("Rec $it")
        }


    println(
        """
        
        ******************************
        startWith 연산자
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

open class TestItem(val id: Int) {
    override fun toString(): String {
        return "TestItem >> $id"
    }
}

class MyItemInherit(id: Int) : TestItem(id) {
    override fun toString(): String {
        return "MyItemInherit >> $id"
    }
}

inline fun createObservable(): Observable<String> =
    Observable.create {
        it.onNext("R")
        runBlocking { delay(120) }
        it.onNext("Re")
        runBlocking { delay(150) }
        it.onNext("Rea")
        runBlocking { delay(120) }
        it.onNext("Reac")
        runBlocking { delay(330) }
        it.onNext("React")
        runBlocking { delay(120) }
        it.onNext("Reacti")
        runBlocking { delay(220) }
        it.onNext("Reactiv")
        runBlocking { delay(150) }
        it.onNext("Reactive")
        runBlocking { delay(150) }
        it.onNext("Reactive P")
        runBlocking { delay(150) }
        it.onNext("Reactive Pr")
        runBlocking { delay(150) }
        it.onNext("Reactive Pro")
        runBlocking { delay(150) }
        it.onNext("Reactive Prog")
        runBlocking { delay(330) }
        it.onNext("Reactive Progr")
        runBlocking { delay(150) }
        it.onNext("Reactive Progra")
        runBlocking { delay(150) }
        it.onNext("Reactive Program")
        runBlocking { delay(150) }
        it.onNext("Reactive Programm")
        runBlocking { delay(150) }
        it.onNext("Reactive Programmi")
        runBlocking { delay(330) }
        it.onNext("Reactive Programmin")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming i")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming in")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming in K")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming in Ko")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming in Kot")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming in Kotl")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming in Kotli")
        runBlocking { delay(120) }
        it.onNext("Reactive Programming in Kotlin")
        runBlocking { delay(330) }
    }