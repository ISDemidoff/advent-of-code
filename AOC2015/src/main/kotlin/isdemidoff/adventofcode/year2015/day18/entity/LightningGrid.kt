package isdemidoff.adventofcode.year2015.day18.entity

import isdemidoff.utility.isInvalidPosition

class LightningGrid(
    initialState: List<String>,
    private val cornersOverride: Boolean = false,
) {
    var grid: List<List<Boolean>> = initialState.mapIndexed { y, row ->
        row.mapIndexed { x, char ->
            char == '#' || (cornersOverride && (x == 0 || x == row.length - 1) && (y == 0 || y == initialState.size - 1))
        }
    }
    val height = grid.size
    val width = grid.first().size

    fun iterateOnce() = iterate(1)

    fun iterate(numberOfIterations: Int) {
        requireNotNull(numberOfIterations > 0) { "Number of iterations must be natural number." }

        repeat(numberOfIterations) { iterate() }
    }

    private fun iterate() {
        grid = createNextStepGrid()
    }

    private fun createNextStepGrid(): List<List<Boolean>> =
        (0..<height).map { y ->
            (0..<width).map { x ->
                val lights = countOfTurnedOnLights(x, y)

                if (cornersOverride && isCornerPosition(x, y)) return@map true

                if (grid[y][x]) {
                    return@map lights in (2..3)
                } else {
                    return@map lights == 3
                }
            }
        }

    private fun countOfTurnedOnLights(x: Int, y: Int): Int {
        var result = 0
        (y - 1 .. y + 1).forEach { yPos ->
            (x - 1 .. x + 1).forEach { xPos ->
                if (xPos == x && yPos == y) return@forEach
                if (isTurnedOn(xPos, yPos)) result++
            }
        }
        return result
    }

    private fun isTurnedOn(x: Int, y: Int): Boolean {
        if (x.isInvalidPosition(width) || y.isInvalidPosition(height)) return false

        return grid[y][x]
    }

    private fun isCornerPosition(x: Int, y: Int): Boolean {
        return (x == 0 || x == width - 1) && (y == 0 || y == height - 1)
    }

    fun countOfTurnedOnLights(): Int = grid.sumOf { it.count { it } }
}