package isdemidoff.year2015.day12

import isdemidoff.SingleLineSolution
import isdemidoff.SingleLineSolutionBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

fun JsonElement.calculateTotalSum(): Int = when (this) {
    is JsonPrimitive -> this.contentOrNull?.toIntOrNull() ?: 0
    is JsonArray -> this.sumOf { it.calculateTotalSum() }
    is JsonObject -> this.values.sumOf { it.calculateTotalSum() }
    else -> throw IllegalArgumentException("Unexpected type of element ${this.javaClass}")
}

fun JsonElement.calculateTotalSumIgnoring(color: String): Int = when (this) {
    is JsonPrimitive -> this.contentOrNull?.toIntOrNull() ?: 0

    is JsonArray -> this.sumOf { it.calculateTotalSumIgnoring(color) }

    is JsonObject -> this.values
        .takeUnless { values -> values.any { it is JsonPrimitive && it.contentOrNull == color } }
        ?.sumOf { it.calculateTotalSumIgnoring(color) }
        ?: 0

    else -> throw IllegalArgumentException("Unexpected type of element ${this.javaClass}")
}

enum class CountingRules(val calculator: (JsonElement) -> Int) {
    COUNT_ALL({ it.calculateTotalSum() }),
    EXCEPT_RED({ it.calculateTotalSumIgnoring("red") }),
}

class Day12Solution(private val input: String, private val countingRules: CountingRules) : SingleLineSolution<Int>(
    input = input,
    solution = { countingRules.calculator(Json.parseToJsonElement(input)) },
)

/**
 * [Day 12: JSAbacusFramework.io](https://adventofcode.com/2015/day/12).
 */
class Day12SolutionBuilder(
    private val day12Path: String,
    private val countingRules: CountingRules = CountingRules.COUNT_ALL,
) : SingleLineSolutionBuilder<Int>(
    inputsDir = day12Path,
    solutionSupplier = { Day12Solution(it, countingRules) },
) {
    fun withCountingRules(countingRules: CountingRules) = Day12SolutionBuilder(day12Path, countingRules)
}