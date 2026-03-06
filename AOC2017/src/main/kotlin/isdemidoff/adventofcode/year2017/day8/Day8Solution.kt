package isdemidoff.adventofcode.year2017.day8

import isdemidoff.adventofcode.year2017.day8.entity.ProgramData
import isdemidoff.adventofcode.year2017.day8.entity.instructionParser
import isdemidoff.solution.inputparser.scope.uniformLinesParser
import isdemidoff.solution.solution

/**
 * [Day 8: I Heard You Like Registers](https://adventofcode.com/2017/day/8).
 */
val day8 = solution(8) {
    inputParser = uniformLinesParser(instructionParser)

    part1Solver = solver({
        "Largest register value after competing instructions is $it."
    }) { ProgramData().apply { runInstructions(it) }.findLargestRegisterValue() }

    part2Solver = solver({
        "Largest register value ever held during competing instructions is $it."
    }) { ProgramData().apply { runInstructions(it) }.getLargestValueHeld() }
}
