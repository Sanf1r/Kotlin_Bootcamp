package com.example.circlesmodule

import kotlin.math.hypot
import kotlin.math.sqrt

data class Circle(val x: Double, val y: Double, val radius: Double)

data class Point(val x: Double, val y: Double)

fun circlesIntersect(circle1: Circle, circle2: Circle): Boolean {
    return hypotCalc(circle1, circle2) < circle1.radius + circle2.radius
}

fun intersectPoints(circle1: Circle, circle2: Circle): Pair<Point, Point> {
    val d = hypotCalc(circle1, circle2)
    val a = (circle1.radius * circle1.radius - circle2.radius * circle2.radius + d * d) / (2 * d)
    val h = sqrt(circle1.radius * circle1.radius - a * a)
    val x0 = circle1.x + a * (circle2.x - circle1.x) / d
    val y0 = circle1.y + a * (circle2.y - circle1.y) / d
    val x1 = x0 + h * (circle2.y - circle1.y) / d
    val y1 = y0 - h * (circle2.x - circle1.x) / d
    val x2 = x0 - h * (circle2.y - circle1.y) / d
    val y2 = y0 + h * (circle2.x - circle1.x) / d
    return Pair(Point(x1, y1), Point(x2, y2))
}

fun circlesTouch(circle1: Circle, circle2: Circle): Boolean {
    return hypotCalc(circle1, circle2) == circle1.radius + circle2.radius
}

fun circlesInside(circle1: Circle, circle2: Circle): Boolean {
    if (circle1.radius >= circle2.radius) return false
    return hypotCalc(circle1, circle2) <= circle2.radius - circle1.radius
}

fun hypotCalc(circle1: Circle, circle2: Circle): Double {
    val dx = circle2.x - circle1.x
    val dy = circle2.y - circle1.y
    return hypot(dx, dy)
}