package isdemidoff.year2015.day1

import isdemidoff.utility.solution.solution

/**
 * [Day 1: Not Quite Lisp](https://adventofcode.com/2015/day/1).
 */
val day1 = solution(1) {
    inputParser = singleLineParser {
        it.map { ch ->
            when (ch) {
                '(' -> 1
                ')' -> -1
                else -> throw IllegalArgumentException("Only parenthesis expected in input string")
            }
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