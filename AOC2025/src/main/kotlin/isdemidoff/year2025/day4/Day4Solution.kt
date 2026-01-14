package isdemidoff.year2025.day4

import isdemidoff.utility.solution.solution
import isdemidoff.year2025.day4.entity.WarehouseGrid

/**
 * [Day 4: Printing Department](https://adventofcode.com/2025/day/4).
 */
val day4 = solution(4) {
    inputParser = singleBlockParser { WarehouseGrid(it) }

    part1Solver = solver({ "$it rolls of paper are accessible for forklift." }) { it.calculateAvailableCells() }
}