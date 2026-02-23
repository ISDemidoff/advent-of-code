package isdemidoff.adventofcode.year2015.day2

import isdemidoff.adventofcode.year2015.day2.entity.wrappedBoxFromList
import isdemidoff.utility.parsing.toIntsList
import isdemidoff.utility.solution.inputparser.functions.mapLines
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution

/**
 * [Day 2: I Was Told There Would Be No Math](https://adventofcode.com/2015/day/2).
 */
val day2 = solution(2) {
    inputParser = StringsInputParsers.splitLinesBy("x")
        .mapLines(::toIntsList)
        .mapLines(wrappedBoxFromList)

    part1Solver = solver({ "We need $it square feet of wrapping." }) {
        it.sumOf { it.calculateWrappingNeeded() }
    }

    part2Solver = solver({ "We need $it feet of ribbon." }) {
        it.sumOf { it.calculateRibbonNeeded() }
    }
}