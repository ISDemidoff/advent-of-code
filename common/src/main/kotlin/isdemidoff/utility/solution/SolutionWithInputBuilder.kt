package isdemidoff.utility.solution

import isdemidoff.utility.solution.datasupplier.DataSupplier
import isdemidoff.utility.solution.result.SolutionResult
import isdemidoff.utility.solution.solver.EmptySolver
import isdemidoff.utility.solution.solver.Solver

class SolutionWithInputBuilder<INNER_DATA, R1, R2>(
    private val inputSupplier: DataSupplier<INNER_DATA>,
    var part1Solver: Solver<INNER_DATA, R1>,
    var part2Solver: Solver<INNER_DATA, R2>,
) {
    fun solvePart1(vararg args: Any): SolutionResult<R1> = part1Solver.validateAndSolve(inputSupplier.getInputData(), *args)
    fun solvePart2(vararg args: Any): SolutionResult<R2> = part2Solver.validateAndSolve(inputSupplier.getInputData(), *args)

    fun getSolutions(part1Args: List<Any>, part2Args: List<Any>): List<String?> =
        listOf(part1Solver, part2Solver)
            .zip(listOf(part1Args, part2Args)) { solver, args ->
                solver.solveToPrettyString(args)
            }

    private fun Solver<INNER_DATA, *>.solveToPrettyString(args: List<Any>): String? =
        this.takeUnless { it is EmptySolver }
            ?.validateAndSolve(inputSupplier.getInputData(), *args.toTypedArray())
            ?.formatPretty()
}