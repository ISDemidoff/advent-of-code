import Point.Companion.toPoint
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseUnescapedCsvInputLines
import kotlin.math.absoluteValue

data class Point(val x: Long, val y: Long) {
    infix fun rectangleSizeWith(other: Point) = sideLength(this.x, other.x) * sideLength(this.y, other.y)

    companion object {
        fun List<Long>.toPoint(): Point {
            require(this.size == 2) { "Expected exactly 2 coordinates but got ${this.size}" }
            return Point(this[0], this[1])
        }
    }
}

fun sideLength(first: Long, second: Long) = (first - second).absoluteValue + 1

fun solveForFileName(fileName: String) = readLines(fileName)
    .parseUnescapedCsvInputLines { it.toString().toLong() }
    .map { it.toPoint() }
    .let { findMapRectangleSize(it) }

fun findMapRectangleSize(points: List<Point>) = cartesianProduct(points, points) { (first, second) -> first rectangleSizeWith second }.max()


