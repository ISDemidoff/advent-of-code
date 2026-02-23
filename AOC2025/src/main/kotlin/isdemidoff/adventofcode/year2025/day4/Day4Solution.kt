package isdemidoff.adventofcode.year2025.day4

import isdemidoff.adventofcode.year2025.day4.entity.WarehouseGrid
import isdemidoff.utility.solution.inputparser.functions.andThen
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution

/**
 * [Day 4: Printing Department](https://adventofcode.com/2025/day/4).
 */
val day4 = solution(4) {
    inputParser = StringsInputParsers.singleBlock andThen ::WarehouseGrid

    part1Solver = solver({ "$it rolls of paper are accessible for forklift." }) { it.calculateAvailableCells() }
}