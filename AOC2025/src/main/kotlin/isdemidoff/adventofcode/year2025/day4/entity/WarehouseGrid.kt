package isdemidoff.adventofcode.year2025.day4.entity

import isdemidoff.utility.isInvalidPosition

class WarehouseGrid(val grid: List<String>) {
    val length = grid.size
    val width = grid[0].length

    fun isOccupied(x: Int, y: Int): Boolean {
        if (x.isInvalidPosition(length)) {
            return false
        }
        if (y.isInvalidPosition(width)) {
            return false
        }
        return grid[y][x] == OCCUPIES_POS
    }

    fun isCellAvailable(x: Int, y: Int): Boolean {
        var blockedPositions = 0
        (x-1 .. x+1).forEach { xPos ->
            (y-1 .. y+1).forEach { yPos ->
                if (x == xPos && y == yPos) return@forEach
                if (isOccupied(xPos, yPos)) blockedPositions++
            }
        }
        return blockedPositions < BLOCK_THRESHOLD
    }

    fun calculateAvailableCells() =
        (0..<length).sumOf { y ->
            (0..<width).count { x ->
                isOccupied(x, y) && isCellAvailable(x, y)
            }
        }

    companion object {
        const val OCCUPIES_POS = '@'
        const val BLOCK_THRESHOLD = 4
    }
}