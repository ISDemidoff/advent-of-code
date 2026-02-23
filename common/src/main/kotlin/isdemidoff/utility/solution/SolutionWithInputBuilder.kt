package isdemidoff.utility.solution

import isdemidoff.utility.solution.datasupplier.DataSupplier
import isdemidoff.utility.solution.model.PartSolutionConfig
import isdemidoff.utility.solution.solver.Solver

class SolutionWithInputBuilder<INNER_DATA, R1, R2>(
    private val inputSupplier: DataSupplier<INNER_DATA>,
    var part1Solver: Solver<INNER_DATA, R1>,
    var part2Solver: Solver<INNER_DATA, R2>,
) {
    fun solvePart1(vararg args: Any): R1 = part1Solver.solveToResult(*args)
    fun solvePart2(vararg args: Any): R2 = part2Solver.solveToResult(*args)

    fun getSolutions(part1Config: PartSolutionConfig, part2Config: PartSolutionConfig): List<String> =
        listOf(part1Solver, part2Solver).zip(listOf(part1Config, part2Config)) { solver, config ->
            solver.solveToPrettyString(inputSupplier, config)
        }

    private fun <T> Solver<INNER_DATA, T>.solveToResult(vararg args: Any): T =
        inputSupplier.getInputData()
            .mapCatching { this.validateAndSolve(it, *args).get() }
            .getOrThrow()
}