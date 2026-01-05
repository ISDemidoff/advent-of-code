package isdemidoff.year2025.day6

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseWhitespaceDelimitedInputList
import isdemidoff.utility.takeFirstChars
import isdemidoff.utility.toLongsListList
import isdemidoff.year2025.day6.entity.Problem
import isdemidoff.year2025.day6.entity.makeProblemOf

class Day6SolutionBuilder(day6Path: String) : SimpleSolutionBuilder<Long, List<Problem>>(
    day6Path,
    { filename ->
        readLines(filename).parseWhitespaceDelimitedInputList()
            .let { it.last().takeFirstChars() to it.dropLast(1).toLongsListList() }
            .let { (operations, elements) -> operations.mapIndexed { index, op -> op makeProblemOf elements.map { it[index] } } }
    },
    { parsedInput -> parsedInput.sumOf { it.solve() } },
)