package isdemidoff.adventofcode.year2016.day3

import isdemidoff.utility.parseWhitespaceDelimitedInput
import isdemidoff.utility.solution.solution
import isdemidoff.utility.transpose

/**
 * [Day 3: Squares With Three Sides](https://adventofcode.com/2016/day/3).
 */
val day3 = solution(3) {
    inputParser = uniformLinesParser { it.parseWhitespaceDelimitedInput { it.toString().toInt() } }

    fun countTriangles(maybeTriangles: List<List<Int>>) = maybeTriangles.count { maybeTriangle ->
        require(maybeTriangle.size == 3) { "Invalid count of sides for a triangle: $maybeTriangle" }
        val sorted: List<Int> = maybeTriangle.sorted()
        sorted.let { (a, b, c) -> a + b > c }
    }

    part1Solver = solver({
        "There are $it possible triangles."
    }) { countTriangles(it) }

    part2Solver = solver({
        "There are $it possible triangles if read them vertically."
    }) { countTriangles(it.transpose().flatten().chunked(3)) }
}