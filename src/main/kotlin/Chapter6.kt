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

    Observable.combineLatest(observable5, observable6, BiFunction { t1, t2 -> "$t1 $t2" })
        .subscribe { println("Rec $it") }

    runBlocking { delay(1100) }


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