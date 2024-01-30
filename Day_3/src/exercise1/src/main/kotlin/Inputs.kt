package org.example

fun input(): Int {
    val x: Int
    while (true) {
        val str = readln()
        if (str.matches("^[1-4]\$".toRegex())) {
            x = str.toInt()
            println()
            break
        } else {
            println("It doesn't look like a correct input.")
            continue
        }
    }
    return x
}

fun inputProf(): Int {
    val x: Int
    while (true) {
        val str = readln()
        if (str.matches("^[1-6]\$".toRegex())) {
            x = str.toInt()
            println()
            break
        } else {
            println("It doesn't look like a correct input.")
            continue
        }
    }
    return x
}