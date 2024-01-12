import kotlin.math.log10

fun inputNumber(): Int {
    val x: Int
    val str = readln()
    if (str.matches("^[1-9](\\d+)?\$".toRegex())) {
        x = str.toInt()
    } else {
        throw Exception("Wrong input! Only positive integers are allowed.")
    }
    return x
}

fun primeFinder(num: Int): Boolean {
    var res = true
    if (num <= 1) return false
    var i = 2
    while (i * i <= num) {
        if (num % i == 0) {
            res = false
            break
        }
        ++i
    }
    return res
}

fun Int.length() = when (this) {
    0 -> 1
    else -> log10(toDouble()).toInt() + 1
}

fun main(args: Array<String>) {
    if (args.size != 1 || (args[0] != "lower" && args[0] != "higher")) {
        throw Exception("Wrong program arguments!")
    }
    println("The grouping order is: ${args[0]}")
    println("Enter a number:")
    var num = inputNumber()
    val digitCount = num.length()
    val arr = IntArray(digitCount)
    if (args[0] == "higher") num = num.toString().reversed().toInt()

    var i = 0
    while (i < digitCount) {
        arr[i] = num % 10
        num /= 10
        ++i
    }

    var tmp = 0
    for (data in arr) {
        tmp += data
        if (primeFinder(tmp)) {
            println("$tmp - prime")
        } else {
            println(tmp)
        }
        tmp *= 10
    }
}