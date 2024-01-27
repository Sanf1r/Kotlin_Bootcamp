package com.example.kotlincourse

fun inputNumber(): Int {
    val x: Int
    val str = readln()
    if (str.matches("^-?([1-9](\\d+)?|0)\$".toRegex())) {
        x = str.toInt()
    } else {
        throw Exception("Wrong input! Only integers are allowed.")
    }
    return x
}

fun main() {
    println("Type a response code:")
    val code = inputNumber()
    val response = when (code) {
        in 200..201 -> Responses.Success(code)
        1000 -> Responses.Error.UserNotIdentifiedError(code)
        1001 -> Responses.Error.SessionExpiredError(code)
        1002 -> Responses.Error.NoConnectionError(code)
        1003 -> Responses.Error.DeviceVerificationError(code)
        else -> Responses.Error.UnknownError(code)
    }
    println(response)
}