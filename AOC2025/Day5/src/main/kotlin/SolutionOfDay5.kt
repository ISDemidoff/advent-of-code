import isdemidoff.utility.toLongsList
import isdemidoff.utility.input.readTwoBlocks
import isdemidoff.utility.toLongRanges

fun solveForFileName(fileName: String) = readTwoBlocks(fileName)
    .let { (ranges, ids) -> ranges.toLongRanges() to ids.toLongsList() }
    .let { (ranges, ids) -> ids.count { id -> ranges.any { id in it } } }
