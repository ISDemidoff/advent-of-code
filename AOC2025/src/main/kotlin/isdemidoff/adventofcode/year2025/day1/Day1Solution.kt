package isdemidoff.adventofcode.year2025.day1

import isdemidoff.adventofcode.year2025.day1.entity.Rotation
import isdemidoff.utility.solution.solution

private fun Int.isStartingPosition() = this % 100 == 0

/**
 * [Day 1: Secret Entrance](https://adventofcode.com/2025/day/1).
 */
val day1 = solution<List<Rotation>, Int>(1) {
    inputParser = uniformLinesParser(::Rotation)

    part1Solver = solver({ "Password is $it." }) { rotations ->
        var position = 50
        var result = 0
        rotations.forEach { (direction, angle) ->
            position += angle * direction.singleClick
            if (position.isStartingPosition()) result++
        }
        result
    }

    part2Solver = solver({ "Password method 0x434C49434B is $it." }) { rotations ->
        var position = 50
        var result = 0
        rotations.forEach { (direction, angle) ->
            repeat(angle) {
                position += direction.singleClick
                if (position.isStartingPosition()) result++
            }
        }
        result
    }
}