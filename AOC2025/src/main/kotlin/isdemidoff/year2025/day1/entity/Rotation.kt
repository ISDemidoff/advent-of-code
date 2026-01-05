package isdemidoff.year2025.day1.entity

import isdemidoff.year2025.day1.entity.Rotation.Direction

data class Rotation(
    val direction: Direction,
    val angle: Int,
) {
    constructor(direction: Char, angle: Int) : this(
        direction = direction.getDirection(),
        angle = angle,
    )

    enum class Direction(val value: Char) {
        CLOCKWISE('R'),
        COUNTERCLOCKWISE('L'),
    }
}

private fun Char.getDirection(): Direction = Direction.entries.first { it.value == this }