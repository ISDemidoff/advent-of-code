package isdemidoff.adventofcode.year2016.day1

import isdemidoff.solution.inputparser.scope.StringsInputParsers
import isdemidoff.solution.solution
import kotlin.math.abs

/**
 * [Day 1: No Time for a Taxicab](https://adventofcode.com/2016/day/1).
 */
val day1 = solution(1) {
    inputParser = StringsInputParsers.splitLineBy(", ")

    part1Solver = solver({
        "HQ is $it blocks away."
    }) { instructions ->
        var x = 0
        var y = 0
        var currentRotation = 0

        instructions.forEach { instruction ->
            when (instruction.first()) {
                'L' -> currentRotation = (currentRotation + 3) % 4
                'R' -> currentRotation = (currentRotation + 1) % 4
                else -> throw IllegalArgumentException("Expected 'R' or 'L' at start of instruction but got '$instruction'")
            }

            val dist = instruction.drop(1).toInt()

            when (currentRotation) {
                0 -> y += dist
                1 -> x += dist
                2 -> y -= dist
                3 -> x -= dist
                else -> error("Not expected state on $instruction, rotation = $currentRotation")
            }
        }

        return@solver abs(x) + abs(y)
    }

    part2Solver = solver({
        "Actual location of HQ is $it blocks away."
    }) { instructions ->
        var x = 0
        var y = 0
        var currentRotation = 0
        val visitedLocations = mutableSetOf<Pair<Int, Int>>()
        visitedLocations.add(x to y)

        fun walk(dist: Int): Int? {
            repeat(dist) {
                when (currentRotation) {
                    0 -> y += 1
                    1 -> x += 1
                    2 -> y -= 1
                    3 -> x -= 1
                }
                if (visitedLocations.contains(x to y)) return abs(x) + abs(y)
                visitedLocations.add(x to y)
            }
            return null
        }

        instructions.forEach { instruction ->
            when (instruction.first()) {
                'L' -> currentRotation = (currentRotation + 3) % 4
                'R' -> currentRotation = (currentRotation + 1) % 4
                else -> throw IllegalArgumentException("Expected 'R' or 'L' at start of instruction but got '$instruction'")
            }

            walk(instruction.drop(1).toInt())?.let { return@solver it }
        }

        return@solver -1
    }
}