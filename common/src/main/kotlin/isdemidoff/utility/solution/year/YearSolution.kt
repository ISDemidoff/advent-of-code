package isdemidoff.utility.solution.year

class YearSolution(
    private val year: Int,
    private vararg val solutionBuildersWithArgs: SolutionData,
) {
    fun getSolutionsFormatted() = with(StringBuilder()) {
        appendLine("=== AOC$year solutions ===\n")

        solutionBuildersWithArgs.forEach { (builder, args1, args2) ->
            builder.input { inputFile() }
                .getSolutions(args1, args2)
                .let { solutions ->
                    appendLine("""Day ${builder.context.day} solutions:""")
                    solutions.forEachIndexed { index, solution ->
                        appendLine(
                            solution?.let { "Part ${index + 1}: $solution" }
                                ?: "Part ${index + 1} is not solved yet."
                        )
                    }
                    appendLine()
                }
        }

        appendLine("=== End of solutions ===")
    }.toString()
}