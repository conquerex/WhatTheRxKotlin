import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

/**
 * @author Jongkook
 * @date : 2020/12/27
 */

fun main(args: Array<String>) {

    println(
        """
        
        ******************************
        스케줄러 종류
        ******************************
        
    """.trimIndent()
    )

//    Observable.range(1,10)
//        .subscribe {
//            runBlocking { delay(200) }
//            println("Obs1 Item Rec $it")
//        }
//
//    Observable.range(21, 10)
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Obs2 Item Rec $it")
//        }


//    Observable.range(1,10)
//        .subscribeOn(Schedulers.computation())
//        .subscribe {
//            runBlocking { delay(200) }
//            println("Obs1 Item Rec $it")
//        }
//
//    Observable.range(21, 10)
//        .subscribeOn(Schedulers.computation())
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Obs2 Item Rec $it")
//        }
//
//    runBlocking { delay(2100) }


    println(
        """
        
        ******************************
        Schedulers.trampoline()
        ******************************
        
    """.trimIndent()
    )

//    GlobalScope.async {
//        Observable.range(1, 10)
////            .subscribeOn(Schedulers.single())
//            .subscribeOn(Schedulers.trampoline())
//            .subscribe {
//                runBlocking { delay(200) }
//                println("Obs1 item rec $it")
//            }
//
//        Observable.range(21, 10)
////            .subscribeOn(Schedulers.single())
//            .subscribeOn(Schedulers.trampoline())
//            .subscribe {
//                runBlocking { delay(100) }
//                println("Obs2 item rec $it")
//            }
//
//        for (i in 1..10) {
//            delay(100)
//            println("Blocking thread $i")
//        }
//    }
//
//    runBlocking { delay(6000) }


    println(
        """
        
        ******************************
        Schedulers.from
        ******************************
        
    """.trimIndent()
    )

    val executor = Executors.newFixedThreadPool(2)
    val scheduler = Schedulers.from(executor)

//    Observable.range(1, 10)
//        .subscribeOn(scheduler)
//        .subscribe {
//            runBlocking { delay(200) }
//            println("Obs1 item rec $it >> ${Thread.currentThread().name}")
//        }
//
//    Observable.range(21, 10)
//        .subscribeOn(scheduler)
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Obs2 item rec $it >> ${Thread.currentThread().name}")
//        }
//
//    Observable.range(51, 10)
//        .subscribeOn(scheduler)
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Obs3 item rec $it >> ${Thread.currentThread().name}")
//        }
//
//    runBlocking { delay(5000) }


    println(
        """
        
        ******************************
        구독 시 스레드 변경 : subscribeOn 연산자
        ******************************
        
    """.trimIndent()
    )

    listOf("1", "2", "3", "4", "6")
        .toObservable()
        .map { item ->
            println("mapping $item -- ${Thread.currentThread().name}")
            return@map item.toInt()
        }.subscribe { item ->
            println("rec $item >> ${Thread.currentThread().name}")
        }

    listOf("0", "2", "3", "4", "6")
        .toObservable()
        .map {
            println("mapping $it -- ${Thread.currentThread().name}")
            return@map it.toInt()
        }.subscribeOn(Schedulers.computation())
        .subscribe {
            println("rec $it >> ${Thread.currentThread().name}")
        }

    runBlocking { delay(1000) }


    println(
        """
        
        ******************************
        다른 스레드에서 관찰 : observeOn 연산자
        ******************************
        
    """.trimIndent()
    )

    listOf("1", "2", "3", "4", "6")
        .toObservable()
        .observeOn(Schedulers.computation())
        .map {
            println("mapping $it -- ${Thread.currentThread().name}")
            return@map it.toInt()
        }
        .observeOn(Schedulers.io())
        .subscribe {
            println("rec $it >> ${Thread.currentThread().name}")
        }

    runBlocking { delay(1000) }

}