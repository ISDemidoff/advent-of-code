package isdemidoff.year2025.day12.entity

data class GiftBox(
    val shape: List<List<Boolean>>, // 3x3 grid
) {
    val size = shape.sumOf { row -> row.sumOf { if (it) 1 else 0 } }
}

fun List<String>.toGiftBoxExtendedInput(): GiftBox {
    check(this[0].matches("""[0-9]:""".toRegex())) { "Gift box input must start with string satisfying \"[0-9]:\"" }
    check(this[4].isBlank()) { "Gift box input must end with blank line" }

    return this.drop(1).take(3).toGiftBox()
}

fun List<String>.toGiftBox(): GiftBox {
    check(this.size == 3) { "Gift box should be set with 3 rows" }
    return GiftBox(this.map { row -> row.map { it == '#' } })
}