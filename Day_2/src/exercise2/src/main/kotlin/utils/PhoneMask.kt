package com.example.kotlincourse.utils

fun String.phoneMask(): String {
    val result = StringBuilder()
    if (this.matches(
            ("^(\\+7\\d{10}|[7-8]\\d{10})\$").toRegex()
        )
    ) {
        result.append(this.trim('+'))
    } else {
        return this
    }

    if (result.substring(1 .. 3) == "800") {
        result.insert(1, " (")
        result.insert(6, ") ")
        result.insert(11, ' ')
        result.insert(14, ' ')
        if (this.startsWith('+')) result.append('+')
    } else {
        result.replace(0,1,"+7 ")
        result.insert(6, ' ')
        result.insert(10, '-')
        result.insert(13, '-')
    }
    return result.toString()
}