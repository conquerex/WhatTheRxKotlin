import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * @author Jongkook
 * @date : 2020/12/02
 */

fun main(args: Array<String>) {
    val sum = { x: Int, y: Int -> x + y }
    println("Sum ${sum(12, 14)}")
    val anonymousMult = { x: Int -> (Random.nextInt(15) + 1) * x }
    println("random output ${anonymousMult(2)}")

    // 순수 함수
    println("named pure func square = ${square(3)}")
    val qube = { n: Int -> n * n * n }
    println("lamda pure func qube = ${square(3)}")

    // 고차 함수
    highOrderFunc(12) { a: Int -> a.isEven() }
    highOrderFunc(19) { a: Int -> a.isEven() }

    // 인라인 함수
    for (i in 1..10) {
        println("$i output ${doSomeStuff(i)}")
    }

    // ReactiveCalculator 클래스에 함수형 프로그래밍 적용
    println("Initial Out put with a = 15, b = 10")
    var cal = ReactiveCalculator(15, 10)
    println("Enter a = <number> or b = <number> in separate lines\nexit to exit the program")
    var line: String?
//    do {
//        line = readLine()
//        cal.handleInput(line)
//    } while (line != null && !line.toLowerCase().contains("exit"))

    // 코루틴 시작하기
    runBlocking {
//        val exeTime = longRunningTask()
//        println("Execution Time is $exeTime")
    }

    // 코루틴 시작하기 - 비동기
    val time2 = GlobalScope.async {
        println("********************")
        longRunningTask()
    }

    println("***** Print after async")
    runBlocking {
//        println("***** Printing time ${time2.await()}")
    }

    // 시퀀스 생성하기 - 피보나치 프로그램
    val fibonacciSeries = sequence<Int> {
        var a = 0
        var b = 1
        yield(a)
        yield(b)

        while (true) {
            val c = a + b
            yield(c)
            a = b
            b = c
        }
    }

    println(fibonacciSeries.take(10).joinToString(","))

    // 코루틴을 사용한 ReactiveCalculator 클래스
    println("Initial Out put with a = 10, b = 4")
    var cal2 = ReactiveCalculator(10, 4)
    println("Enter a = <number> or b = <number> in separate lines\nexit to exit the program")
    var line2: String?
    do {
        line2 = readLine()
        GlobalScope.async {
            cal2.handleInput(line2)
        }
    } while (line2 != null && !line2.toLowerCase().contains("exit"))

}

suspend fun longRunningTask(): Long {
    val time = measureTimeMillis {
        println("Plz wait....")
//        delay(2, TimeUnit.SECONDS) // 왜 안되지???
        delay(2000)
        println("Delay over...")
    }
    return time
}

inline fun doSomeStuff(a: Int = 0) = a + (a * a)

inline fun Int.isEven(): Boolean = (this % 2) == 0

inline fun highOrderFunc(a: Int, validityCheckFunc: (a: Int) -> Boolean) {
    if (validityCheckFunc(a)) {
        println("a $a is Valid.")
    } else {
        println("a $a is Invalid.")
    }
}

fun square(n: Int): Int {
    return n * n
}