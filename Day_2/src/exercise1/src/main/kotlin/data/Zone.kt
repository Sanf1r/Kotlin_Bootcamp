package com.example.kotlincourse.data

import kotlin.math.abs
import kotlin.math.pow

data class Point(val x: Double, val y: Double)
data class Circle(val x: Double, val y: Double, val r: Double)
data class Triangle(
    val x1: Double, val y1: Double,
    val x2: Double, val y2: Double,
    val x3: Double, val y3: Double
)

data class Tetragon(
    val x1: Double, val y1: Double,
    val x2: Double, val y2: Double,
    val x3: Double, val y3: Double,
    val x4: Double, val y4: Double
)

open class BaseZone {
    open val shape = "Base"
    open val phone = "88008473824"
    open fun determ(inc: Incident): Boolean {
        return true
    }
}

class CircleZone(circle: Circle) : BaseZone() {
    private val zone = circle
    override val phone = "89347362826"
    override val shape = "circle"

    override fun determ(inc: Incident): Boolean {
        return (inc.x - zone.x).pow(2.0) + (inc.y - zone.y).pow(2.0) <= zone.r.pow(2.0)
    }

    override fun toString(): String {
        return zone.toString()
    }
}

class TriangleZone(triangle: Triangle) : BaseZone() {
    private val zone = triangle
    override val phone = "89347362840"
    override val shape = "triangle"

    private fun area(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double): Double {
        return abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0)
    }

    override fun determ(inc: Incident): Boolean {
        val bigTriang = area(zone.x1, zone.y1, zone.x2, zone.y2, zone.x3, zone.y3)
        val miniTriangOne = area(inc.x, inc.y, zone.x2, zone.y2, zone.x3, zone.y3)
        val miniTriangTwo = area(zone.x1, zone.y1, inc.x, inc.y, zone.x3, zone.y3)
        val miniTriangThree = area(zone.x1, zone.y1, zone.x2, zone.y2, inc.x, inc.y)
        return (bigTriang == miniTriangOne + miniTriangTwo + miniTriangThree)
    }

    override fun toString(): String {
        return zone.toString()
    }
}

class TetragonZone(tetragon: Tetragon) : BaseZone() {
    private val zone = tetragon
    override val phone = "89347362830"
    override val shape = "tetragon"
    override fun determ(inc: Incident): Boolean {
        val tetraList = listOf(
            Point(zone.x1, zone.y1), Point(zone.x2, zone.y2),
            Point(zone.x3, zone.y3), Point(zone.x4, zone.y4)
        )
        var intersections = 0
        val numVertices = tetraList.size
        var p1 = tetraList[0]

        for (i in 1 until numVertices + 1) {
            val p2 = tetraList[i % numVertices]

            // Check if the point is above the minimum y coordinate of the edge
            if (inc.y > minOf(p1.y, p2.y)) {
                // Check if the point is below the maximum y coordinate of the edge
                if (inc.y <= maxOf(p1.y, p2.y)) {
                    // Check if the point is to the left of the maximum x coordinate of the edge
                    if (inc.x <= maxOf(p1.x, p2.x)) {
                        // Calculate the x-intersection of the line connecting the point to the edge
                        val xIntersection = (inc.y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x
                        // Check if the point is on the same line as the edge or to the left of the x-intersection
                        if (p1.x == p2.x || inc.x <= xIntersection) {
                            intersections++
                        }
                    }
                }
            }
            p1 = p2
        }
        return intersections % 2 != 0
    }

    override fun toString(): String {
        return zone.toString()
    }
}

fun findType(str: String): Int {
    val result = if (str.contains("fire".toRegex())) {
        0
    } else if (str.contains("gas".toRegex())) {
        1
    } else if (str.contains("cat".toRegex())) {
        2
    } else {
        4
    }
    return result
}