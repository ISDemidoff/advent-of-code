package isdemidoff.year2015.day17

import isdemidoff.utility.solution.solution
import isdemidoff.utility.solution.solver.solver
import isdemidoff.year2015.day17.entity.SumCombiner

/**
 * [Day 17: No Such Thing as Too Much](https://adventofcode.com/2015/day/17).
 */
val day17 = solution(17) {
    inputParser = uniformLinesParser { it.toInt() }

    fun solve(input: List<Int>, totalSum: Int): List<List<Int>> =
        SumCombiner(input).findCombinations(totalSum)

    part1Solver = solver<List<Int>, List<List<Int>>, Int>({ result, _ ->
        "Total number of combinations is ${result.size}."
    }) { input, totalSum -> solve(input, totalSum) }

    part2Solver = solver<List<Int>, List<List<Int>>, Int>({ result, _ ->
        val minSize = result.minOf { it.size }
        "Total number of combinations is ${result.count { it.size == minSize }}."
    }) { input, totalSum -> solve(input, totalSum) }
}