import isdemidoff.utility.input.readLines

fun solveForFileName(fileName: String): Int {
    var position = 50
    var result = 0
    readLines(fileName)
        .map { it[0] to it.drop(1).toInt() }
        .forEach { (dir, count) ->
            when (dir) {
                'L' -> position -= count
                'R' -> position += count
                else -> throw IllegalArgumentException("Invalid direction $dir")
            }
            if (position % 100 == 0) result++
        }

    return result
}