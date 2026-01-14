package isdemidoff.year2015.day6

import isdemidoff.utility.solution.Solver
import isdemidoff.utility.solution.solution
import isdemidoff.year2015.day6.entity.BrightnessLight
import isdemidoff.year2015.day6.entity.Instruction
import isdemidoff.year2015.day6.entity.Light
import isdemidoff.year2015.day6.entity.LightGrid
import isdemidoff.year2015.day6.entity.TogglingLight
import isdemidoff.year2015.day6.entity.parseInstruction

/**
 * [Day 6: Probably a Fire Hazard](https://adventofcode.com/2015/day/6).
 */
val day6 = solution(6) {
    inputParser = uniformLinesParser { it.parseInstruction() }

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