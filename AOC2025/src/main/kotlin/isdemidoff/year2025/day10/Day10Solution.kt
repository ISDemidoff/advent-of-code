package isdemidoff.year2025.day10

import isdemidoff.utility.solution.solution
import isdemidoff.year2025.day10.entity.toMachine

/**
 * [Day 10: Factory](https://adventofcode.com/2025/day/10).
 */
val day10 = solution(10) {
    inputParser = uniformLinesParser { it.toMachine() }

    part1Solver = solver({ "Minimum number or presses to turn on all machines is $it." }) {
        it.sumOf { it.findLeastNumButtonsToTurnOn() }
    }
}