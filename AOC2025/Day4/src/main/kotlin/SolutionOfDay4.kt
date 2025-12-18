import isdemidoff.utility.input.readLines
import isdemidoff.utility.isInvalidPosition

val EMPTY_POS = '.'
val OCCUPIES_POS = '@'
val BLOCK_THRESHOLD = 4

fun solveForFileName(fileName: String) = readLines(fileName).let { grid ->
    var result = 0
    grid.forEachIndexed { yPos, row ->
        row.forEachIndexed { xPos, symbol ->
            when (symbol) {
                EMPTY_POS -> return@forEachIndexed
                OCCUPIES_POS -> if (checkForAvailability(grid, yPos, xPos)) result++
                else -> throw IllegalArgumentException("Invalid symbol occurred $symbol")
            }
        }
    }
    return@let result
}

private fun checkForAvailability(grid: List<String>, yPos: Int, xPos: Int): Boolean {
    var blockedPositions = 0
    (yPos-1..yPos+1).forEach { y ->
        if (y.isInvalidPosition(grid.size)) return@forEach
        (xPos-1..xPos+1).forEach { x ->
            if (x.isInvalidPosition(grid[y].length)) return@forEach
            if (y == yPos && x == xPos) return@forEach

            if (grid[y][x] == OCCUPIES_POS) blockedPositions++
        }
    }
    return blockedPositions < BLOCK_THRESHOLD
}