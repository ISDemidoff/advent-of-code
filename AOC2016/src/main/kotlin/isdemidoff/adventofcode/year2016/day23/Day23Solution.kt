package isdemidoff.adventofcode.year2016.day23

import isdemidoff.adventofcode.year2016.day23.entity.MapBasedProgramState
import isdemidoff.adventofcode.year2016.day23.entity.ProgramInstruction
import isdemidoff.adventofcode.year2016.day23.entity.ProgramState
import isdemidoff.adventofcode.year2016.day23.entity.instructionReader
import isdemidoff.solution.inputparser.scope.uniformLinesParser
import isdemidoff.solution.solution

/**
 * [Day 23: Safe Cracking](https://adventofcode.com/2016/day/23).
 *
 * No multiply optimizations on part 2 since it's clearly possible to search such patterns (better do it in runtime).
 * I got 62 seconds long solution for part 2, enough for me.
 */
val day23 = solution<List<ProgramInstruction>, ProgramState>(23) {
    inputParser = uniformLinesParser(instructionReader)

    part1Solver = solver({
        "Value at register 'a' is ${it.getRegisterValue("a")}"
    }) {
        MapBasedProgramState(it).apply {
            updateRegisterValue("a") { 7 }
            runProgram()
        }
    }

    part2Solver = solver({
        "Value at register 'a' is now ${it.getRegisterValue("a")}"
    }) {
        MapBasedProgramState(it).apply {
            updateRegisterValue("a") { 12 }
            runProgram()
        }
    }
}
