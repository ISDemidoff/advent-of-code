package isdemidoff.adventofcode.year2016.day14

import isdemidoff.utility.other.zipTriple
import isdemidoff.utility.solution.inputparser.InputParsers
import isdemidoff.utility.solution.solution
import isdemidoff.utility.strings.md5hex
import java.util.TreeSet

internal fun stretchedHash(str: String): String {
    var md5 = md5hex(str)
    repeat(2016) { md5 = md5hex(md5) }
    return md5
}

private fun extractFirstTriplet(str: String): Char? =
    str.zipTriple { a, b, c -> if (a == b && b == c) a else null }.filterNotNull().firstOrNull()

private fun hasFivelet(str: String, ch: Char): Boolean =
    str.contains(ch.toString().repeat(5))

/**
 * [Day 14: One-Time Pad](https://adventofcode.com/2016/day/14).
 *
 * This one had a trick. When you essentially use key candidate caching (ofc I used it for part 1 as well),
 * you can encounter situation when 64th "key acknowledger" (fivelet that confirms that we met a key earlier)
 * occurs earlier that 64th actual key because index for his "key acknowledger" located farther.
 * Interesting that I had correct answer in part 1 using "just throw key when met 64th confirmation" strategy.
 */
val day14 = solution(14) {
    inputParser = InputParsers.singleString

    fun findNthPadKeyIndex(salt: String, hashFunction: (String) -> String): Int {
        val targetN = 64
        var index = 0
        val keyCandidates = ArrayDeque<Pair<Int, Char>>() // index to char
        val keys = TreeSet<Int>()
        var threshold: Int? = null

        while (true) {
            // Remove non actual candidates
            while (keyCandidates.isNotEmpty()) {
                val (key, _) = keyCandidates.first()
                if (key + 1000 <= index) {
                    keyCandidates.removeFirst()
                    if (threshold != null && key > threshold) {
                        // We calculated at least 64 keys (can be more) and ensured that we will not meet keys
                        // smaller than 64th added to found keys set. Time to get 64th key from sorted form.
                        return keys.sorted()[targetN - 1]
                    }
                } else {
                    break
                }
            }

            val hash = hashFunction("$salt$index")

            // check candidate
            val keyCandidatesIterator = keyCandidates.iterator()
            while (keyCandidatesIterator.hasNext()) {
                val (key, ch) = keyCandidatesIterator.next()
                if (hasFivelet(hash, ch)) {
                    keys.add(key)
                    keyCandidatesIterator.remove()
                    if (keys.size == targetN) {
                        // We met above threshold for 64th key
                        threshold = key
                    }
                }
            }

            // check for adding a candidate
            extractFirstTriplet(hash)?.let { ch ->
                keyCandidates.addLast(index to ch)
            }

            ++index
        }
    }

    part1Solver = solver({
        "Index $it produced 64th key."
    }) {
        findNthPadKeyIndex(
            salt = it,
            hashFunction = ::md5hex,
        )
    }

    part2Solver = solver({
        "Index $it produced 64th key when using key streching."
    }) {
        findNthPadKeyIndex(
            salt = it,
            hashFunction = ::stretchedHash,
        )
    }
}