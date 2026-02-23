package isdemidoff.adventofcode.year2016.day9

import isdemidoff.utility.keyValueBy
import isdemidoff.utility.solution.inputparser.InputParsers
import isdemidoff.utility.solution.solution

internal fun calculateDecompressedLength(input: String, recursiveDecompress: Boolean): Long {
    if (input.isEmpty()) return 0L

    if (input.startsWith("(")) {
        val (chCount, repetitions) = input.substringAfter("(").substringBefore(")")
            .keyValueBy("x", String::toInt, String::toInt)
        val afterMarkerString = input.substringAfter(")")
        val markerUnfoldedDecompressedLength = if (recursiveDecompress) {
            calculateDecompressedLength(afterMarkerString.substring(0, chCount), true)
        } else {
            chCount.toLong()
        }
        return calculateDecompressedLength(afterMarkerString.substring(chCount), recursiveDecompress) +
                markerUnfoldedDecompressedLength * repetitions.toLong()
    } else {
        val nextMarketIdx = input.indexOfFirst { it == '(' }.takeUnless { it == -1 } ?: input.length
        return nextMarketIdx.toLong() + calculateDecompressedLength(input.substring(nextMarketIdx), recursiveDecompress)
    }
}

/**
 * [Day 9: Explosives in Cyberspace](https://adventofcode.com/2016/day/9).
 *
 * This solution actually rely on foldable "compressing", when any inner markers never interact with sections unaffected by outer marker.
 */
val day9 = solution(9) {
    inputParser = InputParsers.singleString

    part1Solver = solver({
        "Length of decompressed file is $it."
    }) { calculateDecompressedLength(it, false) }

    part2Solver = solver({
        "Length of decompressed v2 file is $it."
    }) { calculateDecompressedLength(it, true) }
}