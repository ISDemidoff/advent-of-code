package isdemidoff.year2015.day6

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day6.entity.Instruction
import isdemidoff.year2015.day6.entity.LightGrid
import isdemidoff.year2015.day6.entity.parseInstruction

/**
 * [Day 6: Probably a Fire Hazard](https://adventofcode.com/2015/day/6).
 */
class Day6SolutionBuilder(day6Path: String) : SimpleSolutionBuilder<Int, List<Instruction>>(
    inputsDir = day6Path,
    inputParser = { readLines(it).map { it.parseInstruction() } },
    solver = { LightGrid().apply { applyInstructions(it) }.countOfLitLights() },
)