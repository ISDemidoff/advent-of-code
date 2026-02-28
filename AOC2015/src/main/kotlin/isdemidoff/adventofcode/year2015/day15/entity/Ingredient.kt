package isdemidoff.adventofcode.year2015.day15.entity

import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields
import kotlin.math.max

data class Ingredient(
    val name: String,
    val capacity: Long,
    val durability: Long,
    val flavor: Long,
    val texture: Long,
    val calories: Long,
) {
    fun scoringProperties() = listOf(capacity, durability, flavor, texture)
}

internal fun Map<Ingredient, Int>.calculateScore() =
    this.map { (ingredient, count) -> ingredient.scoringProperties().map { it * count } }
        .let {
            it.drop(1).fold(it.first()) { acc, value ->
                acc.mapIndexed { index, lng -> lng + value[index] }
            }
        }
        .map { max(it, 0) } // negative values to 0
        .reduce(Long::times)

internal fun Map<Ingredient, Int>.calculateCalories() =
    this.map { (ingredient, count) -> ingredient.calories * count }.sum()

internal val readIngredient = regexMatch(
    """(.*): capacity (-?[0-9]+), durability (-?[0-9]+), flavor (-?[0-9]+), texture (-?[0-9]+), calories (-?[0-9]+)""".toRegex() yields {
        Ingredient(
            name = it.component1(),
            capacity = it.component2().toLong(),
            durability = it.component3().toLong(),
            flavor = it.component4().toLong(),
            texture = it.component5().toLong(),
            calories = it.component6().toLong(),
        )
    }
)