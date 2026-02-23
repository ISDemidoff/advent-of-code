package isdemidoff.adventofcode.year2015.day2.entity

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

val wrappedBoxFromList: (List<Int>) -> WrappedBox = {
    require(it.size == 3) { "There must be exactly 3 dimensions specified, but got $it" }
    WrappedBox(it[0], it[1], it[2])
}