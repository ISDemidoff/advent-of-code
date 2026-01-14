package isdemidoff.utility.solution

import kotlin.reflect.KClass

interface Solver<INNER_DATA, R> {
    fun solve(input: INNER_DATA, vararg args: Any): SolutionResult<R>
    fun expectedArgs(): List<KClass<*>> = emptyList()
}

open class SolverNoArgs<INNER_DATA, R>(
    private val fn: (INNER_DATA) -> SolutionResult<R>,
) : Solver<INNER_DATA, R> {
    override fun solve(input: INNER_DATA, vararg args: Any) = fn(input)
}

class EmptySolver<INNER_DATA, R> : SolverNoArgs<INNER_DATA, R>({ TODO("No solution provided yet") })
