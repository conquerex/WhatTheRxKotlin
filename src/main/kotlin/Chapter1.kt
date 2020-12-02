import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

/**
 * @author Jongkook
 * @date : 2020/12/02
 */

fun main(args: Array<String>) {
    var number = 4
    var isEven = isEven(number)
    println("The number is " + (if (isEven) "Even" else "Odd"))
    number = 9
    println("The number is " + (if (isEven) "Even" else "Odd"))

    // RxJava의 푸시 메커니즘과 풀 메커니즘 비교
    var list: List<Any> = listOf("One", 2, "Three", 4.5, 6.0f)
    var iterator = list.iterator()
    while (iterator.hasNext()) {
        println(iterator.next())
    }

    // RxKotlin 시작하기
    var observable: Observable<Any> = list.toObservable()
    observable.subscribeBy(
        onNext = { println(it) },
        onError = { it.printStackTrace() },
        onComplete = { println("Done!!!") }
    )
}

fun isEven(n: Int): Boolean = ((n % 2) == 0)
