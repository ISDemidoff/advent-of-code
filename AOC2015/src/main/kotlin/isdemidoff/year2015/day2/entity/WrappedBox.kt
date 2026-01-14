package isdemidoff.year2015.day2.entity

import isdemidoff.utility.parseUnescapedCsvInputLine

class WrappedBox(
    private val height: Int,
    private val width: Int,
    private val length: Int,
) {

    private fun calculateSides() = listOf(height * width, height * length, length * width)
    private val dimensions = listOf(height, width, length)

    fun calculateWrappingNeeded() = calculateSides().let { it.min() + 2 * it.reduce(Int::plus) }

    fun calculateRibbonNeeded() = dimensions.sorted().run {
        take(2).sum().times(2) + reduce(Int::times)
    }
}

fun wrappedBox(str: String): WrappedBox =
    str.parseUnescapedCsvInputLine('x') { it.toString().toInt() }
        .also { require(it.size == 3) { "There must be exactly 3 dimensions specified, but got $str" } }
        .let { (x, y, z) -> WrappedBox(x, y, z) }
