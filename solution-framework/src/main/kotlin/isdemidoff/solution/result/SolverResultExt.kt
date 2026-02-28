package isdemidoff.solution.result

fun <R> solverResult(
    formatter: (R) -> String = { if (it is String) it else it.toString() },
    dataSupplier: () -> R,
) = SimpleSolverResult(formatter, dataSupplier)
