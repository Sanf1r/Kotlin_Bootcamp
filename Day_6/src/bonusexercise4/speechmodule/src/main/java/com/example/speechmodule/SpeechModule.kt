package com.example.speechmodule

import kotlin.math.abs

object SpeechModule {

    fun speechModule(numIn: Int?): String {
        if (numIn == null || numIn !in -1000000000..1000000000) return "Wrong input!"
        var limit = 1000000000
        var count = 0
        val result = StringBuilder()
        val bigOnes = arrayOf(
            "", "Billion",
            "Million", "Thousand"
        )
        val firstTwenty = arrayOf(
            "", "One", "Two", "Three",
            "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven",
            "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        )
        val tens = arrayOf(
            "", "", "Twenty", "Thirty",
            "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety"
        )

        if (numIn == 0) {
            return "Zero"
        }
        var numToWork = numIn
        if (numToWork < 0) {
            result.append("Minus-")
            numToWork = abs(numToWork)
        }
// first while to cut numbers by limit
        while (numToWork > 0) {
            var tmp = numToWork / limit
// second while to find max left
            while (tmp == 0) {
                numToWork %= limit
                limit /= 1000
                tmp = numToWork / limit
                ++count
            }
// now add a hundred
            if (tmp > 99) {
                result.append("${firstTwenty[tmp / 100]}-Hundred-")
            }
// reduce to tens
            tmp %= 100
// add to string
            if (tmp in 1..19) {
                result.append("${firstTwenty[tmp]}-")
            } else if (tmp % 10 == 0 && tmp != 0) {
                result.append("${tens[tmp / 10]}-")
            } else if (tmp in 21..99) {
                result.append("${tens[tmp / 10]}-${firstTwenty[tmp % 10]}-")
            }

            if (count < 3) {
                result.append("${bigOnes[++count]}-")
            }

// get new number and repeat
            numToWork %= limit
            limit /= 1000
        }
        return result.toString().trim('-')
    }
}