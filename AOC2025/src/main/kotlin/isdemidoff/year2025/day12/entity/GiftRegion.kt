package isdemidoff.year2025.day12.entity

import isdemidoff.utility.parseUnescapedCsvInputLine

data class GiftRegion(
    val length: Int,
    val wight: Int,
    val counts: List<Int>,
) {
    val area = length * wight

    fun isFitting(boxes: List<GiftBox>, algoVersion: AlgoVersion = AlgoVersion.AREA): Boolean = when (algoVersion) {
        AlgoVersion.AREA -> boxes.zip(counts).sumOf { (box, count) -> box.size * count } <= area
        AlgoVersion.ACTUAL_FIT -> false
    }
}

fun String.toGiftRegion(): GiftRegion = split(":")
    .let {
        check(it.size == 2) { "Gift region must contain exactly 2 parts separated by colon" }
        it[0] to it[1]
    }
    .let { (sizeStr, countsStr) ->
        val size = sizeStr.split("x")
            .let {
                check(it.size == 2) { "Gift region size must be set in form of \"{length}x{width}\"" }
                it[0].toInt() to it[1].toInt()
            }
        val counts = countsStr.parseUnescapedCsvInputLine(delimiter = ' ') { it.toString().toInt() }
        GiftRegion(size.first, size.second, counts)
    }