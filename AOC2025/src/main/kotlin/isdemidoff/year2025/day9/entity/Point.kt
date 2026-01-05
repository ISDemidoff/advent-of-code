package isdemidoff.year2025.day9.entity

import kotlin.math.absoluteValue

data class Point(val x: Long, val y: Long) {
    infix fun rectangleSizeWith(other: Point) = sideLength(this.x, other.x) * sideLength(this.y, other.y)
}

private fun sideLength(
    firstCoordinate: Long,
    secondCoordinate: Long,
) = (firstCoordinate - secondCoordinate).absoluteValue + 1

fun List<Long>.toPoint(): Point {
    require(this.size == 2) { "Expected exactly 2 coordinates but got ${this.size}" }
    return Point(this[0], this[1])
}