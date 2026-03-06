package isdemidoff.adventofcode.year2016.day12

import isdemidoff.adventofcode.year2016.day23.entity.MapBasedProgramState
import isdemidoff.adventofcode.year2016.day23.entity.ProgramInstruction
import isdemidoff.adventofcode.year2016.day23.entity.ProgramState
import isdemidoff.adventofcode.year2016.day23.entity.instructionReader
import isdemidoff.solution.inputparser.scope.uniformLinesParser
import isdemidoff.solution.solution

/**
 * [Day 12: Leonardo's Monorail](https://adventofcode.com/2016/day/12).
 */
val day12 = solution<List<ProgramInstruction>, ProgramState>(12) {
    inputParser = uniformLinesParser(instructionReader)

    part1Solver = solver({
        "Value at register 'a' is ${it.getRegisterValue("a")}"
    }) { MapBasedProgramState(it).apply { runProgram() } }

    part2Solver = solver({
        "With extra update value at register 'a' is ${it.getRegisterValue("a")}"
    }) {
        MapBasedProgramState(it).apply {
            updateRegisterValue("c") { 1 }
            runProgram()
        }
    }
}
