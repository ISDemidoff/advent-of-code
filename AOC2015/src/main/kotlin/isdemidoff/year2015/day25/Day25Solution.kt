package isdemidoff.year2015.day25

import isdemidoff.utility.solution.solution

internal fun findCodeAtPosition(row: Int, column: Int): Long {
    val requiredIterations = ( (row + column).let { it * it } - 3 * row - column ) / 2
    var result = 20151125L
    repeat(requiredIterations) { result = (result * 252533L) % 33554393L }
    return result
}

/**
 * [Day 25: Let It Snow](https://adventofcode.com/2015/day/25).
 */
val day25 = solution<Pair<Int, Int>, Long>(25) {
    inputParser = singleLineParser { inputLine ->
        """Enter the code at row ([0-9]+), column ([0-9]+)""".toRegex().find(inputLine)
            .let { requireNotNull(it?.destructured) { "Incorrect input: $inputLine" } }
            .let { it.component1().toInt() to it.component2().toInt() }
    }

    part1Solver = solver({
        "Code to the machine is $it."
    }) { (row, column) -> findCodeAtPosition(row, column) }
}