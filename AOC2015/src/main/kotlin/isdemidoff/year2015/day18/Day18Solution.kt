package isdemidoff.year2015.day18

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day18.entity.LightningGrid

class Day18Solution(
    private val lightningGrid: LightningGrid,
    private val numberOfIterations: Int,
) : Solution<Int> {
    override fun solve(): Int {
        lightningGrid.iterate(numberOfIterations)
        return lightningGrid.countOfTurnedOnLights()
    }
}

/**
 * [Day 18: Like a GIF For Your Yard](https://adventofcode.com/2015/day/18).
 */
class Day18SolutionBuilder(
    private val day18Path: String,
    private val numberOfIterations: Int = 100,
    private val cornersOverride: Boolean = false,
) : SimpleSolutionBuilder<Int, LightningGrid>(
    inputsDir = day18Path,
    inputParser = { LightningGrid(readLines(it), cornersOverride) },
    solutionSupplier = { Day18Solution(it, numberOfIterations) }
) {
    fun forNumberOfIterations(numberOfIterations: Int) = Day18SolutionBuilder(day18Path, numberOfIterations, cornersOverride)
    fun withCornersOverride(cornersOverride: Boolean) = Day18SolutionBuilder(day18Path, numberOfIterations, cornersOverride)
}