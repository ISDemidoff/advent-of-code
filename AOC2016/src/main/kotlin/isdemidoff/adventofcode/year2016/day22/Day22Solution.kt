package isdemidoff.adventofcode.year2016.day22

import isdemidoff.adventofcode.year2016.day22.entity.createFileSystem
import isdemidoff.adventofcode.year2016.day22.entity.getFastestWayToMoveData
import isdemidoff.adventofcode.year2016.day22.entity.nodeParser
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.utility.cartesianProduct

/**
 * [Day 22: Grid Computing](https://adventofcode.com/2016/day/22).
 *
 * I am totally disappointed with part 2 solution since it has no connection with part 1.
 */
val day22 = solution(22) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine nodeParser

    part1Solver = solver({
        "There are $it viable pairs of nodes."
    }) { nodes -> cartesianProduct(nodes, nodes).count { it.first isViableWith it.second } }

    part2Solver = solver({
        "Fewest number of steps to get goal data is $it."
    }) { getFastestWayToMoveData(createFileSystem(it)) }
}