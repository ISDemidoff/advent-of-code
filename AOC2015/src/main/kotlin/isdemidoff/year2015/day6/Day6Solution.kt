package isdemidoff.year2015.day6

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day6.entity.Instruction
import isdemidoff.year2015.day6.entity.Light
import isdemidoff.year2015.day6.entity.LightGrid
import isdemidoff.year2015.day6.entity.TogglingLight
import isdemidoff.year2015.day6.entity.parseInstruction

/**
 * [Day 6: Probably a Fire Hazard](https://adventofcode.com/2015/day/6).
 */
class Day6SolutionBuilder(
    private val day6Path: String,
    private val lightGenerator: () -> Light = { TogglingLight() },
) : SimpleSolutionBuilder<Int, List<Instruction>>(
    inputsDir = day6Path,
    inputParser = { readLines(it).map { it.parseInstruction() } },
    solver = { LightGrid(lightGenerator).apply { applyInstructions(it) }.countOfLitLights() },
) {
    fun withLightGenerator(lightGenerator: () -> Light) = Day6SolutionBuilder(day6Path, lightGenerator)
}