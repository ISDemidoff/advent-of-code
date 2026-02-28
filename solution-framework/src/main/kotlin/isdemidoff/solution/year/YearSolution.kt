package isdemidoff.solution.year

class YearSolution(
    private val config: YearSolutionConfig,
    private vararg val solutionBuildersWithArgs: SolutionData,
) {
    fun getSolutionsFormatted() = with(StringBuilder()) {
        appendLine("=== AOC${config.year} solutions ===\n")

        solutionBuildersWithArgs.forEach { data ->
            data.builder
                .input { inputFile() }
                .getSolutions(
                    config.adjust(data.part1Config),
                    config.adjust(data.part2Config),
                )
                .let { solutions ->
                    appendLine("""Day ${data.builder.context.day} solutions:""")
                    solutions.forEachIndexed { index, solution ->
                        appendLine(solution.let { "Part ${index + 1}: $solution" })
                    }
                    appendLine()
                }
        }

        appendLine("=== End of solutions ===")
    }.toString()
}
