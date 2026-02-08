package isdemidoff.utility.solution.solver

import isdemidoff.utility.solution.result.SolutionResult
import isdemidoff.utility.solution.result.solutionResult

class EmptySolver<INNER_DATA, R> : SolverNoArgs<INNER_DATA, R>({ TODO("No solution provided yet") })

inline fun <INNER_DATA, R, reified A : Any> SolverUseScope<INNER_DATA>.solver(
    crossinline formatter: (result: R, A) -> String = { result, _ -> if (result is String) result else result.toString() },
    crossinline fn: (data: INNER_DATA, A) -> R,
) = object : Solver<INNER_DATA, R> {
    override fun expectedArgs() = listOf(A::class)
    override fun solve(input: INNER_DATA, vararg args: Any): SolutionResult<R> {
        val arg0 = args[0] as A
        return solutionResult({ res -> formatter(res, arg0) }) { fn(input, arg0) }
    }
}

inline fun <INNER_DATA, R, reified A1 : Any, reified A2 : Any> SolverUseScope<INNER_DATA>.solver(
    crossinline formatter: (result: R, A1, A2) -> String = { result, _, _ -> if (result is String) result else result.toString() },
    crossinline fn: (data: INNER_DATA, A1, A2) -> R,
) = object : Solver<INNER_DATA, R> {
    override fun expectedArgs() = listOf(A1::class, A2::class)
    override fun solve(input: INNER_DATA, vararg args: Any): SolutionResult<R> {
        val arg0 = args[0] as A1
        val arg1 = args[1] as A2
        return solutionResult({ res -> formatter(res, arg0, arg1) }) { fn(input, arg0, arg1) }
    }
}

inline fun <INNER_DATA, R, reified A1 : Any, reified A2 : Any, reified A3 : Any> SolverUseScope<INNER_DATA>.solver(
    crossinline formatter: (result: R, A1, A2, A3) -> String = { result, _, _, _ -> if (result is String) result else result.toString() },
    crossinline fn: (data: INNER_DATA, A1, A2, A3) -> R,
) = object : Solver<INNER_DATA, R> {
    override fun expectedArgs() = listOf(A1::class, A2::class, A3::class)
    override fun solve(input: INNER_DATA, vararg args: Any): SolutionResult<R> {
        val arg0 = args[0] as A1
        val arg1 = args[1] as A2
        val arg2 = args[2] as A3
        return solutionResult({ res -> formatter(res, arg0, arg1, arg2) }) { fn(input, arg0, arg1, arg2) }
    }
}