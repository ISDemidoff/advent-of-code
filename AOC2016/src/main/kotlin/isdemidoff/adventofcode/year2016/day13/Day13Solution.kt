package isdemidoff.adventofcode.year2016.day13

import isdemidoff.adventofcode.year2016.day13.entity.Building
import isdemidoff.adventofcode.year2016.day13.entity.parsePuzzleInput
import isdemidoff.utility.solution.solution

/**
 * [Day 13: A Maze of Twisty Little Cubicles](https://adventofcode.com/2016/day/13).
 */
val day13 = solution(13) {
    inputParser = twoBlocksParser { parsePuzzleInput(it) }

    part1Solver = solver({
        "Fewest number of steps to reach goal is $it."
    }) { Building(it).findSolution() }

    part2Solver = solver({
        "You can reach $it locations in 50 steps."
    }) { Building(it).findLocations(50) }
}