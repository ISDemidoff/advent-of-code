import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseUnescapedCsvInputLine

data class GiftBox(
    val shape: List<List<Boolean>>, // 3x3 grid
) {
    val size = shape.sumOf { row -> row.sumOf { if (it) 1 else 0 } }
}

enum class AlgoVersion {
    AREA,
    ACTUAL_FIT,
}

data class GiftRegion(
    val length: Int,
    val wight: Int,
    val counts: List<Int>,
) {
    val area = length * wight

    fun isFitting(boxes: List<GiftBox>, algoVersion: AlgoVersion = AlgoVersion.AREA): Boolean = when (algoVersion) {
        AlgoVersion.AREA -> boxes.zip(counts).sumOf { (box, count) -> box.size * count } <= area

        AlgoVersion.ACTUAL_FIT -> false

        else -> throw IllegalArgumentException("No such algo: $algoVersion")
    }
}


fun solveForFileName(fileName: String) = readLines(fileName)
    .let { parseInput(it) }
    .let { (boxes, regions) -> regions.count { it.isFitting(boxes) } }

fun parseInput(input: List<String>): Pair<List<GiftBox>, List<GiftRegion>> {
    val boxes = input.take(30)
        .windowed(5, 5) { rawGiftBox ->
            check(rawGiftBox[0].matches("""[0-9]:""".toRegex()))
            check(rawGiftBox[4].isBlank())

            rawGiftBox.drop(1).take(3)
                .map { row -> row.map { it == '#' } }
                .let { GiftBox(it) }
        }

    val regions = input.drop(30)
        .map { line ->
            line.split(":")
                .let {
                    check(it.size == 2)
                    it[0] to it[1]
                }
                .let { (sizeStr, countsStr) ->
                    val size = sizeStr.split("x")
                        .let {
                            check(it.size == 2)
                            it[0].toInt() to it[1].toInt()
                        }
                    val counts = countsStr.parseUnescapedCsvInputLine(delimiter = ' ') { it.toString().toInt() }
                    GiftRegion(size.first, size.second, counts)
                }
        }

    return boxes to regions
}