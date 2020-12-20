import io.reactivex.Observable
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



    println(
        """
        
        ******************************
        
        ******************************
        
    """.trimIndent()
    )

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