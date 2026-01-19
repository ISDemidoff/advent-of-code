package isdemidoff.adventofcode.year2015.day2

import isdemidoff.adventofcode.year2015.day2.entity.WrappedBox
import isdemidoff.utility.parseUnescapedCsvInputLine
import isdemidoff.utility.solution.solution

/**
 * [Day 2: I Was Told There Would Be No Math](https://adventofcode.com/2015/day/2).
 */
val day2 = solution(2) {
    inputParser = uniformLinesParser {
        it.parseUnescapedCsvInputLine('x') { it.toString().toInt() }
            .also { require(it.size == 3) { "There must be exactly 3 dimensions specified, but got $it" } }
            .let { (x, y, z) -> WrappedBox(x, y, z) }
    }

    part1Solver = solver({ "We need $it square feet of wrapping." }) {
        it.sumOf { it.calculateWrappingNeeded() }
    }

    part2Solver = solver({ "We need $it feet of ribbon." }) {
        it.sumOf { it.calculateRibbonNeeded() }
    }
}