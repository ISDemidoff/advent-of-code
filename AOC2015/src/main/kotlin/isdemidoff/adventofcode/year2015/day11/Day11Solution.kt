package isdemidoff.adventofcode.year2015.day11

import isdemidoff.utility.solution.solution

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

/**
 * [Day 11: Corporate Policy](https://adventofcode.com/2015/day/11).
 */
val day11 = solution(11) {
    inputParser = singleLineParser { it }

    part1Solver = solver({ "Next password is $it" }) {
        generateSequence(it) { it.getNextValidPassword() }.drop(1).first()
    }

    part2Solver = solver({ "Next password after next is $it" }) {
        generateSequence(it) { it.getNextValidPassword() }.drop(2).first()
    }
}