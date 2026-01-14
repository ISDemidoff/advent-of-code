package isdemidoff.year2015.day10

import isdemidoff.utility.solution.solution

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
val day10 = solution(10) {
    inputParser = singleLineParser { it }

    fun solutionProvider(repetitions: Int) = solver({
        "After $repetitions steps, input become a string of $it size."
    }) {
        var result = it
        repeat(repetitions) { result = result.nextApply() }
        result.length
    }

    part1Solver = solutionProvider(40)
    part2Solver = solutionProvider(50)
}