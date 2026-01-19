package isdemidoff.adventofcode.year2015.day18

import isdemidoff.adventofcode.year2015.day18.entity.LightningGrid
import isdemidoff.utility.solution.solution
import isdemidoff.utility.solution.solver.solver

/**
 * [Day 18: Like a GIF For Your Yard](https://adventofcode.com/2015/day/18).
 */
val day18 = solution(18) {
    inputParser = singleBlockParser { it }

    part1Solver = solver<List<String>, Int, Int>({ result, iterations ->
        "There are total of $result lights on after $iterations iterations."
    }) { input, iterations ->
        LightningGrid(input).apply { repeat(iterations) { iterateOnce() } }.countOfTurnedOnLights()
    }

    part2Solver = solver<List<String>, Int, Int>({ result, iterations ->
        "There are total of $result lights on after $iterations iterations with stuck on corners lights."
    }) { input, iterations ->
        LightningGrid(input, true).apply { repeat(iterations) { iterateOnce() } }.countOfTurnedOnLights()
    }
}