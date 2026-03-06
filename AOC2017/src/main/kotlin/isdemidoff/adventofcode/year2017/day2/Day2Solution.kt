package isdemidoff.adventofcode.year2017.day2

import isdemidoff.solution.inputparser.scope.intTable
import isdemidoff.solution.solution
import isdemidoff.utility.cartesianProduct

/**
 * [Day 2: Corruption Checksum](https://adventofcode.com/2017/day/2).
 */
val day2 = solution(2) {
    inputParser = intTable

    fun findMinMaxDiff(data: List<Int>): Int {
        var (min, max) = data[0] to data[0]
        (1..data.lastIndex).forEach {
            if (data[it] < min) min = data[it]
            if (data[it] > max) max = data[it]
        }
        return max - min
    }

    part1Solver = solver({
        "Checksum for the spreadsheet is $it."
    }) { it.sumOf { findMinMaxDiff(it) } }

    fun findAppropriateDivision(data: List<Int>): Int {
        return cartesianProduct(0..data.lastIndex, 0..data.lastIndex) {
            if (it.first != it.second) data[it.first] to data[it.second] else null
        }
            .filterNotNull()
            .first { it.first % it.second == 0 }
            .let { it.first / it.second }
    }

    part2Solver = solver({
        "Sum of divisions result is $it."
    }) { it.sumOf { findAppropriateDivision(it) } }
}
