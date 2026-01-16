package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.SolutionBuilder

fun printYearSolution(
    year: Int,
    vararg solutions: Any,
) = YearSolution(
    year,
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