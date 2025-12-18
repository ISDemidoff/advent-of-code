import isdemidoff.utility.input.readSingleLine
import isdemidoff.utility.toLongRanges

fun solveForFileName(fileName: String) = readSingleLine(fileName)
    .split(",")
    .toLongRanges()
    .sumOf { it.getSumOfSillyPatterns() }

private fun LongRange.getSumOfSillyPatterns() = filter { it.isSillyNumber() }.sum()

private fun Long.isSillyNumber() = toString()
    .takeIf { it.length % 2 == 0 }
    ?.takeIf { it.take(it.length / 2) == it.takeLast(it.length / 2) } != null
