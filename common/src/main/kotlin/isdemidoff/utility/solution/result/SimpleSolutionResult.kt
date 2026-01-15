package isdemidoff.utility.solution.result

class SimpleSolutionResult<R>(
    private val formatter: (R) -> String,
    supplier: () -> R,
) : SolutionResult<R> {
    private val cachedData by lazy { supplier() }
    override fun get(): R = cachedData
    override fun formatPretty(): String = formatter(cachedData)
}