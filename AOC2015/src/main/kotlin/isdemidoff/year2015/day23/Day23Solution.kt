package isdemidoff.year2015.day23

import isdemidoff.utility.solution.solution
import isdemidoff.utility.solution.solver.solver
import isdemidoff.year2015.day23.entity.Command
import isdemidoff.year2015.day23.entity.IncRegisterCommand
import isdemidoff.year2015.day23.entity.ProgramEnvironment
import isdemidoff.year2015.day23.entity.parseCommand

/**
 * [Day 23: Opening the Turing Lock](https://adventofcode.com/2015/day/23).
 */
val day23 = solution(23) {
    inputParser = uniformLinesParser { parseCommand(it) }

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