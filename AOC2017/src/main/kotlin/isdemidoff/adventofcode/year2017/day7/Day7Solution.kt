package isdemidoff.adventofcode.year2017.day7

import isdemidoff.adventofcode.year2017.day7.entity.createGraph
import isdemidoff.adventofcode.year2017.day7.entity.programNodeDefinitionParser
import isdemidoff.solution.complexSolution
import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.scope.uniformLinesParser

/**
 * [Day 7: Recursive Circus](https://adventofcode.com/2017/day/7).
 */
val day7 = complexSolution(7) {
    inputParser = uniformLinesParser(programNodeDefinitionParser) andThen createGraph

    part1Solver = solver({
        "The name of the bottom program is $it."
    }) { it.findBottomProgram().name }

    part2Solver = solver({
        "Unbalanced program node should've weight $it."
    }) { it.findUnbalancedProgramDesiredState() }
}
