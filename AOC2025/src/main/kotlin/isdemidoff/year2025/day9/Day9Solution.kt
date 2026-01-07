package isdemidoff.year2025.day9

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseUnescapedCsvInputLines
import isdemidoff.year2025.day9.entity.Point
import isdemidoff.year2025.day9.entity.toPoint

fun findMaxRectangleSize(points: List<Point>) = cartesianProduct(points, points) { (first, second) -> first rectangleSizeWith second }.max()

class Day9SolutionBuilder(day9Path: String) : SimpleSolutionBuilder<Long, List<Point>>(
    inputsDir = day9Path,
    inputParser = { filename ->
        readLines(filename)
            .parseUnescapedCsvInputLines { it.toString().toLong() }
            .map { it.toPoint() }
    },
    solver = { findMaxRectangleSize(it) },
)