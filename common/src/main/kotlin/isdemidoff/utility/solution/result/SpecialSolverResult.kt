package isdemidoff.utility.solution.result

data class SpecialSolverResult<R>(
    val message: String,
) : SolverResult<R> {
    override fun get(): R = TODO("There are no result")
    override fun formatPretty(): String = message
}