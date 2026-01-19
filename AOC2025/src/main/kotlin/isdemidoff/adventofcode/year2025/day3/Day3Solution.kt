package isdemidoff.adventofcode.year2025.day3

import isdemidoff.utility.solution.solution

private fun findMaxOutputJoltage(battery: String) =
    (0 ..< battery.lastIndex).maxOf { leftIndex ->
        (leftIndex + 1 ..battery.lastIndex).maxOf { rightIndex ->
            "${battery[leftIndex]}${battery[rightIndex]}".toInt()
        }
    }

/**
 * [Day 3: Lobby](https://adventofcode.com/2025/day/3).
 */
val day3 = solution(3) {
    inputParser = singleBlockParser { it }

    part1Solver = solver({ "Max total joltage is $it." }) {
        it.sumOf { findMaxOutputJoltage(it) }
    }
}