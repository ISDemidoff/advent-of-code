package isdemidoff.adventofcode.year2016.day5

import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.utility.strings.md5hex

/**
 * [Day 5: How About a Nice Game of Chess?](https://adventofcode.com/2016/day/5).
 */
val day5 = solution(5) {
    inputParser = StringsInputParsers.singleLine

    fun findNextInterestingHash(str: String, seed: Int = 1): Pair<Int, String> {
        generateSequence(seed) { it + 1 }
            .forEach {
                val hash = md5hex(str + it)
                if (hash.startsWith("00000")) {
                    return it to hash
                }
            }
        return -1 to "" // Should never happen
    }

    val PASSWORD_LENGTH = 8

    part1Solver = solver({
        "Password for the door is $it."
    }) { doorId ->
        val result = StringBuilder()
        var nextSeed = 1
        repeat(PASSWORD_LENGTH) {
            val (hashSeed, hash) = findNextInterestingHash(doorId, nextSeed)
            result.append(hash[5])
            nextSeed = hashSeed + 1
        }
        result.toString()
    }

    val UNDEF_CHAR = '.'

    part2Solver = solver({
        "Cinematic password for the door is $it."
    }) { doorId ->
        val result = CharArray(PASSWORD_LENGTH) { UNDEF_CHAR }

        var nextSeed = 1
        while (result.any { it == UNDEF_CHAR }) {
            val (hashSeed, hash) = findNextInterestingHash(doorId, nextSeed)
            val pos = hash[5]
            val ch = hash[6]

            if (pos in '0'..'7') {
                val posInt = pos - '0'
                if (result[posInt] == UNDEF_CHAR) {
                    result[posInt] = ch
                }
            }

            nextSeed = hashSeed + 1
        }

        result.joinToString(separator = "")
    }
}