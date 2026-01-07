package isdemidoff.year2025.day2

import isdemidoff.RealSimpleSolutionBuilder
import isdemidoff.utility.input.readSingleLine
import isdemidoff.utility.parseUnescapedCsvInputLine
import isdemidoff.utility.toLongRange

private fun Long.isSillyNumber() = toString()
    .takeIf { it.length % 2 == 0 }
    ?.takeIf { it.take(it.length / 2) == it.takeLast(it.length / 2) } != null

private fun LongRange.getSumOfSillyPatterns() = filter { it.isSillyNumber() }.sum()

class Day2SolutionBuilder(day2Path: String) : RealSimpleSolutionBuilder<Long, List<LongRange>>(
    inputsDir = day2Path,
    inputParser = { filename ->
        readSingleLine(filename)
            .parseUnescapedCsvInputLine { it.toLongRange() }
    },
    solver = { it.sumOf { it.getSumOfSillyPatterns() } },
)