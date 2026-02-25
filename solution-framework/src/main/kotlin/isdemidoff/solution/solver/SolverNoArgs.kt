package isdemidoff.solution.solver

import isdemidoff.solution.result.SolverResult

open class SolverNoArgs<INNER_DATA, R>(
    private val fn: (INNER_DATA) -> SolverResult<R>,
) : Solver<INNER_DATA, R> {
    override fun solve(input: INNER_DATA, vararg args: Any) = fn(input)
}