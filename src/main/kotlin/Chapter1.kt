import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

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

    // ReactiveEvenOdd 프로그램
    var subject: Subject<Int> = PublishSubject.create()
    subject.map { isEven(it) }.subscribe {
        println("Num ---> ${if (it) "Even" else "Odd"}")
    }
    subject.onNext(7)
    subject.onNext(4)

    // ReactiveCalculator 프로젝트
    var calculator: ReactiveCalculator = ReactiveCalculator(15, 10)
    println("Enter a = <num> or b = <num> in separate lines/nexit to exit the program")
    var line: String?
    do {
        line = readLine()
//        calculator.handleInput(line)
    } while (line != null && !line.toLowerCase().contains("exit"))

}

fun isEven(n: Int): Boolean = ((n % 2) == 0)
