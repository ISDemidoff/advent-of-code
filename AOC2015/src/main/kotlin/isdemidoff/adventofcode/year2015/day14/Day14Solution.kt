package isdemidoff.adventofcode.year2015.day14

import isdemidoff.adventofcode.year2015.day14.entity.DistanceTravelledRace
import isdemidoff.adventofcode.year2015.day14.entity.LeadTimeRace
import isdemidoff.adventofcode.year2015.day14.entity.Reindeer
import isdemidoff.adventofcode.year2015.day14.entity.readReindeer
import isdemidoff.solution.inputparser.scope.uniformLinesParser
import isdemidoff.solution.solution
import isdemidoff.solution.solver.solver

/**
 * [Day 14: Reindeer Olympics](https://adventofcode.com/2015/day/14).
 */
val day14 = solution(14) {
    inputParser = uniformLinesParser(readReindeer)

    fun Map<Reindeer, Int>.findBestResult() = this.maxOf { it.value }

    part1Solver = solver<List<Reindeer>, Map<Reindeer, Int>, Int>({ result, seconds ->
        "Max distance travelled after $seconds seconds is ${result.findBestResult()}."
    }) { input, seconds ->
        DistanceTravelledRace(input).getRaceResults(seconds)
    }

    part2Solver = solver<List<Reindeer>, Map<Reindeer, Int>, Int>({ result, seconds ->
        "Max lead time after $seconds seconds is ${result.findBestResult()}."
    }) { input, seconds ->
        LeadTimeRace(input).getRaceResults(seconds)
    }
}
