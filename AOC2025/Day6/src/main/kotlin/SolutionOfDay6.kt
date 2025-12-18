import Problem.Companion.makeOperation
import isdemidoff.utility.toLongsListList
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseWhitespaceDelimitedInputList
import isdemidoff.utility.takeFirstChars

@ConsistentCopyVisibility
data class Problem private constructor(
    val elements: List<Long>,
    val operation: Operation,
) {
    enum class Operation(
        private val symbol: Char,
        val func: (Long, Long) -> Long
    ) {
        ADD('+', Long::plus),
        MULTIPLY('*', Long::times),
        ;

        companion object {
            fun fromChar(char: Char) = entries.first { operation -> operation.symbol == char }
        }
    }

    fun calculate() = elements.reduce(operation.func)

    companion object {
        fun Char.makeOperation(elements: List<Long>) = Problem(elements, Operation.fromChar(this))
    }
}

fun solveForFileName(fileName: String) = readLines(fileName)
    .parseWhitespaceDelimitedInputList()
    .let { it.last().takeFirstChars() to it.dropLast(1).toLongsListList() }
    .let { (operations, elements) -> transposeProblems(operations, elements) }
    .sumOf { it.calculate() }

private fun transposeProblems(operations: List<Char>, elements: List<List<Long>>) =
    operations.mapIndexed { index, op -> op.makeOperation(elements.map { it[index] }) }
