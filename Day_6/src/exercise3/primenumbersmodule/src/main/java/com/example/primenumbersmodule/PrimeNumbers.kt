package com.example.primenumbersmodule

import kotlin.math.log10

fun primeFinder(num: Long): Boolean {
    var res = true
    if (num <= 1) return false
    var i = 2
    while (i * i <= num) {
        if (num % i == 0L) {
            res = false
            break
        }
        ++i
    }
    return res
}

fun Long.length() = when (this) {
    0L -> 1
    else -> log10(toDouble()).toInt() + 1
}

fun primeNumbersLogic(input: Long?, reversed: Boolean): String {
    if (input == 0L || input == null) return "Wrong input! Only positive integers are allowed."
    val res = StringBuilder()
    var num = input
    val digitCount = num.length()
    val arr = LongArray(digitCount)
    if (reversed) num = num.toString().reversed().toLong()

    var i = 0
    while (i < digitCount) {
        arr[i] = num % 10
        num /= 10
        ++i
    }

    var tmp = 0L
    for (data in arr) {
        tmp += data
        if (primeFinder(tmp)) {
            res.appendLine("$tmp - prime")
        } else {
            res.appendLine(tmp)
        }
        tmp *= 10
    }
    return res.toString()
}
