package isdemidoff.adventofcode.year2016.day13.entity

import isdemidoff.utility.keyValueBy

data class PuzzleInput(
    val designerFavNumber: Int,
    val desiredCoordinates: Pair<Int, Int>,
)

fun parsePuzzleInput(data: Pair<List<String>, List<String>>): PuzzleInput {
    require(data.first.size == 1) { "There must be only one line in first block." }
    require(data.second.size == 1) { "There must be only one line in second block." }
    return PuzzleInput(
        designerFavNumber = data.first.first().toInt(),
        desiredCoordinates = data.second.first().keyValueBy(",", { it.toInt() }) { it.toInt() },
    )
}
