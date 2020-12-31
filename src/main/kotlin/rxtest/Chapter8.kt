package rxtest

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * @author Jongkook
 * @date : 2020/12/31
 */

fun main(args: Array<String>) {

    println(
        """
        
        ******************************
        구독자 차단
        ******************************
        
    """.trimIndent()
    )

    Observable.range(1, 10)
        .subscribeOn(Schedulers.computation())
        .subscribe { println("Rec $it") }
    runBlocking { delay(10) }




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

class Chapter8 {
    fun add(a: Int, b: Int): Int = a + b
}

fun mult(a: Int, b: Int) = a * b