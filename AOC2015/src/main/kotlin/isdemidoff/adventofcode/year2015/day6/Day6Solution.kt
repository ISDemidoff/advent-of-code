package isdemidoff.adventofcode.year2015.day6

import isdemidoff.adventofcode.year2015.day6.entity.BrightnessLight
import isdemidoff.adventofcode.year2015.day6.entity.Light
import isdemidoff.adventofcode.year2015.day6.entity.LightGrid
import isdemidoff.adventofcode.year2015.day6.entity.TogglingLight
import isdemidoff.adventofcode.year2015.day6.entity.instructionParser
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution

/**
 * [Day 6: Probably a Fire Hazard](https://adventofcode.com/2015/day/6).
 */
val day6 = solution(6) {
    inputParser = StringsInputParsers.singleBlock andThenOnEveryLine instructionParser

    fun solverGenerator(
        formatter: (Int) -> String,
        lightSupplier: () -> Light,
    ) = solver(formatter) { instructions ->
        LightGrid(lightSupplier)
            .apply { applyInstructions(instructions) }
            .totalValueOfLitLights()
    }

    part1Solver = solverGenerator({ "Total $it light lit." }) { TogglingLight() }

    part2Solver = solverGenerator({ "Total $it brightness on all lights." }) { BrightnessLight() }
}
