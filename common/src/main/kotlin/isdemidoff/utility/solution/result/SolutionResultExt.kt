package isdemidoff.utility.solution.result

fun <R> solutionResult(
    formatter: (R) -> String = { if (it is String) it else it.toString() },
    dataSupplier: () -> R,
) = SimpleSolutionResult(formatter, dataSupplier)