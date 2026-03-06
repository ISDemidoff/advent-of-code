package isdemidoff.adventofcode.year2015.day4

import isdemidoff.solution.inputparser.scope.singleLine
import isdemidoff.solution.solution
import isdemidoff.solution.solver.solver
import isdemidoff.utility.strings.md5hex

/**
 * [Day 4: The Ideal Stocking Stuffer](https://adventofcode.com/2015/day/4).
 */
val day4 = solution(4) {
    inputParser = singleLine

    val solver = solver<String, Int, String>({ result, _ ->
        "Lowest positive number to add is $result."
    }) { str, targetStartingPattern ->
        generateSequence(1) { it + 1 }
            .first { md5hex(str + it).startsWith(targetStartingPattern) }
    }

    part1Solver = solver
    part2Solver = solver
}
