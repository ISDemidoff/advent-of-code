package isdemidoff.adventofcode.year2016.day24

import isdemidoff.adventofcode.year2016.day24.entity.labyrinthParser
import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

/**
 * [Day 24: Air Duct Spelunking](https://adventofcode.com/2016/day/24).
 */
val day24 = solution(24) {
    inputParser = StringsInputParsers.singleBlock andThen labyrinthParser

    part1Solver = solver({
        "Fewest number of steps to walk around all points is $it."
    }) { it.findShortestPathOutOfOrder() }

    part2Solver = solver({
        "Fewest number of steps to walk around all points and return is $it."
    }) { it.findShortestPathOutOfOrderWithReturn() }
}