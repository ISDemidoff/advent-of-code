package isdemidoff.year2025.day1.entity

import isdemidoff.year2025.day1.entity.Rotation.Direction

data class Rotation(
    val direction: Direction,
    val angle: Int,
) {
    constructor(str: String) : this(
        direction = str.first(),
        angle = str.drop(1).toInt(),
    )

    constructor(direction: Char, angle: Int) : this(
        direction = direction.getDirection(),
        angle = angle,
    )

    enum class Direction(val value: Char, val singleClick: Int) {
        CLOCKWISE('R', 1),
        COUNTERCLOCKWISE('L', -1),
    }
}

private fun Char.getDirection(): Direction = Direction.entries.first { it.value == this }