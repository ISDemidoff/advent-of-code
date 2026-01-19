package isdemidoff.adventofcode.year2025.day6

import isdemidoff.adventofcode.year2025.day6.entity.makeProblemOf
import isdemidoff.utility.parseWhitespaceDelimitedInputList
import isdemidoff.utility.solution.solution
import isdemidoff.utility.takeFirstChars
import isdemidoff.utility.toLongsListList

/**
 * [Day 6: Trash Compactor](https://adventofcode.com/2025/day/6).
 */
val day6 = solution(6) {
    inputParser = singleBlockParser { lines ->
        lines.parseWhitespaceDelimitedInputList()
            .let { it.last().takeFirstChars() to it.dropLast(1).toLongsListList() }
            .let { (operations, elements) -> operations.mapIndexed { index, op -> op makeProblemOf elements.map { it[index] } } }
    }

    part1Solver = solver({ "Grand total of answers is $it." }) { it.sumOf { it.solve() } }
}