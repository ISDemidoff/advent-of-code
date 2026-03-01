package isdemidoff.solution

import isdemidoff.solution.datasupplier.DataSupplier
import isdemidoff.solution.model.PartSolutionConfig
import isdemidoff.solution.solver.EmptySolver
import isdemidoff.solution.solver.Solver
import isdemidoff.solution.solver.SpecialSolver
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

fun <T> Solver<T, *>.solveToPrettyString(inputSupplier: DataSupplier<T>, config: PartSolutionConfig): String {
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

private fun createMessageForDisabledSolution(reason: String) = "Solution disabled with reason '$reason'."
private fun createMessageForFailedSolution(ex: Throwable) = "Solution threw an exception $ex."
private fun formatDuration(durationNanos: Long): String = durationNanos.nanoseconds
    .toComponents { minutes, seconds, nanoseconds ->
        minutes.minutes + seconds.seconds + nanoseconds.nanoseconds.inWholeMilliseconds.milliseconds
    }.toString()
