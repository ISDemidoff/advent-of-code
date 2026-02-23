package isdemidoff.adventofcode.year2015.day12

import isdemidoff.utility.solution.inputparser.functions.map
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

@Suppress("REDUNDANT_ELSE_IN_WHEN")
fun JsonElement.calculateTotalSum(): Int = when (this) {
    is JsonPrimitive -> this.contentOrNull?.toIntOrNull() ?: 0
    is JsonArray -> this.sumOf { it.calculateTotalSum() }
    is JsonObject -> this.values.sumOf { it.calculateTotalSum() }
    else -> throw IllegalArgumentException("Unexpected type of element ${this.javaClass}")
}

@Suppress("REDUNDANT_ELSE_IN_WHEN")
fun JsonElement.calculateTotalSumIgnoring(color: String): Int = when (this) {
    is JsonPrimitive -> this.contentOrNull?.toIntOrNull() ?: 0

    is JsonArray -> this.sumOf { it.calculateTotalSumIgnoring(color) }

    is JsonObject -> this.values
        .takeUnless { values -> values.any { it is JsonPrimitive && it.contentOrNull == color } }
        ?.sumOf { it.calculateTotalSumIgnoring(color) }
        ?: 0

    else -> throw IllegalArgumentException("Unexpected type of element ${this.javaClass}")
}

/**
 * [Day 12: JSAbacusFramework.io](https://adventofcode.com/2015/day/12).
 */
val day12 = solution(12) {
    inputParser = StringsInputParsers.singleLine.map(Json::parseToJsonElement)

    part1Solver = solver({ "Total sum of all numbers is $it" }) { it.calculateTotalSum() }
    part2Solver = solver({ "Total sum of all numbers except objects with 'red' is $it" }) { it.calculateTotalSumIgnoring("red") }
}