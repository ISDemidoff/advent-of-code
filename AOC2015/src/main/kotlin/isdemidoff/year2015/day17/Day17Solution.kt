package isdemidoff.year2015.day17

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readLines

class Day17Solution(
    ints: List<Int>,
    private val totalSum: Int,
) : Solution<List<List<Int>>> {
    private val terms = ints.sorted()

    override fun solve(): List<List<Int>> {
        return findCombinations(availableTerms = terms, totalSum = totalSum)
    }

    private fun findCombinations(
        availableTerms: List<Int>,
        takenTerms: List<Int> = listOf(),
        totalSum: Int,
    ): List<List<Int>> {
        if (totalSum == 0) {
            return listOf(takenTerms)
        } else if (totalSum < 0) {
            return emptyList()
        }

        if (availableTerms.isEmpty()) return emptyList()

        return availableTerms.flatMapIndexed { index, v ->
            findCombinations(
                availableTerms.subList(index + 1, availableTerms.size),
                takenTerms + v,
                totalSum - v,
            )
        }
    }
}

/**
 * [Day 17: No Such Thing as Too Much](https://adventofcode.com/2015/day/17).
 */
class Day17SolutionBuilder(
    private val day17Path: String,
    private val totalSum: Int = 150,
) : SimpleSolutionBuilder<List<List<Int>>, List<Int>>(
    inputsDir = day17Path,
    inputParser = { readLines(it).map { it.toInt() } },
    solutionSupplier = { Day17Solution(it, totalSum) }
) {
    fun forTotalSum(newSum: Int) = Day17SolutionBuilder(day17Path, newSum)
}