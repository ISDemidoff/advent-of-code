package isdemidoff.adventofcode.year2016.day3

import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.spaceDelimitedTable
import isdemidoff.solution.solution
import isdemidoff.utility.other.transpose
import isdemidoff.utility.parsing.toIntsList

/**
 * [Day 3: Squares With Three Sides](https://adventofcode.com/2016/day/3).
 */
val day3 = solution(3) {
    inputParser = spaceDelimitedTable andThenOnEveryLine ::toIntsList

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
