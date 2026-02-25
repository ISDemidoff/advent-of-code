package isdemidoff.adventofcode.year2025.day10

import isdemidoff.adventofcode.year2025.day10.entity.parseMachine
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

/**
 * [Day 10: Factory](https://adventofcode.com/2025/day/10).
 */
val day10 = solution(10) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine ::parseMachine

    part1Solver = solver({ "Minimum number or presses to turn on all machines is $it." }) {
        it.sumOf { it.findLeastNumButtonsToTurnOn() }
    }
}