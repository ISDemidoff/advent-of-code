package isdemidoff.year2015.day4

import isdemidoff.SingleLineSolution
import isdemidoff.SingleLineSolutionBuilder
import java.security.MessageDigest

private fun String.md5hex(): String = MessageDigest.getInstance("MD5")
    .digest(this.toByteArray(Charsets.UTF_8))
    .toHexString()

val DEFAULT_STARTING_PATTERN = "0".repeat(5)

class Day4Solution(
    input: String,
    private val targetStartingPattern: String = DEFAULT_STARTING_PATTERN,
) : SingleLineSolution<Int>(
    input = input,
    solution = { str ->
        generateSequence(1) { it + 1 }
            .first { (str + it).md5hex().startsWith(targetStartingPattern) }
    }
)

/**
 * [Day 4: The Ideal Stocking Stuffer](https://adventofcode.com/2015/day/4).
 */
class Day4SolutionBuilder(
    private val day4Path: String,
    private val targetStartingPattern: String = DEFAULT_STARTING_PATTERN,
) : SingleLineSolutionBuilder<Int>(
    inputsDir = day4Path,
    solutionSupplier = { Day4Solution(it, targetStartingPattern) }
) {
    fun withStartingPattern(startingPattern: String) = Day4SolutionBuilder(day4Path, startingPattern)
}