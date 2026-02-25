package isdemidoff.adventofcode.year2025.day6

import isdemidoff.adventofcode.year2025.day6.entity.makeProblemOf
import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.functions.mapLines
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.utility.parsing.toLongsList

/**
 * [Day 6: Trash Compactor](https://adventofcode.com/2025/day/6).
 */
val day6 = solution(6) {
    inputParser = StringsInputParsers.spaceDelimitedTable
        .andThen(StringsInputParsers.transpose)
        .mapLines { line -> line.last().first() makeProblemOf toLongsList(line.dropLast(1)) }

    part1Solver = solver({ "Grand total of answers is $it." }) { it.sumOf { it.solve() } }
}