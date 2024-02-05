package org.example

import kotlin.reflect.KProperty

data class First(val name: String)
data class Second(val name: String)

abstract class Converter<A, B> {
    abstract fun convert(input: A): B
}

class FirstToSecondConverter : Converter<First, Second>() {
    override fun convert(input: First): Second {
        return Second(input.name)
    }

    fun convertToList(input: List<First>): List<Second> {
        val res = mutableListOf<Second>()
        for (elem in input) res.add(Second(elem.name))
        return res
    }
}


fun areTypesEqual(obj1: Any, obj2: Any): Boolean {
    return obj1::class == obj2::class
}

inline fun <reified T> Any?.isInstanceOf(): Boolean {
    return this is T
}


open class Animal

class Dog : Animal()

class Cat : Animal()

class Four {
    var description: String by Delegate()
    var logs: String by LogDelegate()
}

class Delegate {
    private var value: String = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value.replace(' ', '_')
    }
}

class LogDelegate {
    private var value: String = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("A property \"${value}\" was readed")
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
        println("A property was assigned \"$value\"")
    }
}

fun Number.add(other: Number): Number {
    return this.toDouble() + other.toDouble()
}

fun Number.subtract(other: Number): Number {
    return this.toDouble() - other.toDouble()
}

fun Number.multiply(other: Number): Number {
    return this.toDouble() * other.toDouble()
}

fun Number.divide(other: Number): Number {
    if (other.toDouble() == 0.0) throw ArithmeticException("Division by zero")
    return this.toDouble() / other.toDouble()
}

enum class Operation(val func: (Number, Number) -> Number) {
    ADDITION({ a, b -> a.add(b) }),
    SUBTRACTION({ a, b -> a.subtract(b) }),
    MULTIPLY({ a, b -> a.multiply(b) }),
    DIVIDE({ a, b -> a.divide(b) })
}

fun getOperation(key: String): ((Number, Number) -> Number)? {
    return Operation.entries.find { it.name == key }?.func
}