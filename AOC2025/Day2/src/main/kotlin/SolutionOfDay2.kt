fun solveForFileName(fileName: String) = readSingleLine(fileName)
    .split(",")
    .map { it.split("-") }
    .map { it[0].toLong() to it[1].toLong() }
    .sumOf { getSumOfSillyPatterns(it) }

fun getSumOfSillyPatterns(interval: Pair<Long, Long>) =
    (interval.first..interval.second).sumOf { if (it.isSillyNumber()) it else 0 }

fun Long.isSillyNumber() = toString()
    .takeIf { it.length % 2 == 0 }
    ?.takeIf { it.take(it.length / 2) == it.takeLast(it.length / 2) } != null
