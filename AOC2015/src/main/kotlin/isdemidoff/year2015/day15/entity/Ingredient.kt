package isdemidoff.year2015.day15.entity

import kotlin.math.max

data class Ingredient(
    val name: String,
    private val properties: List<Long>,
) : List<Long> by properties

internal fun String.parseIngredient() = Ingredient(
    name = substringBefore(":"),
    properties = substringAfter(":").trim()
        .split(",")
        .map { it.takeLastWhile { it == '-' || it in '0'..'9' }.toLong() },
)

internal fun Map<Ingredient, Int>.calculateScore() =
    this.map { (ingredient, count) -> ingredient.map { it * count } }
        .let {
            it.drop(1).fold(it.first()) { acc, value ->
                acc.mapIndexed { index, lng -> lng + value[index] }
            }
        }
        .map { max(it, 0) } // negative values to 0
        .reduce(Long::times)