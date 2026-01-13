package isdemidoff.year2025.day3

import isdemidoff.SimpleDeprecatedSolutionBuilder
import isdemidoff.utility.input.readLines

private fun findMaxOutputJoltage(battery: String) = battery.mapIndexed { leftIndex, leftChar ->
    if (leftIndex == battery.lastIndex) return@mapIndexed 0
    battery.drop(leftIndex + 1).maxOf { rightChar -> "$leftChar$rightChar".toInt() }
}.max()

class Day3SolutionBuilder(day3Path: String) : SimpleDeprecatedSolutionBuilder<Int, List<String>>(
    inputsDir = day3Path,
    inputParser = { readLines(it) },
    solver = { it.sumOf { findMaxOutputJoltage(it) } },
)