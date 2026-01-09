package isdemidoff.year2015.day14

import isdemidoff.SimpleSolution
import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day14.entity.RaceConditions
import isdemidoff.year2015.day14.entity.Reindeer
import isdemidoff.year2015.day14.entity.createReindeer

class Day14Solution(
    reindeer: List<Reindeer>,
    private val secondsToPass: Int,
    private val raceConditions: RaceConditions = RaceConditions.DISTANCE_TRAVELLED,
) : SimpleSolution<Map<Reindeer, Int>, List<Reindeer>>(
    input = reindeer,
    solver = { raceConditions.raceGenerator(it).getRaceResults(secondsToPass) },
)

/**
 * [Day 14: Reindeer Olympics](https://adventofcode.com/2015/day/14).
 */
class Day14SolutionBuilder(
    private val day14Path: String,
    private val secondsToPass: Int = 0,
    private val raceConditions: RaceConditions = RaceConditions.DISTANCE_TRAVELLED,
) : SimpleSolutionBuilder<Map<Reindeer, Int>, List<Reindeer>>(
    inputsDir = day14Path,
    inputParser = { readLines(it).map { it.createReindeer() } },
    solver = { Day14Solution(it, secondsToPass, raceConditions).solve() },
) {
    fun withRaceConditions(conditions: RaceConditions) = Day14SolutionBuilder(day14Path, secondsToPass, conditions)
    fun forSeconds(seconds: Int) = Day14SolutionBuilder(day14Path, seconds, raceConditions)
}