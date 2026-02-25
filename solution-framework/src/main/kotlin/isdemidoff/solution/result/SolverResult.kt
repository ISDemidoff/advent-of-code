package isdemidoff.solution.result

sealed interface SolverResult<R> {
    fun get(): R
    fun formatPretty(): String
}

