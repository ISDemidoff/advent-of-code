package isdemidoff.year2015.day8

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.SingleLineSolution
import isdemidoff.utility.input.readLines

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

class Day8Solution(input: String) : SingleLineSolution<Int>(
    input = input,
    solution = { it.length - it.inMemorySize() },
)

/**
 * [Day 8: Matchsticks](https://adventofcode.com/2015/day/8).
 */
class Day8SolutionBuilder(day8Path: String) : SimpleSolutionBuilder<Int, List<String>>(
    inputsDir = day8Path,
    inputParser = { readLines(it) },
    solver = { it.sumOf { Day8Solution(it).solve() } },
)