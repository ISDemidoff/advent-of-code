package isdemidoff.adventofcode.year2017.day4

import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.utility.strings.countCharacterStatistics

/**
 * [Day 4: High-Entropy Passphrases](https://adventofcode.com/2017/day/4).
 */
val day4 = solution(4) {
    inputParser = StringsInputParsers.singleBlock

    part1Solver = solver({
        "There are $it valid passphrases."
    }) {
        it.count {
            it.split(" ")
                .let { it.toSet().size == it.size }
        }
    }

    part2Solver = solver({
        "There are $it valid passphrases using anagrams method."
    }) {
        it.count {
            it.split(" ").map { countCharacterStatistics(it) }
                .let { it.toSet().size == it.size }
        }
    }
}
