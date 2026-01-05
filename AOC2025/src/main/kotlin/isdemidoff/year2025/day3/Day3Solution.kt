package isdemidoff.year2025.day3

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines

private fun findMaxOutputJoltage(battery: String) = battery.mapIndexed { leftIndex, leftChar ->
    if (leftIndex == battery.lastIndex) return@mapIndexed 0
    battery.drop(leftIndex + 1).maxOf { rightChar -> "$leftChar$rightChar".toInt() }
}.max()

class Day3SolutionBuilder(day3Path: String) : SimpleSolutionBuilder<Int, List<String>>(
    day3Path,
    { readLines(it) },
    { it.sumOf { findMaxOutputJoltage(it) } },
)