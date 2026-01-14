package isdemidoff.year2015.day4

import isdemidoff.utility.solution.solution
import isdemidoff.utility.solution.solver
import java.security.MessageDigest

private fun String.md5hex(): String = MessageDigest.getInstance("MD5")
    .digest(this.toByteArray(Charsets.UTF_8))
    .toHexString()

/**
 * [Day 4: The Ideal Stocking Stuffer](https://adventofcode.com/2015/day/4).
 */
val day4 = solution(4) {
    inputParser = singleLineParser { it }

    val solver = solver<String, Int, String>({ result, _ ->
        "Lowest positive number to add is $result."
    }) { str, targetStartingPattern ->
        generateSequence(1) { it + 1 }
            .first { (str + it).md5hex().startsWith(targetStartingPattern) }
    }

    part1Solver = solver
    part2Solver = solver
}