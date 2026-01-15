package isdemidoff.utility.solution.solver

import isdemidoff.utility.solution.result.SolutionResult

open class SolverNoArgs<INNER_DATA, R>(
    private val fn: (INNER_DATA) -> SolutionResult<R>,
) : Solver<INNER_DATA, R> {
    override fun solve(input: INNER_DATA, vararg args: Any) = fn(input)
}