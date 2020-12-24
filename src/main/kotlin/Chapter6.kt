import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.toObservable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

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

    Observable.amb(listOf(ob5, ob6))
        .subscribe { println("Rec $it") }
    runBlocking { delay(1500) }



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