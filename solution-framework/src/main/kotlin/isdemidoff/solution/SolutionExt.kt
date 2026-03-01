package isdemidoff.solution

fun <INNER_DATA, R1, R2> complexSolution(day: Int, prepare: SolutionBuilder<INNER_DATA, R1, R2>.() -> Unit): SolutionBuilder<INNER_DATA, R1, R2> =
    SolutionBuilder<INNER_DATA, R1, R2>(day).apply { prepare() }

fun <INNER_DATA, R> solution(day: Int, prepare: SolutionBuilder<INNER_DATA, R, R>.() -> Unit): SolutionBuilder<INNER_DATA, R, R> =
    SolutionBuilder<INNER_DATA, R, R>(day).apply { prepare() }
