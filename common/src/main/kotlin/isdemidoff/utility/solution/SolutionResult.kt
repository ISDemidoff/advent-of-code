package isdemidoff.utility.solution

sealed interface SolutionResult<R> {
    fun get(): R
    fun formatPretty(): String
    fun printFormatted() = println(formatPretty())
}

class SimpleSolutionResult<R>(
    private val formatter: (R) -> String,
    supplier: () -> R,
) : SolutionResult<R> {
    private val cachedData by lazy { supplier() }
    override fun get(): R = cachedData
    override fun formatPretty(): String = formatter(cachedData)
}

fun <R> solutionResult(
    formatter: (R) -> String = { if (it is String) it else it.toString() },
    dataSupplier: () -> R,
) = SimpleSolutionResult(formatter, dataSupplier)
