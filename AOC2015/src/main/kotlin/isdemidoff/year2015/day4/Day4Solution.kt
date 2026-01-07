package isdemidoff.year2015.day4

import isdemidoff.SingleLineSolution
import isdemidoff.SingleLineSolutionBuilder
import java.security.MessageDigest

private fun String.md5hex(): String = MessageDigest.getInstance("MD5")
    .digest(this.toByteArray(Charsets.UTF_8))
    .toHexString()

class Day4Solution(input: String) : SingleLineSolution<Int>(
    input = input,
    solution = { str ->
        generateSequence(1) { it + 1 }
            .first { (str + it).md5hex().startsWith("00000") }
    }
)

class Day4SolutionBuilder(day4Path: String) : SingleLineSolutionBuilder<Int>(
    inputsDir = day4Path,
    solutionSupplier = { Day4Solution(it) }
)