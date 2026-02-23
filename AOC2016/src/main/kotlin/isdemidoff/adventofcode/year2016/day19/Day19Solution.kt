package isdemidoff.adventofcode.year2016.day19

import isdemidoff.utility.solution.inputparser.scope.PrimitivesInputParsers
import isdemidoff.utility.solution.solution

/**
 * [Day 19: An Elephant Named Joseph](https://adventofcode.com/2016/day/19).
 *
 * There is a huge hint in name referring to [Josephus problem](https://en.wikipedia.org/wiki/Josephus_problem),
 * which can be done [using bitwise operations](https://gist.github.com/a6y3ap/0b552ac514d6f83a34f44dc3c0301f62).
 *
 * Part 2 is harder since I couldn't find easy solution, so I developed "classic" DP solution.
 */
val day19 = solution(19) {
    inputParser = PrimitivesInputParsers.singleInt

    part1Solver = solver({
        "Elf $it will take all the presents."
    }) { (it shl 1).takeHighestOneBit().inv() and (it shl 1).or(1) }

    part2Solver = solver({
        "Elf $it will take all the presents with modified rules."
    }) { n ->
        var result = 1 // for n=2
        (3..n).forEach { countOfElements ->
            // Note that person with index `countOfElements / 2 + 1` gets expelled.
            result = if (result < countOfElements / 2) {
                // Shift 1 when n-1 solution do not pass expelled person.
                result + 1
            } else {
                // Shift 2 when n-1 solution passes expelled person + special threat for last (1st) person.
                (result + 2).let { if (it > countOfElements) it - countOfElements else it }
            }
        }
        return@solver result
    }
}