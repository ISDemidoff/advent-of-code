package isdemidoff.solution.solver

import isdemidoff.solution.result.SpecialSolverResult

class SpecialSolver<INNER_DATA, R>(
    private val message: String,
) : Solver<INNER_DATA, R> {
    override fun solve(input: INNER_DATA, vararg args: Any) = SpecialSolverResult<R>(message)
    fun getSpecialMessage(): String = message
}
