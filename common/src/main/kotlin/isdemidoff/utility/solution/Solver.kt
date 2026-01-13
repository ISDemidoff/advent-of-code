package isdemidoff.utility.solution

open class Solver<INNER_DATA, R>(private val fn: (INNER_DATA) -> SolutionResult<R>) : (INNER_DATA) -> SolutionResult<R> by fn {
    fun solve(input: INNER_DATA) = fn(input)
}

class EmptySolver<INNER_DATA, R> : Solver<INNER_DATA, R>({ TODO("No solution provided yet") })

fun <INNER_DATA, R> solver(
    formatter: (R) -> String = { if (it is String) it else it.toString() },
    fn: (INNER_DATA) -> R,
) = Solver<INNER_DATA, R> { solutionResult(formatter) { fn(it) } }
