package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.SolutionBuilder

fun printYearSolution(
    config: YearSolutionConfig,
    vararg solutions: Any,
) = YearSolution(
    config,
    *solutions.mapIndexed { index, it ->
        extractSolution(it) { IllegalArgumentException("Expected SolutionData or SolutionBuilder at position $index, got $it (type ${it::class.qualifiedName})") }
    }.toTypedArray()
).getSolutionsFormatted().let { println(it) }

private fun extractSolution(builderOrData: Any, exceptionSupplier: () -> Exception): SolutionData {
    return when (builderOrData) {
        is SolutionBuilder<*, *, *> -> builderOrData.withNoArgs()
        is SolutionData -> builderOrData
        else -> throw exceptionSupplier()
    }
}

object DisableReason {
    const val LONG_TIME = "It takes a long time to complete"
}