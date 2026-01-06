package isdemidoff.year2015.day1

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readSingleLine

class Day1Solution(val input: String) : Solution<Int> {
    override fun solve(): Int {
        var result = 0
        input.forEach { when(it) {
            '(' -> result += 1
            ')' -> result -= 1
            else -> throw IllegalArgumentException("Only parenthesis expected in input string")
        } }
        return result
    }
}

class Day1SolutionBuilder(day1Path: String) : SimpleSolutionBuilder<Int, String>(
    day1Path,
    { readSingleLine(it) },
    { Day1Solution(it).solve() },
)