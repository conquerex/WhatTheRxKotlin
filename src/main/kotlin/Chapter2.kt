import kotlin.random.Random

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