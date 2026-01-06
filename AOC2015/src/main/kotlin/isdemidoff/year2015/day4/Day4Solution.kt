package isdemidoff.year2015.day4

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readSingleLine
import java.security.MessageDigest

class Day4Solution(private val input: String) : Solution<Int> {
    override fun solve() = generateSequence(1) { it + 1 }
        .first { candidate ->
            makeMd5Hash(candidate)
                .startsWith("00000")
        }

    private fun makeMd5Hash(suffix: Int) =
        MessageDigest.getInstance("MD5")
            .digest(
                (input + suffix)
                    .toByteArray(Charsets.UTF_8)
            )
            .toHexString()
}

class Day4SolutionBuilder(day4Path: String) : SimpleSolutionBuilder<Int, String>(
    day4Path,
    { readSingleLine(it) },
    { Day4Solution(it).solve() }
)