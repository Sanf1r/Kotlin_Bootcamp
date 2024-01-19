fun inputNumberTemp(): Double {
    val x: Double
    while (true) {
        val str = readln()
        if (str.matches("^-?([1-9](\\d+)?([.]\\d+)?|0([.]\\d+)?)\$".toRegex())) {
            x = str.toDouble()
            break
        } else {
            println("Incorrect input! Enter a temperature:")
            continue
        }
    }
    return x
}

fun inputNumberHumidity(): Int {
    val x: Int
    while (true) {
        val str = readln()
        if (str.matches("^([1-9](\\d)?|0|100)\$".toRegex())) {
            x = str.toInt()
            break
        } else {
            println("Incorrect input! Enter humidity:")
            continue
        }
    }
    return x
}

fun inputChar(): String {
    val x: String
    while (true) {
        val str = readln()
        if (str.matches("^[SW]\$".toRegex())) {
            x = str
            break
        } else {
            println("Wrong input! Only 'S' or 'W' are allowed.")
            continue
        }
    }
    return x
}

fun convert(mode: String, temp: Double): Double {
    var res = temp
    when (mode) {
        "Fahrenheit" -> {
            res = temp * 9 / 5 + 32
            println("The temperature is $res ˚F")
        }
        "Kelvin" -> {
            res = temp + 273.15
            println("The temperature is $res ˚K")
        }
        else -> {
            println("The temperature is $temp ˚C")
        }
    }
    return res
}

fun comfortTemp(mode: String, season: String, temp: Double) {
    var low = 20.0
    var mid = 22.0
    var top = 25.0
    var symb = "C"
    if (mode == "Fahrenheit") {
        low = low * 9 / 5 + 32
        mid = mid * 9 / 5 + 32
        top = top * 9 / 5 + 32
        symb = "F"
    } else if (mode == "Kelvin") {
        low += 273.15
        mid += 273.15
        top += 273.15
        symb = "K"
    }
    if (season == "S") {
        println("The comfortable temperature is from $mid to $top ˚$symb.")
        if (temp < mid) {
            println("Please, make it warmer by ${mid - temp} degrees.")
        } else if (temp > top) {
            println("Please, make it colder by ${temp - top} degrees.")
        }
    } else {
        println("The comfortable temperature is from $low to $mid ˚$symb.")
        if (temp < low) {
            println("Please, make it warmer by ${low - temp} degrees.")
        } else if (temp > mid) {
            println("Please, make it colder by ${temp - mid} degrees.")
        }
    }
}

fun comfortHumidity(season: String, hum: Int) {
    var low = 30
    var mid = 45
    var top = 60
    if (season == "S") {
        println("The comfortable humidity is from $low% to $top%")
        if (hum < low) {
            println("Please, increase humidity by ${low - hum} percents.")
        } else if (hum > top) {
            println("Please, decrease humidity by ${hum - top} percents.")
        } else {
            println("The humidity is comfortable")
        }
    } else {
        println("The comfortable humidity is from $low% to $mid%")
        if (hum < low) {
            println("Please, increase humidity by ${low - hum} percents.")
        } else if (hum > mid) {
            println("Please, decrease humidity by ${hum - mid} percents.")
        } else {
            println("The humidity is comfortable")
        }
    }

}

fun main(args: Array<String>) {
    val mode: String = if (args.isEmpty()) {
        "Celsius"
    } else if (args.size == 1 && (args[0] == "Celsius" || args[0] == "Kelvin" || args[0] == "Fahrenheit")) {
        args[0]
    } else {
        throw Exception("Wrong program arguments!")
    }

    println("Output mode: $mode")
    println("Enter a season - (W)inter or (S)ummer:")
    val season = inputChar()
    if (season == "S") {
        print("Season: Summer")
    } else {
        print("Season: Winter")
    }
    println(". Enter a temperature:")
    var temp = inputNumberTemp()
    println("Enter humidity:")
    var hum = inputNumberHumidity()
    println()

    temp = convert(mode, temp)
    comfortTemp(mode, season, temp)
    println()
    comfortHumidity(season, hum)
}