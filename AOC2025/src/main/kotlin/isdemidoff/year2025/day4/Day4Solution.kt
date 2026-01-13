package isdemidoff.year2025.day4

import isdemidoff.SimpleDeprecatedSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2025.day4.entity.WarehouseGrid

class Day4SolutionBuilder(day4Path: String) : SimpleDeprecatedSolutionBuilder<Int, WarehouseGrid>(
    inputsDir = day4Path,
    inputParser = { WarehouseGrid(readLines(it)) },
    solver = { it.calculateAvailableCells() },
)