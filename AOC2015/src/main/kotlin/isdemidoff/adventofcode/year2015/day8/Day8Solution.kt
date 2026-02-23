package isdemidoff.adventofcode.year2015.day8

import isdemidoff.utility.solution.inputparser.InputParsers
import isdemidoff.utility.solution.solution

private fun String.inMemorySize(): Int {
    check(this.startsWith("\"") && this.endsWith("\"")) { "String must be a string literal: $this" }
    var internalString = this.substring(1 ..< this.length - 1)

    val quotesCount = """\\"""".toRegex().findAll(internalString).count()
    internalString = internalString.replace("""\\"""".toRegex(), "")

    val backslashesCount = """\\\\""".toRegex().findAll(internalString).count()
    internalString = internalString.replace("""\\\\""".toRegex(), "")

    val asciiCodesCount = """\\x[0-9a-f]{2}""".toRegex().findAll(internalString).count()

    return internalString.length - 3 * asciiCodesCount + backslashesCount + quotesCount
}

private fun String.encodedSize(): Int {
    check(this.startsWith("\"") && this.endsWith("\"")) { "String must be a string literal: $this" }

    return this.replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .length + 2
}

/**
 * [Day 8: Matchsticks](https://adventofcode.com/2015/day/8).
 */
val day8 = solution(8) {
    inputParser = InputParsers.strings

    part1Solver = solver({ "Size difference is $it" }) {
        it.sumOf { (it.length - it.inMemorySize()) }
    }

    part2Solver = solver({ "Size difference is $it" }) {
        it.sumOf { (it.encodedSize() - it.length) }
    }
}