package isdemidoff.year2015.day5

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readLines

class Day5Solution(private val input: String) : Solution<Boolean> {
    override fun solve() =
        input.containsThreeVowels()
                && input.containsDoubleLetter()
                && !input.containsForbiddenSubstring()

    private fun String.containsThreeVowels() =
        """[aeiou]""".toRegex().findAll(this).take(3).toList().size == 3

    private fun String.containsDoubleLetter() =
        this.zipWithNext().any { (a, b) -> a == b }

    private fun String.containsForbiddenSubstring() =
        """(ab)|(cd)|(pq)|(xy)""".toRegex().find(this) != null
}

class Day5SolutionBuilder(day5Path: String) : SimpleSolutionBuilder<Int, List<String>>(
    day5Path,
    { readLines(it) },
    { it.count { Day5Solution(it).solve() } },
)