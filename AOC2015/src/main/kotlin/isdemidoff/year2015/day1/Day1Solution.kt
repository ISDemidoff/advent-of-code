package isdemidoff.year2015.day1

import isdemidoff.SingleLineDeprecatedSolution
import isdemidoff.SingleLineDeprecatedSolutionBuilder

class Day1Solution(input: String) : SingleLineDeprecatedSolution<Int>(
    input = input,
    solution = {
        it.map {
            when(it) {
                '(' -> 1
                ')' -> -1
                else -> throw IllegalArgumentException("Only parenthesis expected in input string")
            }
        }.reduceIndexed { index, acc, i ->
            (acc + i).also { if (it == -1) println("Entered a basement on index ${index + 1}") }
        }
    },
)

/**
 * [Day 1: Not Quite Lisp](https://adventofcode.com/2015/day/1).
 *
 * There is still a bug: if input string starts with `)`
 * then it will not show that we entered a basement on index 1.
 */
class Day1SolutionBuilder(day1Path: String) : SingleLineDeprecatedSolutionBuilder<Int>(
    inputsDir = day1Path,
    deprecatedSolutionSupplier = { Day1Solution(it) },
)