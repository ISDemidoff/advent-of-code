package isdemidoff.adventofcode.year2015.day25

import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.scope.singleLine
import isdemidoff.solution.solution
import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields
import isdemidoff.utility.parsing.toIntOrError

data class Position(val row: Int, val column: Int)

internal fun findCodeAtPosition(position: Position): Long {
    val requiredIterations = ( (position.row + position.column).let { it * it } - 3 * position.row - position.column ) / 2
    var result = 20151125L
    repeat(requiredIterations) { result = (result * 252533L) % 33554393L }
    return result
}

internal val targetPositionParser: (String) -> Position = regexMatch(
    """To continue, please consult the code grid in the manual\.\s+Enter the code at row ([0-9]+), column ([0-9]+)\.""".toRegex() yields { (row, column) ->
        Position(row.toIntOrError(), column.toIntOrError())
    }
)

/**
 * [Day 25: Let It Snow](https://adventofcode.com/2015/day/25).
 */
val day25 = solution(25) {
    inputParser = singleLine andThen targetPositionParser

    part1Solver = solver({
        "Code to the machine is $it."
    }) { findCodeAtPosition(it) }

    part2Solver = specialSolver()
}
