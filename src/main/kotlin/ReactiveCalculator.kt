/**
 * @author Jongkook
 * @date : 2020/12/04
 */

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