package isdemidoff.utility.solution.solver

import isdemidoff.utility.solution.result.SolverResult
import kotlin.reflect.KClass

interface Solver<INNER_DATA, R> {
    fun solve(input: INNER_DATA, vararg args: Any): SolverResult<R>
    fun expectedArgs(): List<KClass<*>> = emptyList()

    fun validateAndSolve(input: INNER_DATA, vararg args: Any): SolverResult<R> {
        val expectedArgTypes = expectedArgs()
        require(expectedArgTypes.size == args.size) {
            "Expected args count ${expectedArgTypes.size}, got ${args.size}"
        }
        for (i in args.indices) {
            require(args[i]::class == expectedArgTypes[i]) {
                "Expected arg at position $i type ${expectedArgTypes[i]} but got ${args[i]} (type ${args[i]::class})"
            }
        }
        return solve(input, *args)
    }
}
