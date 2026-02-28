package isdemidoff.adventofcode.year2015.day6.entity

data class Instruction(
    val operation: Operation,
    val xFrom: Int,
    val xTo: Int,
    val yFrom: Int,
    val yTo: Int,
) {
    enum class Operation(val text: String) {
        TURN_ON("turn on"),
        TURN_OFF("turn off"),
        TOGGLE("toggle"),
    }
}

private fun String.findOperation() =
    Instruction.Operation.entries.first { this.startsWith(it.text) }

val instructionParser: (String) -> Instruction = { input ->
    val op = input.findOperation()
    val limits = input.substring(op.text.length).trim()
    val points = limits.split(" through ")
        .also { check(it.size == 2) { "Exactly two point must be specified" } }
        .map { pointRaw ->
            pointRaw.split(",")
                .also { check(it.size == 2) { "Exactly two dimensions must be specified for point" } }
                .map { it.toInt() }
        }

    Instruction(
        operation = op,
        xFrom = points.minOf { it.first() },
        xTo = points.maxOf { it.first() },
        yFrom = points.minOf { it.last() },
        yTo = points.maxOf { it.last() },
    )
}
