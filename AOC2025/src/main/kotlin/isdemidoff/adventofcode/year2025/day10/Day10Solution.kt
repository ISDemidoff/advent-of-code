package isdemidoff.adventofcode.year2025.day10

import isdemidoff.adventofcode.year2025.day10.entity.toMachine
import isdemidoff.utility.solution.solution

/**
 * [Day 10: Factory](https://adventofcode.com/2025/day/10).
 */
val day10 = solution(10) {
    inputParser = uniformLinesParser { it.toMachine() }

    part1Solver = solver({ "Minimum number or presses to turn on all machines is $it." }) {
        it.sumOf { it.findLeastNumButtonsToTurnOn() }
    }
}