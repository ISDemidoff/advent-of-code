package isdemidoff.year2015.day10

import isdemidoff.SingleLineDeprecatedSolutionBuilder

internal fun String.nextApply(): String {
    require(this.matches("""[0-9]+""".toRegex())) { "Invalid input: $this" }
    val output = StringBuilder()

    var current = this[0]
    var count = 1

    fun appendOutput() {
        output.append(count).append(current)
    }

    this.substring(1).forEach {
        if (current == it) {
            ++count
        } else {
            appendOutput()
            current = it
            count = 1
        }
    }
    appendOutput()

    return output.toString()
}

/**
 * [Day 10: Elves Look, Elves Say](https://adventofcode.com/2015/day/10).
 */
class Day10SolutionBuilder(
    private val day10Path: String,
    private val repetitions: Int = 40,
) : SingleLineDeprecatedSolutionBuilder<Int>(
    inputsDir = day10Path,
    solver = {
        var result = it
        repeat(repetitions) { result = result.nextApply() }
        result.length
    },
) {
    fun withRepetitions(rep: Int) = Day10SolutionBuilder(day10Path, rep)
}