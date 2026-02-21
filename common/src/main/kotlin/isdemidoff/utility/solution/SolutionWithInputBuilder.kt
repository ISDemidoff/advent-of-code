package isdemidoff.utility.solution

import isdemidoff.utility.solution.datasupplier.DataSupplier
import isdemidoff.utility.solution.model.PartSolutionConfig
import isdemidoff.utility.solution.solver.EmptySolver
import isdemidoff.utility.solution.solver.Solver
import isdemidoff.utility.solution.solver.SpecialSolver
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

class SolutionWithInputBuilder<INNER_DATA, R1, R2>(
    private val inputSupplier: DataSupplier<INNER_DATA>,
    var part1Solver: Solver<INNER_DATA, R1>,
    var part2Solver: Solver<INNER_DATA, R2>,
) {
    fun solvePart1(vararg args: Any): R1 = part1Solver.solveToResult(*args)
    fun solvePart2(vararg args: Any): R2 = part2Solver.solveToResult(*args)

    fun getSolutions(part1Config: PartSolutionConfig, part2Config: PartSolutionConfig): List<String> =
        listOf(part1Solver, part2Solver).zip(listOf(part1Config, part2Config)) { solver, config ->
            solver.solveToPrettyString(config)
        }

    private fun <T> Solver<INNER_DATA, T>.solveToResult(vararg args: Any): T =
        inputSupplier.getInputData()
            .mapCatching { this.validateAndSolve(it, *args).get() }
            .getOrThrow()

    private fun Solver<INNER_DATA, *>.solveToPrettyString(config: PartSolutionConfig): String {
        if (config.disableReason != null) return createMessageForDisabledSolution(config.disableReason)

        if (this is EmptySolver) return "Solution is not provided yet."
        if (this is SpecialSolver) return this.getSpecialMessage()

        val startTime = if (config.timerEnabled) System.nanoTime() else null

        val resultString = StringBuilder()

        val formattedResult = inputSupplier.getInputData()
            .mapCatching { this.validateAndSolve(it, *config.args.toTypedArray()).formatPretty() }
            .getOrElse { createMessageForFailedSolution(it) }

        if (startTime != null) resultString.append("[Time: ${formatDuration(System.nanoTime() - startTime)}] ")

        resultString.append(formattedResult)

        return resultString.toString()
    }

    // Helper functions

    private fun createMessageForDisabledSolution(reason: String) = "Solution disabled with reason '$reason'."
    private fun createMessageForFailedSolution(ex: Throwable) = "Solution threw an exception $ex."
    private fun formatDuration(durationNanos: Long): String = durationNanos.nanoseconds
        .toComponents { minutes, seconds, nanoseconds ->
            minutes.minutes + seconds.seconds + nanoseconds.nanoseconds.inWholeMilliseconds.milliseconds
        }.toString()
}