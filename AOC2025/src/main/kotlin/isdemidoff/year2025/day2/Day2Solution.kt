package isdemidoff.year2025.day2

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readSingleLine
import isdemidoff.utility.parseUnescapedCsvInputLine
import isdemidoff.utility.toLongRange

private fun Long.isSillyNumber() = toString()
    .takeIf { it.length % 2 == 0 }
    ?.takeIf { it.take(it.length / 2) == it.takeLast(it.length / 2) } != null

private fun LongRange.getSumOfSillyPatterns() = filter { it.isSillyNumber() }.sum()

class Day2SolutionBuilder(day2Path: String) : SimpleSolutionBuilder<Long, List<LongRange>>(
    day2Path,
    { filename ->
        readSingleLine(filename)
            .parseUnescapedCsvInputLine { it.toLongRange() }
    },
    { it.sumOf { it.getSumOfSillyPatterns() } },
)