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
        calculator.handleInput(line)
    } while (line != null && !line.toLowerCase().contains("exit"))

}

class ReactiveCalculator(a: Int, b: Int) {
    internal val subjectAdd: io.reactivex.subjects.Subject<Pair<Int, Int>> =
        io.reactivex.subjects.PublishSubject.create()
    internal val subjectSub: io.reactivex.subjects.Subject<Pair<Int, Int>> =
        io.reactivex.subjects.PublishSubject.create()
    internal val subjectMult: io.reactivex.subjects.Subject<Pair<Int, Int>> =
        io.reactivex.subjects.PublishSubject.create()
    internal val subjectDiv: io.reactivex.subjects.Subject<Pair<Int, Int>> =
        io.reactivex.subjects.PublishSubject.create()

    internal val subjectCalc: io.reactivex.subjects.Subject<ReactiveCalculator> =
        io.reactivex.subjects.PublishSubject.create()

    internal var nums: Pair<Int, Int> = Pair(0, 0)

    init {
        nums = Pair(a, b)

        subjectAdd.map({ it.first + it.second }).subscribe({ println("Add = $it") })
        subjectSub.map({ it.first - it.second }).subscribe({ println("Substract = $it") })
        subjectMult.map({ it.first * it.second }).subscribe({ println("Multiply = $it") })
        subjectDiv.map({ it.first / (it.second * 1.0) }).subscribe({ println("Divide = $it") })

        subjectCalc.subscribe({
            with(it) {
                calculateAddition()
                calculateSubstraction()
                calculateMultiplication()
                calculateDivision()
            }
        })

        subjectCalc.onNext(this)
    }


    fun calculateAddition() {
        subjectAdd.onNext(nums)
    }

    fun calculateSubstraction() {
        subjectSub.onNext(nums)
    }

    fun calculateMultiplication() {
        subjectMult.onNext(nums)
    }

    fun calculateDivision() {
        subjectDiv.onNext(nums)
    }

    fun modifyNumbers(a: Int = nums.first, b: Int = nums.second) {
        nums = Pair(a, b)
        subjectCalc.onNext(this)

    }

    fun handleInput(inputLine: String?) {
        if (!inputLine.equals("exit")) {
            val pattern: java.util.regex.Pattern = java.util.regex.Pattern.compile("([a|b])(?:\\s)?=(?:\\s)?(\\d*)");

            var a: Int? = null
            var b: Int? = null

            val matcher: java.util.regex.Matcher = pattern.matcher(inputLine)

            if (matcher.matches() && matcher.group(1) != null && matcher.group(2) != null) {
                if (matcher.group(1).toLowerCase().equals("a")) {
                    a = matcher.group(2).toInt()
                } else if (matcher.group(1).toLowerCase().equals("b")) {
                    b = matcher.group(2).toInt()
                }
            }


            when {
                a != null && b != null -> modifyNumbers(a, b)
                a != null -> modifyNumbers(a = a)
                b != null -> modifyNumbers(b = b)
                else -> println("Invalid Input")

            }
        }
    }

}

fun isEven(n: Int): Boolean = ((n % 2) == 0)
