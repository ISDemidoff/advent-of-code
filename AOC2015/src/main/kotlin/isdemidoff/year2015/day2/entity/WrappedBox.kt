package isdemidoff.year2015.day2.entity

class WrappedBox(
    private val height: Int,
    private val width: Int,
    private val length: Int,
) {
    private fun calculateSides() = listOf(height * width, height * length, length * width)

    fun calculateWrappingNeeded() = calculateSides().let { it.min() + 2 * it.reduce(Int::plus) }
}

fun String.toWrappedBox() = this.split('x')
    .also { check(it.size == 3) { "There must be exactly 3 dimensions specified, but git $this" } }
    .let { WrappedBox(it[0].toInt(), it[1].toInt(), it[2].toInt()) }