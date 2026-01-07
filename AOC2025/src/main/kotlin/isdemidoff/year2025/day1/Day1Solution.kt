package isdemidoff.year2025.day1

import isdemidoff.RealSimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2025.day1.entity.Rotation
import isdemidoff.year2025.day1.entity.Rotation.Direction

private fun Int.isStartingPosition() = this % 100 == 0

private infix fun Int.rotate(rotation: Rotation): Int = when (rotation.direction) {
    Direction.CLOCKWISE -> this + rotation.angle
    Direction.COUNTERCLOCKWISE -> this - rotation.angle
}

class Day1SolutionBuilder(day1Path: String) : RealSimpleSolutionBuilder<Int, List<Rotation>>(
    inputsDir = day1Path,
    inputParser = { filename ->
        readLines(filename)
            .map { Rotation(it[0], it.drop(1).toInt()) }
    },
    solver = { rotations ->
        var position = 50
        var result = 0
        rotations.forEach {
            position = position rotate it
            if (position.isStartingPosition()) result++
        }
        result
    },
)