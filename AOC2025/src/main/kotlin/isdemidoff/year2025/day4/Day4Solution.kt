package isdemidoff.year2025.day4

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2025.day4.entity.WarehouseGrid

class Day4SolutionBuilder(day4Path: String) : SimpleSolutionBuilder<Int, WarehouseGrid>(
    day4Path,
    { WarehouseGrid(readLines(it)) },
    { it.calculateAvailableCells() },
)