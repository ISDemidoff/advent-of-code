package isdemidoff.year2015.day10

import isdemidoff.SingleLineSolutionBuilder

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

class Day10SolutionBuilder(day10Path: String) : SingleLineSolutionBuilder<Int>(
    inputsDir = day10Path,
    solver = {
        var result = it
        repeat(40) { result = result.nextApply() }
        result.length
    },
)