package isdemidoff.utility.solution.result

sealed interface SolutionResult<R> {
    fun get(): R
    fun formatPretty(): String
}

