package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.model.PartSolutionConfig

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
                    data.part1Config.adjust(config),
                    data.part2Config.adjust(config),
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

    private fun PartSolutionConfig.adjust(yearConfig: YearSolutionConfig) =
        config.forceTimerEnabled?.let { copy(timerEnabled = it) } ?: this
}