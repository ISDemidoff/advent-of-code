package isdemidoff.year2025.day2

import isdemidoff.utility.parseUnescapedCsvInputLine
import isdemidoff.utility.solution.solution
import isdemidoff.utility.toLongRange

private fun LongRange.getSumOfSillyPatterns(onlyTwo: Boolean) =
    filter { it.isRepeatedPattern(onlyTwo) }.sum()

private fun Long.isRepeatedPattern(onlyTwo: Boolean) = toString().let { str ->
    if (str.length < 2) return@let false
    val checkAgainst = ownDividers(str.length).toMutableList().apply { removeIf { onlyTwo && it != 2 } }

    checkAgainst.forEach {
        val chunked = str.chunked(str.length / it)
        if (chunked.toSet().size == 1) {
            return@let true
        }
    }

    return@let false
}

private fun ownDividers(int: Int): List<Int> {
    val result = mutableListOf(int)
    (2 .. int / 2).forEach {
        if (int % it == 0) result.add(it)
    }
    return result
}

/**
 * [Day 2: Gift Shop](https://adventofcode.com/2025/day/2).
 */
val day2 = solution(2) {
    inputParser = singleLineParser { it.parseUnescapedCsvInputLine { it.toLongRange() } }

    part1Solver = solver({ "Adding all invalid IDs results into $it." }) {
        it.sumOf { it.getSumOfSillyPatterns(true) }
    }

    part2Solver = solver({ "Adding all invalid IDs using extended method results into $it." }) {
        it.sumOf { it.getSumOfSillyPatterns(false) }
    }
}