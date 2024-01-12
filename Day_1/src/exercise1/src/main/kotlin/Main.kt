import kotlin.math.hypot

data class Circle(val x: Double, val y: Double, val radius: Double)

// Если расстояние между центрами
// меньше сумме радиусов окружностей, то они пересекаются.
fun circlesIntersect(circle1: Circle, circle2: Circle): Boolean {
    return hypotCalc(circle1, circle2) < circle1.radius + circle2.radius
}

// равно сумме радиусов окружностей, то они касаются.
fun circlesTouch(circle1: Circle, circle2: Circle): Boolean {
    return hypotCalc(circle1, circle2) == circle1.radius + circle2.radius
}

// меньше или равно разности радиусов (радиус внешней окружности минус радиус внутренней),
// то первая окружность находится внутри второй.
fun circlesInside(circle1: Circle, circle2: Circle): Boolean {
    if (circle1.radius >= circle2.radius) return false
    return hypotCalc(circle1, circle2) <= circle2.radius - circle1.radius
}

fun hypotCalc(circle1: Circle, circle2: Circle): Double {
    val dx = circle2.x - circle1.x
    val dy = circle2.y - circle1.y
    return hypot(dx, dy)
}

fun inputCoord(): Double {
    val x: Double
    while (true) {
            val str = readln()
            if (str.matches("^-?([1-9](\\d+)?([.]\\d+)?|0([.]\\d+)?)\$".toRegex())) {
                x = str.toDouble()
                break
            } else {
                println("Couldn't parse a number. Please, try again")
                continue
            }
    }
    return x
}

fun inputRadius(): Double {
    val x: Double
    var str: String
    while (true) {
        str = readln()
        if (str.matches("^([1-9](\\d+)?([.]\\d+)?|0[.]\\d+)\$".toRegex())) {
            x = str.toDouble()
            break
        } else {
            println("Couldn't parse a number. Please, try again")
            continue
        }
    }
    return x
}

fun main() {
    println("Input x1:")
    val x1 = inputCoord()
    println("Input y1:")
    val y1 = inputCoord()
    println("Input r1:")
    val r1 = inputRadius()
    println("Input x2:")
    val x2 = inputCoord()
    println("Input y2:")
    val y2 = inputCoord()
    println("Input r2:")
    val r2 = inputRadius()

    val one = Circle(x1, y1, r1)
    val two = Circle(x2, y2, r2)

    if (one == two) {
        println("Result: the circles coincide")
    } else if (circlesInside(one, two) || circlesInside(two, one)) {
        println("Result: the circles inside one another")
    } else if (circlesTouch(one, two)) {
        println("Result: the circles touch")
    } else if (circlesIntersect(one, two)) {
        println("Result: the circles intersect")
    } else {
        println("Result: the circles do not intersect")
    }
}