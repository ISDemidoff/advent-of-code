package isdemidoff.adventofcode.year2025.day9

import isdemidoff.adventofcode.year2025.day9.entity.Point
import isdemidoff.adventofcode.year2025.day9.entity.toPoint
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.singleBlock
import isdemidoff.solution.solution
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.parseUnescapedCsvInputLine

fun findMaxRectangleSize(points: List<Point>) = cartesianProduct(points, points) { (first, second) -> first rectangleSizeWith second }.max()

/**
 * [Day 9: Movie Theater](https://adventofcode.com/2025/day/9).
 */
val day9 = solution(9) {
    inputParser = singleBlock andThenOnEveryLine {
        it.parseUnescapedCsvInputLine { it.toString().toLong() }.toPoint()
    }

    part1Solver = solver({ "Max rectangle size is $it." }) { findMaxRectangleSize(it) }
}
