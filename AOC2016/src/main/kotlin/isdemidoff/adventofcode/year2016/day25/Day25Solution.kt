package isdemidoff.adventofcode.year2016.day25

import isdemidoff.adventofcode.year2016.day23.entity.MapBasedProgramState
import isdemidoff.adventofcode.year2016.day23.entity.instructionReader
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

/**
 * [Day 25: Clock Signal](https://adventofcode.com/2016/day/25).
 */
val day25 = solution(25) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine instructionReader

    part1Solver = solver({
        "Minimal value passed to 'a' to produce needed sequence is $it."
    }) { instructions ->
        (1..1000).first { inputA ->
            MapBasedProgramState(instructions).apply {
                updateRegisterValue("a") { inputA }
                runProgram(50000)
            }
                .getOutput()
                .mapIndexed { index, value -> index % 2 == value }
                .all { it }
        }
    }

    part2Solver = specialSolver()
}