package isdemidoff.adventofcode.year2017.day5

import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.scope.PrimitivesInputParsers
import isdemidoff.solution.solution
import isdemidoff.utility.isInvalidPosition

/**
 * [Day 5: A Maze of Twisty Trampolines, All Alike](https://adventofcode.com/2017/day/5).
 */
val day5 = solution(5) {
    inputParser = PrimitivesInputParsers.intLines andThen { it.toMutableList() }

    part1Solver = solver({
        "$it steps taken before reach exit."
    }) { jumps ->
        var stepsTaken = 0
        var currentIndex = 0
        while (!currentIndex.isInvalidPosition(jumps.size)) {
            val jump = jumps[currentIndex]
            jumps[currentIndex] = jump + 1
            currentIndex += jump
            stepsTaken++
        }
        return@solver stepsTaken
    }

    part2Solver = solver({
        "$it steps taken before reach exit."
    }) { jumps ->
        var stepsTaken = 0
        var currentIndex = 0
        while (!currentIndex.isInvalidPosition(jumps.size)) {
            val jump = jumps[currentIndex]
            jumps[currentIndex] = if (jump >= 3) jump - 1 else jump + 1
            currentIndex += jump
            stepsTaken++
        }
        return@solver stepsTaken
    }
}
