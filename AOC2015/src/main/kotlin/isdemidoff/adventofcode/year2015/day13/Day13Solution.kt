package isdemidoff.adventofcode.year2015.day13

import isdemidoff.adventofcode.year2015.day13.entity.TableArrangement
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution

/**
 * [Day 13: Knights of the Dinner Table](https://adventofcode.com/2015/day/13).
 */
val day13 = solution(13) {
    inputParser = StringsInputParsers.singleBlock

    part1Solver = solver({ "Max change of happiness is $it." }) {
        TableArrangement(it, false).findBestSetup()
    }

    part2Solver = solver({ "Max change of happiness when sitting with you is $it." }) {
        TableArrangement(it, true).findBestSetup()
    }
}