package isdemidoff.year2015.day5

import isdemidoff.SimpleDeprecatedSolutionBuilder
import isdemidoff.SingleLineDeprecatedSolution
import isdemidoff.utility.input.readLines

private fun String.containsThreeVowels() =
    """[aeiou]""".toRegex().findAll(this).take(3).toList().size == 3

private fun String.containsDoubleLetter() =
    this.zipWithNext().any { (a, b) -> a == b }

private fun String.containsForbiddenSubstring() =
    """(ab)|(cd)|(pq)|(xy)""".toRegex().find(this) != null

private fun String.containsTwoRepeatingPairs() =
    this.zipWithNext().any { (l, r) -> """$l$r""".toRegex().findAll(this).count() > 1 }

private fun String.containsInfixedDoubleLetter() =
    this.zipWithNext().zipWithNext().any { (leftPair, rightPair) -> leftPair.first == rightPair.second }

enum class RulesSet(vararg val rules: (String) -> Boolean) {
    PART_ONE(
        { it.containsThreeVowels() },
        { it.containsDoubleLetter() },
        { !it.containsForbiddenSubstring() },
    ),

    PART_TWO(
        { it.containsTwoRepeatingPairs() },
        { it.containsInfixedDoubleLetter() }
    ),
}

infix fun String.satisfies(rulesSet: RulesSet) = rulesSet.rules.all { it(this) }

class Day5Solution(
    input: String,
    private val rulesSet: RulesSet = RulesSet.PART_ONE,
) : SingleLineDeprecatedSolution<Boolean>(
    input = input,
    solution = { it satisfies rulesSet },
)

/**
 * [Day 5: Doesn't He Have Intern-Elves For This?](https://adventofcode.com/2015/day/5).
 */
class Day5SolutionBuilder(
    private val day5Path: String,
    private val rulesSet: RulesSet = RulesSet.PART_ONE,
) : SimpleDeprecatedSolutionBuilder<Int, List<String>>(
    inputsDir = day5Path,
    inputParser = { readLines(it) },
    solver = { it.count { Day5Solution(it, rulesSet).solve() } },
) {
    fun forRulesSet(newRulesSet: RulesSet) = Day5SolutionBuilder(day5Path, newRulesSet)
}