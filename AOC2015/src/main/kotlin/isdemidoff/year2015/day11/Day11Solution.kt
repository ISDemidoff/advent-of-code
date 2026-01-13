package isdemidoff.year2015.day11

import isdemidoff.SingleLineDeprecatedSolution
import isdemidoff.SingleLineDeprecatedSolutionBuilder

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

fun String.getNextValidPassword() =
    generateSequence(this.increment()) { it.increment() }
        .first { it.isValidPassword() }

class Day11Solution(
    input: String,
    private val position: Int = 1,
) : SingleLineDeprecatedSolution<String>(
    input = input,
    solution = { generateSequence(it) { it.getNextValidPassword() }.drop(position).first() }
)

/**
 * [Day 11: Corporate Policy](https://adventofcode.com/2015/day/11).
 */
class Day11SolutionBuilder(
    private val day11Path: String,
    private val position: Int = 1,
) : SingleLineDeprecatedSolutionBuilder<String>(
    inputsDir = day11Path,
    deprecatedSolutionSupplier = { Day11Solution(it, position) },
) {
    fun searchingPosition(pos: Int) = Day11SolutionBuilder(day11Path, pos)
}