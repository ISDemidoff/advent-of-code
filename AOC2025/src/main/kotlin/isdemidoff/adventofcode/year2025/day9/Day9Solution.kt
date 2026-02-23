package isdemidoff.adventofcode.year2025.day9

import isdemidoff.adventofcode.year2025.day9.entity.Point
import isdemidoff.adventofcode.year2025.day9.entity.toPoint
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.parseUnescapedCsvInputLine
import isdemidoff.utility.solution.inputparser.functions.mapLines
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution

fun findMaxRectangleSize(points: List<Point>) = cartesianProduct(points, points) { (first, second) -> first rectangleSizeWith second }.max()

/**
 * [Day 9: Movie Theater](https://adventofcode.com/2025/day/9).
 */
val day9 = solution(9) {
    inputParser = StringsInputParsers.singleBlock.mapLines { it.parseUnescapedCsvInputLine { it.toString().toLong() }.toPoint() }

    part1Solver = solver({ "Max rectangle size is $it." }) { findMaxRectangleSize(it) }
}