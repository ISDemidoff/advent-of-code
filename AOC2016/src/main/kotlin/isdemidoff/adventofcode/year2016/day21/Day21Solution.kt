package isdemidoff.adventofcode.year2016.day21

import isdemidoff.adventofcode.year2016.day21.entity.ScrumbleInstruction
import isdemidoff.adventofcode.year2016.day21.entity.applyInstructions
import isdemidoff.adventofcode.year2016.day21.entity.scrumbleInstructionParser
import isdemidoff.adventofcode.year2016.day21.entity.unscrambleByInstructions
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import isdemidoff.solution.solver.solver

/**
 * [Day 21: Scrambled Letters and Hash](https://adventofcode.com/2016/day/21).
 */
val day21 = solution<List<ScrumbleInstruction>, String>(21) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine scrumbleInstructionParser

    part1Solver = solver<List<ScrumbleInstruction>, String, String>({ res, input ->
        "Resulting scrumble of password $input is $res."
    }) { instructions, inputPassword -> inputPassword.applyInstructions(instructions) }

    part2Solver = solver<List<ScrumbleInstruction>, String, String>({ res, input ->
        "Unscramble of $input is $res."
    }) { instructions, inputPassword -> inputPassword.unscrambleByInstructions(instructions) }
}