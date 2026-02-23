package isdemidoff.adventofcode.year2016.day13.entity

import isdemidoff.utility.parsing.keyValueBy

data class PuzzleInput(
    val designerFavNumber: Int,
    val desiredCoordinates: Pair<Int, Int>,
)

// TODO rebuild as Function2
val puzzleInputParser = { data: Pair<List<String>, List<String>> ->
    require(data.first.size == 1) { "There must be only one line in first block." }
    require(data.second.size == 1) { "There must be only one line in second block." }
    PuzzleInput(
        designerFavNumber = data.first.first().toInt(),
        desiredCoordinates = data.second.first().keyValueBy(",", { it.toInt() }) { it.toInt() },
    )
}
