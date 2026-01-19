package isdemidoff.adventofcode.year2025.day6.entity

import isdemidoff.adventofcode.year2025.day6.entity.Problem.Operation

data class Problem(
    val elements: List<Long>,
    val operation: Operation,
) {
    enum class Operation(
            val symbol: Char,
            val func: (Long, Long) -> Long
    ) {
        ADD('+', Long::plus),
        MULTIPLY('*', Long::times),
        ;
    }

    fun solve() = elements.reduce(operation.func)
}

private fun Char.toOperation() = Operation.entries.first { operation -> operation.symbol == this }

infix fun Char.makeProblemOf(elements: List<Long>) = Problem(elements, this.toOperation())