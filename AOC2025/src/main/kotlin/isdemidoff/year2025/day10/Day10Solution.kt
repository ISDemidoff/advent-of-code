package isdemidoff.year2025.day10

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2025.day10.entity.Machine
import isdemidoff.year2025.day10.entity.toMachine

class Day10SolutionBuilder(day10Path: String) : SimpleSolutionBuilder<Int, List<Machine>>(
    inputsDir = day10Path,
    inputParser = { readLines(it).map { it.toMachine() } },
    solver = { it.sumOf { it.findLeastNumButtonsToTurnOn() } },
)