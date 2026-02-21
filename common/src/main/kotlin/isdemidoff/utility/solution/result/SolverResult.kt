package isdemidoff.utility.solution.result

sealed interface SolverResult<R> {
    fun get(): R
    fun formatPretty(): String
}

