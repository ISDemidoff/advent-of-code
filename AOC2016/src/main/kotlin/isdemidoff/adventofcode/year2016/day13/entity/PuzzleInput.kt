package isdemidoff.adventofcode.year2016.day13.entity

import isdemidoff.utility.parsing.keyValueBy
import isdemidoff.utility.parsing.toIntOrError

data class PuzzleInput(
    val designerFavNumber: Int,
    val desiredCoordinates: Pair<Int, Int>,
)

val favNumber: (List<String>) -> Int = {
    require(it.size == 1) { "There must be only one line in first block." }
    it.single().toIntOrError()
}

val coordinates: (List<String>) -> Pair<Int, Int> = {
    require(it.size == 1) { "There must be only one line in second block." }
    it.single().keyValueBy(",", { it.toIntOrError() }) { it.toIntOrError() }
}

val puzzleInput: (Pair<Int, Pair<Int, Int>>) -> PuzzleInput = { PuzzleInput(it.first, it.second) }
