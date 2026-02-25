package isdemidoff.adventofcode.year2015.day25

import isdemidoff.solution.inputparser.functions.map
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

data class Position(val row: Int, val column: Int)

internal fun findCodeAtPosition(position: Position): Long {
    val requiredIterations = ( (position.row + position.column).let { it * it } - 3 * position.row - position.column ) / 2
    var result = 20151125L
    repeat(requiredIterations) { result = (result * 252533L) % 33554393L }
    return result
}

/**
 * [Day 25: Let It Snow](https://adventofcode.com/2015/day/25).
 */
val day25 = solution(25) {
    inputParser = StringsInputParsers.singleLine.map { inputLine ->
        """Enter the code at row ([0-9]+), column ([0-9]+)""".toRegex().find(inputLine)
            .let { requireNotNull(it?.destructured) { "Incorrect input: $inputLine" } }
            .let { Position(it.component1().toInt(), it.component2().toInt()) }
    }

    part1Solver = solver({
        "Code to the machine is $it."
    }) { findCodeAtPosition(it) }

    part2Solver = specialSolver()
}