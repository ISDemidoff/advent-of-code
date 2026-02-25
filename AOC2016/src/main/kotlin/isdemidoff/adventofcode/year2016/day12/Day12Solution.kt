package isdemidoff.adventofcode.year2016.day12

import isdemidoff.adventofcode.year2016.day12.entity.MapBasedProgramState
import isdemidoff.adventofcode.year2016.day12.entity.ProgramInstruction
import isdemidoff.adventofcode.year2016.day12.entity.ProgramState
import isdemidoff.adventofcode.year2016.day12.entity.readInstruction
import isdemidoff.adventofcode.year2016.day12.entity.runProgram
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

/**
 * [Day 12: Leonardo's Monorail](https://adventofcode.com/2016/day/12).
 */
val day12 = solution<List<ProgramInstruction>, ProgramState>(12) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine ::readInstruction

    part1Solver = solver({
        "Value at register 'a' is ${it.getRegisterValue("a")}"
    }) { runProgram(program = it) }

    part2Solver = solver({
        "With extra update value at register 'a' is ${it.getRegisterValue("a")}"
    }) { runProgram(state = MapBasedProgramState().apply { updateRegisterValue("c") { 1 } }, program = it) }
}