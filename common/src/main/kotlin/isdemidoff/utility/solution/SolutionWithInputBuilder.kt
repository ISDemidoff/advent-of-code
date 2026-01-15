package isdemidoff.utility.solution

import isdemidoff.utility.solution.datasupplier.DataSupplier
import isdemidoff.utility.solution.result.SolutionResult
import isdemidoff.utility.solution.solver.EmptySolver
import isdemidoff.utility.solution.solver.Solver

class SolutionWithInputBuilder<INNER_DATA, R1, R2>(
    private val context: SolutionBuilderContext,
    private val inputSupplier: DataSupplier<INNER_DATA>,
    var part1Solver: Solver<INNER_DATA, R1>,
    var part2Solver: Solver<INNER_DATA, R2>,
) {
    private val isPart1Solved = part1Solver !is EmptySolver
    private val isPart2Solved = part2Solver !is EmptySolver

    fun solvePart1(vararg args: Any): SolutionResult<R1> = part1Solver.validateAndSolve(inputSupplier.getInputData(), *args)
    fun solvePart2(vararg args: Any): SolutionResult<R2> = part2Solver.validateAndSolve(inputSupplier.getInputData(), *args)

    fun printSolutions(part1Args: List<Any>, part2Args: List<Any>) {
        println("""Day ${context.day} solutions:""")

        if (isPart1Solved) {
            print("Part 1: ")
            solvePart1(*part1Args.toTypedArray()).printFormatted()
        } else {
            println("Part 1 is not solved yet.")
        }

        if (isPart2Solved) {
            print("Part 2: ")
            solvePart2(*part2Args.toTypedArray()).printFormatted()
        } else {
            println("Part 2 is not solved yet.")
        }
    }
}