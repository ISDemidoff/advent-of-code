fun solveForFileName(fileName: String) = readTwoBlocks(fileName)
    .let { (ranges, ids) -> ranges.map { it.toLongRange() } to ids.map { it.toLong() } }
    .let { (ranges, ids) -> ids.count { id -> ranges.any { id in it } } }

fun String.toLongRange() = this.split("-").let { it.first().toLong()..it.last().toLong() }