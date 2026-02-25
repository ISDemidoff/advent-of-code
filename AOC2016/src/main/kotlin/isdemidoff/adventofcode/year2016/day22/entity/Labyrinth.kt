package isdemidoff.adventofcode.year2016.day22.entity

import isdemidoff.utility.isInvalidPosition
import isdemidoff.utility.other.forEachIndexedInMatrix
import isdemidoff.utility.other.indexOfInMatrix
import isdemidoff.utility.other.joinToImage
import isdemidoff.utility.other.mapIndexedMatrix
import isdemidoff.utility.other.transpose

data class Labyrinth(
    val sizeX: Int,
    val sizeY: Int,
    @Suppress("ArrayInDataClass")
    val cells: Array<Array<Int>>,
) {
    fun findShortestPath(from: Position, to: Position): Int {
        // bfs
        val queue = ArrayDeque<Pair<Position, Int>>()
        queue.addLast(from to 0)
        val seenPositions = mutableSetOf<Position>()
        seenPositions.add(from)

        while (queue.isNotEmpty()) {
            val (pos, depth) = queue.removeFirst()
            if (pos == to) return depth
            getNextAvailablePositions(pos)
                .filter { seenPositions.add(it) }
                .forEach { queue.addLast(it to depth + 1) }
        }

        error("Path from $from to $to not found")
    }

    private fun getNextAvailablePositions(cur: Position): List<Position> =
        listOf(
            cur.copy(x = cur.x + 1),
            cur.copy(x = cur.x - 1),
            cur.copy(y = cur.y + 1),
            cur.copy(y = cur.y - 1),
        )
            .filterNot { it.x.isInvalidPosition(sizeX) || it.y.isInvalidPosition(sizeY) }
            .filter { cells[it.x][it.y] == 0 }

    fun getGoalPosition(): Position =
        cells.indexOfInMatrix { it == 2 }
            .takeUnless { it == -1 to -1 }
            ?.let { Position(it.first, it.second) } ?: error("No goal position found")

    fun isWinningPosition(): Boolean = getGoalPosition() == Position(0, 0)

    fun moveGoalPosition(): Labyrinth {
        // Assume we always move it left
        val newCells = Array(sizeX) { Array(sizeY) { 0 } }
        val goalPos = getGoalPosition()

        this.cells.forEachIndexedInMatrix { x, y, cell ->
            newCells[x][y] = cell
            if (Position(x, y) == goalPos) {
                newCells[x][y] = 0
                newCells[x - 1][y] = 2
            }
        }

        return this.copy(cells = newCells)
    }

    /**
     * Debug for data transformation.
     */
    @Suppress("unused")
    infix fun showWithPosition(currentPosition: Position) =
        cells.mapIndexedMatrix { x, y, cell ->
            when {
                Position(x, y) == currentPosition -> '_'
                cell == 0 -> '.'
                cell == 1 -> '#'
                cell == 2 -> 'G'
                else -> '?'
            }
        }
        .transpose()
        .joinToString(separator = "\n") { it.joinToString(separator = "") }

    /**
     * Debug for bfs algo.
     */
    @Suppress("unused")
    infix fun showFilledWith(seenPositions: Set<Position>) =
        cells.mapIndexedMatrix { x, y, cell ->
            when {
                Position(x, y) in seenPositions -> '!'
                cell == 0 -> '.'
                cell == 1 -> '#'
                cell == 2 -> 'G'
                else -> '?'
            }
        }
            .transpose()
            .joinToImage()
}

data class Position(val x: Int, val y: Int)

fun getLabyrinthAndStartingPosition(fileSystem: FileSystem): Pair<Labyrinth, Position> {
    val cells = Array(fileSystem.xSize) { Array(fileSystem.ySize) { 0 } }
    var currentPosition: Position? = null

    fileSystem.nodes.forEachIndexedInMatrix { x, y, node ->
        when {
            node.specialData -> cells[x][y] = 2
            node.used > 150 -> cells[x][y] = 1
            node.used == 0 -> currentPosition = Position(x, y)
            else -> cells[x][y] = 0
        }
    }

    check((0..<fileSystem.xSize).all { cells[it][0] == 0 }) { "Expected to see first row totally empty." }

    return Labyrinth(fileSystem.xSize, fileSystem.ySize, cells) to requireNotNull(currentPosition) { "Not found starting position" }
}

fun getFastestWayToMoveData(fileSystem: FileSystem): Int {
    var (labyrinth, currentPosition) = getLabyrinthAndStartingPosition(fileSystem)

    // +1 is for goal move left since we find path to the left of goal
    var result = labyrinth.findShortestPath(currentPosition, Position(fileSystem.xSize - 2, 0)) + 1
    labyrinth = labyrinth.moveGoalPosition()
    currentPosition = Position(fileSystem.xSize - 1, 0)

    while (!labyrinth.isWinningPosition()) {
        val posTo = Position(currentPosition.x - 2, 0)
        // +1 is for goal move left since we find path to the left of goal
        result += labyrinth.findShortestPath(currentPosition, posTo) + 1
        labyrinth = labyrinth.moveGoalPosition()
        currentPosition = posTo.copy(x = posTo.x + 1)
    }

    return result
}