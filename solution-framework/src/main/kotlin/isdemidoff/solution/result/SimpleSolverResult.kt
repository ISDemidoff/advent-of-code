package isdemidoff.solution.result

class SimpleSolverResult<R>(
    private val formatter: (R) -> String,
    supplier: () -> R,
) : SolverResult<R> {
    private val cachedData by lazy { supplier() }
    override fun get(): R = cachedData
    override fun formatPretty(): String = formatter(cachedData)
}