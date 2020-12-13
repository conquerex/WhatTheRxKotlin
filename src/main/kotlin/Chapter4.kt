import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

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
            println("Received $it")
            runBlocking {
                delay(200)
            }
        }

    runBlocking {
        delay(1500)
    }

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