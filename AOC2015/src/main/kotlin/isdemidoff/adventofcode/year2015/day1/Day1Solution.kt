package isdemidoff.adventofcode.year2015.day1

import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.functions.bidirectional.StringsBiDirectionalFunctions
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

/**
 * [Day 1: Not Quite Lisp](https://adventofcode.com/2015/day/1).
 */
val day1 = solution(1) {
    inputParser = StringsInputParsers.singleLine andThen StringsBiDirectionalFunctions.toCharArray andThenOnEveryLine {
        when (it) {
            '(' -> 1
            ')' -> -1
            else -> throw IllegalArgumentException("Only parenthesis expected in input string")
        }
    }

    part1Solver = solver({ "Santa end up at floor $it." }) { it.sumOf { it } }

    part2Solver = solver({ "Santa first time entered basement (-1 floor) on index $it." }) {
        var currentFloor = 0
        it.forEachIndexed { idx, shift ->
            currentFloor += shift
            if (currentFloor == -1) return@solver idx + 1
        }
        return@solver -1
    }
}