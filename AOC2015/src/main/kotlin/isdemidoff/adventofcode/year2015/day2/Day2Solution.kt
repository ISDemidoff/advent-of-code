package isdemidoff.adventofcode.year2015.day2

import isdemidoff.adventofcode.year2015.day2.entity.wrappedBoxFromList
import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.functions.bidirectional.CollectionsBiDirectionalFunctions.mapMatrix
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toInt
import isdemidoff.solution.inputparser.scope.splitLinesBy
import isdemidoff.solution.solution

/**
 * [Day 2: I Was Told There Would Be No Math](https://adventofcode.com/2015/day/2).
 */
val day2 = solution(2) {
    inputParser = splitLinesBy("x") andThen mapMatrix(toInt) andThenOnEveryLine wrappedBoxFromList

    part1Solver = solver({ "We need $it square feet of wrapping." }) {
        it.sumOf { it.calculateWrappingNeeded() }
    }

    part2Solver = solver({ "We need $it feet of ribbon." }) {
        it.sumOf { it.calculateRibbonNeeded() }
    }
}
