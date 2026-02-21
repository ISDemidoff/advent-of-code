package isdemidoff.utility.solution.solver

import isdemidoff.utility.solution.result.solverResult

interface SolverUseScope<INNER_DATA> {
    fun <R> emptySolver() = EmptySolver<INNER_DATA, R>()

    fun <R> solver(
        formatter: (result: R) -> String = { if (it is String) it else it.toString() },
        fn: (data: INNER_DATA) -> R,
    ) = SolverNoArgs<INNER_DATA, R> { solverResult(formatter) { fn(it) } }

    fun <R> specialSolver(
        message: String = "Happy new year!",
    ) = SpecialSolver<INNER_DATA, R>(message)
}
