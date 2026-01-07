package isdemidoff.year2015.day5

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.SingleLineSolution
import isdemidoff.utility.input.readLines

private fun String.containsThreeVowels() =
    """[aeiou]""".toRegex().findAll(this).take(3).toList().size == 3

private fun String.containsDoubleLetter() =
    this.zipWithNext().any { (a, b) -> a == b }

private fun String.containsForbiddenSubstring() =
    """(ab)|(cd)|(pq)|(xy)""".toRegex().find(this) != null

class Day5Solution(input: String) : SingleLineSolution<Boolean>(
    input = input,
    solution = {
        it.containsThreeVowels()
                && it.containsDoubleLetter()
                && !it.containsForbiddenSubstring()
    },
)

/**
 * [Day 5: Doesn't He Have Intern-Elves For This?](https://adventofcode.com/2015/day/5).
 */
class Day5SolutionBuilder(day5Path: String) : SimpleSolutionBuilder<Int, List<String>>(
    inputsDir = day5Path,
    inputParser = { readLines(it) },
    solver = { it.count { Day5Solution(it).solve() } },
)