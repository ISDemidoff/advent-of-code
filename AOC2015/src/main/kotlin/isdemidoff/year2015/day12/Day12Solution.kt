package isdemidoff.year2015.day12

import isdemidoff.SingleLineSolution
import isdemidoff.SingleLineSolutionBuilder
import kotlinx.serialization.json.*

fun JsonElement.calculateTotalSum(): Int = when (this) {
    is JsonPrimitive -> this.contentOrNull?.toIntOrNull() ?: 0
    is JsonArray -> this.sumOf { it.calculateTotalSum() }
    is JsonObject -> this.values.sumOf { it.calculateTotalSum() }
    else -> throw IllegalArgumentException("Unexpected type of element ${this.javaClass}")
}

class Day12Solution(private val input: String) : SingleLineSolution<Int>(
    input = input,
    solution = { Json.parseToJsonElement(input).calculateTotalSum() },
)

class Day12SolutionBuilder(day12Path: String) : SingleLineSolutionBuilder<Int>(
    inputsDir = day12Path,
    solutionSupplier = { Day12Solution(it) },
)