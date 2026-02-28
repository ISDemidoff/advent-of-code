package isdemidoff.adventofcode.year2015.day23

import isdemidoff.adventofcode.year2015.day23.entity.Command
import isdemidoff.adventofcode.year2015.day23.entity.IncRegisterCommand
import isdemidoff.adventofcode.year2015.day23.entity.ProgramEnvironment
import isdemidoff.adventofcode.year2015.day23.entity.commandParser
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.solution.solver.solver

/**
 * [Day 23: Opening the Turing Lock](https://adventofcode.com/2015/day/23).
 */
val day23 = solution(23) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine commandParser

    part1Solver = solver<List<Command>, UInt, String>({ result, register ->
        "Value at register '$register' after program run is $result."
    }) { input, register ->
        ProgramEnvironment()
            .runProgram(input)
            .getRegisterValue(register)
    }

    part2Solver = solver<List<Command>, UInt, String>({ result, register ->
        "Value at register '$register' after program run is $result."
    }) { input, register ->
        ProgramEnvironment()
            .apply { runProgram(listOf(IncRegisterCommand("a"))) }
            .runProgram(input)
            .getRegisterValue(register)
    }
}
