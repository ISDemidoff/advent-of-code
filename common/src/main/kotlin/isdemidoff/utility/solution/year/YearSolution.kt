package isdemidoff.utility.solution.year

class YearSolution(
    private val year: Int,
    private vararg val solutionBuildersWithArgs: SolutionData,
) {
    fun getSolutionsFormatted() = with(StringBuilder()) {
        appendLine("=== AOC$year solutions ===\n")

        solutionBuildersWithArgs.forEach { data ->
            data.builder.input { inputFile() }
                .getSolutions(data.buildPart1Context(), data.buildPart2Context())
                .let { solutions ->
                    appendLine("""Day ${data.builder.context.day} solutions:""")
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