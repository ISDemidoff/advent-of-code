package isdemidoff.adventofcode.year2016.day8

import isdemidoff.adventofcode.year2016.day8.entity.Command
import isdemidoff.adventofcode.year2016.day8.entity.Screen
import isdemidoff.adventofcode.year2016.day8.entity.parseCommand
import isdemidoff.utility.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution

/**
 * [Day 8: Two-Factor Authentication](https://adventofcode.com/2016/day/8).
 */
val day8 = solution(8) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine ::parseCommand

    val fn = object: (List<Command>) -> Screen {
        override fun invoke(commands: List<Command>) = Screen(50, 6).apply { executeCommands(commands) }
    }

    part1Solver = solver({
        "After executing all command, there are ${it.getNumberOfLitPixels()} pixels lit."
    }, fn)

    part2Solver = solver({
        "Examining the screen, you see next message:\n${it.splitGridChunked(5)}"
    }, fn)
}