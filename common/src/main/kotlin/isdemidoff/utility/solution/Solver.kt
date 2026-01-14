package isdemidoff.utility.solution

import kotlin.reflect.KClass

interface Solver<INNER_DATA, R> {
    fun solve(input: INNER_DATA, vararg args: Any): SolutionResult<R>
    fun expectedArgs(): List<KClass<*>> = emptyList()

    fun validateAndSolve(input: INNER_DATA, vararg args: Any): SolutionResult<R> {
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

open class SolverNoArgs<INNER_DATA, R>(
    private val fn: (INNER_DATA) -> SolutionResult<R>,
) : Solver<INNER_DATA, R> {
    override fun solve(input: INNER_DATA, vararg args: Any) = fn(input)
}

class EmptySolver<INNER_DATA, R> : SolverNoArgs<INNER_DATA, R>({ TODO("No solution provided yet") })

