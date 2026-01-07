package isdemidoff.year2015.day14

import isdemidoff.SimpleSolution
import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day14.entity.Reindeer
import isdemidoff.year2015.day14.entity.createReindeer

class Day14Solution(
    reindeer: Reindeer,
    private val secondsToPass: Int,
) : SimpleSolution<Int, Reindeer>(
    input = reindeer,
    solver = { it.distanceAfter(secondsToPass) },
)

/**
 * [Day 14: Reindeer Olympics](https://adventofcode.com/2015/day/14).
 */
class Day14SolutionBuilder(
    private val day14Path: String,
    private val secondsToPass: Int = 0,
) : SimpleSolutionBuilder<Int, List<Reindeer>>(
    inputsDir = day14Path,
    inputParser = { readLines(it).map { it.createReindeer() } },
    solver = { it.maxOf { Day14Solution(it, secondsToPass).solve() } },
) {
    fun forSeconds(seconds: Int) = Day14SolutionBuilder(day14Path, seconds)
}