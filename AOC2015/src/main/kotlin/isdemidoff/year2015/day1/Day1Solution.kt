package isdemidoff.year2015.day1

import isdemidoff.SingleLineSolution
import isdemidoff.SingleLineSolutionBuilder

class Day1Solution(input: String) : SingleLineSolution<Int>(
    input = input,
    solution = {
        it.sumOf {
            when(it) {
                '(' -> 1
                ')' -> -1
                else -> throw IllegalArgumentException("Only parenthesis expected in input string")
            }
        }
    },
)

/**
 * [Day 1: Not Quite Lisp](https://adventofcode.com/2015/day/1).
 */
class Day1SolutionBuilder(day1Path: String) : SingleLineSolutionBuilder<Int>(
    inputsDir = day1Path,
    solutionSupplier = { Day1Solution(it) },
)