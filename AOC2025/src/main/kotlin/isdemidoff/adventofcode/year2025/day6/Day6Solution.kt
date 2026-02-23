package isdemidoff.adventofcode.year2025.day6

import isdemidoff.adventofcode.year2025.day6.entity.makeProblemOf
import isdemidoff.utility.parsing.toLongsList
import isdemidoff.utility.solution.inputparser.functions.andThen
import isdemidoff.utility.solution.inputparser.functions.mapLines
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution

/**
 * [Day 6: Trash Compactor](https://adventofcode.com/2025/day/6).
 */
val day6 = solution(6) {
//    inputParser = StringsInputParsers.singleBlockParser { lines ->
//        lines.parseWhitespaceDelimitedInputList()
//            .let { it.last().takeFirstChars() to it.dropLast(1).toLongsListList() }
//            .let { (operations, elements) -> operations.mapIndexed { index, op -> op makeProblemOf elements.map { it[index] } } }
//    }

    inputParser = StringsInputParsers.spaceDelimitedTable
        .andThen(StringsInputParsers.transpose)
        .mapLines { line -> line.last().first() makeProblemOf toLongsList(line.dropLast(1)) }

    part1Solver = solver({ "Grand total of answers is $it." }) { it.sumOf { it.solve() } }
}