package isdemidoff.adventofcode.year2016.day17

import isdemidoff.solution.complexSolution
import isdemidoff.solution.inputparser.scope.singleLine
import isdemidoff.utility.strings.md5hex
import kotlin.math.max

enum class Direction(
    val str: Char,
) {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R'),
}

private fun convertPathToString(path: List<Direction>) = path.joinToString(separator = "") { it.str.toString() }

data class LabyrinthState(
    val size: Pair<Int, Int> = 4 to 4,
    val passcode: String,
    val pathTakenSoFar: List<Direction> = listOf(),
    val position: Pair<Int, Int> = 1 to 1, // 1-indexed
) {
    init {
        check(position.first > 0 && position.second > 0) { "Incorrect position: $position" }
    }

    fun availableDirections(): List<Direction> =
        md5hex(passcode + convertPathToString(pathTakenSoFar))
            .take(4)
            .asIterable()
            .zip(Direction.entries)
            .filter { it.first > 'a' }
            .map { it.second }
            .filterNot { isBadDirection(it) }

    private fun isBadDirection(direction: Direction): Boolean =
        when (direction) {
            Direction.UP -> this.position.second == 1
            Direction.DOWN -> this.position.second == size.second
            Direction.LEFT -> this.position.first == 1
            Direction.RIGHT -> this.position.first == size.first
        }

    fun walkTo(direction: Direction): LabyrinthState = copy(
        pathTakenSoFar = this.pathTakenSoFar + direction,
        position = when (direction) {
            Direction.UP -> this.position.first to this.position.second - 1
            Direction.DOWN -> this.position.first to this.position.second + 1
            Direction.LEFT -> this.position.first - 1 to this.position.second
            Direction.RIGHT -> this.position.first + 1 to this.position.second
        },
    )

    fun isWinningState(): Boolean = position == size
}

/**
 * [Day 17: Two Steps Forward](https://adventofcode.com/2016/day/17).
 */
val day17 = complexSolution(17) {
    inputParser = singleLine

    part1Solver = solver({
        "Shortest path is $it."
    }) { passcode ->
        val queue = ArrayDeque<LabyrinthState>()
        queue.addLast(LabyrinthState(passcode = passcode))

        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            if (state.isWinningState()) return@solver convertPathToString(state.pathTakenSoFar)
            state.availableDirections().forEach { queue.addLast(state.walkTo(it)) }
        }

        error("Path not found")
    }

    part2Solver = solver({
        "Longset path taken require $it steps."
    }) { passcode ->
        val queue = ArrayDeque<LabyrinthState>()
        var maxPathLength = 0
        queue.addLast(LabyrinthState(passcode = passcode))

        while (queue.isNotEmpty()) {
            val state = queue.removeFirst()
            if (state.isWinningState()) {
                maxPathLength = max(maxPathLength, state.pathTakenSoFar.size)
            } else {
                state.availableDirections().forEach { queue.addLast(state.walkTo(it)) }
            }
        }

        return@solver maxPathLength
    }
}
