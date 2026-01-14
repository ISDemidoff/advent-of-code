package isdemidoff.year2015.day5

import isdemidoff.utility.solution.solution

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

/**
 * [Day 5: Doesn't He Have Intern-Elves For This?](https://adventofcode.com/2015/day/5).
 */
val day5 = solution(5) {
    inputParser = uniformLinesParser { it }

    part1Solver = solver({ "There are $it nice strings." }) { lines ->
        lines.count { it.containsThreeVowels() && it.containsDoubleLetter() && !it.containsForbiddenSubstring() }
    }

    part2Solver = solver({ "There are $it nice strings using new rules." }) { lines ->
        lines.count { it.containsTwoRepeatingPairs() && it.containsInfixedDoubleLetter() }
    }
}