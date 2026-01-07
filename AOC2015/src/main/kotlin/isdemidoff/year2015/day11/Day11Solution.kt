package isdemidoff.year2015.day11

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readSingleLine

internal fun String.isValidPassword(): Boolean {
    return this.hasIncreasingStraight() && this.hasNoForbiddenLetters() && this.hasTwoPairsOfLetters()
}

internal fun String.hasIncreasingStraight(): Boolean {
    return this.zipWithNext()
        .zipWithNext { leftPair, rightPair ->
            rightPair.second - rightPair.first == 1
                    && leftPair.second - leftPair.first == 1
        }
        .any { it }
}

internal fun String.hasNoForbiddenLetters() = """[iol]""".toRegex().containsMatchIn(this).not()

internal fun String.hasTwoPairsOfLetters(): Boolean {
    val foundPairs = mutableSetOf<Char>()
    this.zipWithNext { left, right ->
        if (left == right) foundPairs.add(left)
    }
    return foundPairs.size > 1
}

fun String.increment(): String {
    val last = this.last()
    val withoutLast = this.dropLast(1)

    return if (last == 'z') withoutLast.increment() + 'a' else withoutLast + (last + 1)
}

class Day11Solution(private val input: String) : Solution<String> {
    override fun solve() = generateSequence(input.increment()) { it.increment() }.first { it.isValidPassword() }
}

class Day11SolutionBuilder(day11Path: String) : SimpleSolutionBuilder<String, String>(
    day11Path,
    { readSingleLine(it) },
    { Day11Solution(it).solve() },
)