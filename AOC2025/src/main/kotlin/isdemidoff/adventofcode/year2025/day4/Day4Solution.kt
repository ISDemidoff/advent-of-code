package isdemidoff.adventofcode.year2025.day4

import isdemidoff.adventofcode.year2025.day4.entity.WarehouseGrid
import isdemidoff.utility.solution.solution

/**
 * [Day 4: Printing Department](https://adventofcode.com/2025/day/4).
 */
val day4 = solution(4) {
    inputParser = singleBlockParser(::WarehouseGrid)

    part1Solver = solver({ "$it rolls of paper are accessible for forklift." }) { it.calculateAvailableCells() }
}