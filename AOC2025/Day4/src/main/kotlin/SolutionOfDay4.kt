fun solveForFileName(fileName: String) = readLines(fileName).let { grid ->
    var result = 0
    grid.forEachIndexed { yPos, row ->
        row.forEachIndexed { xPos, symbol ->
            when (symbol) {
                '.' -> return@forEachIndexed
                '@' -> if (checkForAvailability(grid, yPos, xPos)) result++
            }
        }
    }
    return@let result
}

val BLOCK_THRESHOLD = 4

fun checkForAvailability(grid: List<String>, yPos: Int, xPos: Int): Boolean {
    var blockedPositions = 0
    (yPos-1..yPos+1).forEach { y ->
        if (y.isInvalidPosition(grid.size)) return@forEach
        (xPos-1..xPos+1).forEach { x ->
            if (x.isInvalidPosition(grid[y].length)) return@forEach
            if (y == yPos && x == xPos) return@forEach

            if (grid[y][x] == '@') blockedPositions++
        }
    }
    return blockedPositions < BLOCK_THRESHOLD
}

fun Int.isInvalidPosition(limit: Int) = this !in 0..<limit