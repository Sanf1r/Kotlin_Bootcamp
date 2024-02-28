package com.example.thermohydrometermodule

enum class Mode { CELSIUS, KELVIN, FAHRENHEIT }

enum class Season { SUMMER, WINTER }

object Thermohydrometer {

    fun thermoLogic(mode: Mode, season: Season, temp: Double?, hum: Int?): String {
        if (temp == null || hum == null || hum > 100) return "Wrong input!"
        val output = StringBuilder()
        val convertedTemp = convert(output, mode, temp)
        comfortTemp(output, mode, season, convertedTemp)
        output.appendLine()
        comfortHumidity(output, season, hum)
        return output.toString()
    }

    private fun convert(output: StringBuilder, mode: Mode, temp: Double): Double {
        var res = temp
        when (mode) {
            Mode.FAHRENHEIT -> {
                res = temp * 9 / 5 + 32
                output.appendLine("The temperature is $res ˚F")
            }

            Mode.KELVIN -> {
                res = temp + 273.15
                output.appendLine("The temperature is $res ˚K")
            }

            else -> {
                output.appendLine("The temperature is $temp ˚C")
            }
        }
        return res
    }

    private fun comfortTemp(output: StringBuilder, mode: Mode, season: Season, temp: Double) {
        var low = 20.0
        var mid = 22.0
        var top = 25.0
        var symb = "C"
        if (mode == Mode.FAHRENHEIT) {
            low = low * 9 / 5 + 32
            mid = mid * 9 / 5 + 32
            top = top * 9 / 5 + 32
            symb = "F"
        } else if (mode == Mode.KELVIN) {
            low += 273.15
            mid += 273.15
            top += 273.15
            symb = "K"
        }
        if (season == Season.SUMMER) {
            output.appendLine("The comfortable temperature is from $mid to $top ˚$symb.")
            if (temp < mid) {
                output.appendLine("Please, make it warmer by ${mid - temp} degrees.")
            } else if (temp > top) {
                output.appendLine("Please, make it colder by ${temp - top} degrees.")
            } else {
                output.appendLine("The temperature is comfortable.")
            }
        } else {
            output.appendLine("The comfortable temperature is from $low to $mid ˚$symb.")
            if (temp < low) {
                output.appendLine("Please, make it warmer by ${low - temp} degrees.")
            } else if (temp > mid) {
                output.appendLine("Please, make it colder by ${temp - mid} degrees.")
            } else {
                output.appendLine("The temperature is comfortable.")
            }
        }
    }

    private fun comfortHumidity(output: StringBuilder, season: Season, hum: Int) {
        val low = 30
        val mid = 45
        val top = 60
        if (season == Season.SUMMER) {
            output.appendLine("The comfortable humidity is from $low% to $top%.")
            if (hum < low) {
                output.appendLine("Please, increase humidity by ${low - hum} percents.")
            } else if (hum > top) {
                output.appendLine("Please, decrease humidity by ${hum - top} percents.")
            } else {
                output.appendLine("The humidity is comfortable.")
            }
        } else {
            output.appendLine("The comfortable humidity is from $low% to $mid%.")
            if (hum < low) {
                output.appendLine("Please, increase humidity by ${low - hum} percents.")
            } else if (hum > mid) {
                output.appendLine("Please, decrease humidity by ${hum - mid} percents.")
            } else {
                output.appendLine("The humidity is comfortable.")
            }
        }
    }
}