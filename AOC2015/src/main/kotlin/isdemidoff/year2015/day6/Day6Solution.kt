package isdemidoff.year2015.day6

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day6.entity.Instruction
import isdemidoff.year2015.day6.entity.LightGrid
import isdemidoff.year2015.day6.entity.parseInstruction

class Day6SolutionBuilder(day6Path: String) : SimpleSolutionBuilder<Int, List<Instruction>>(
    day6Path,
    { readLines(it).map { it.parseInstruction() } },
    { LightGrid().apply { applyInstructions(it) }.countOfLitLights() },
)