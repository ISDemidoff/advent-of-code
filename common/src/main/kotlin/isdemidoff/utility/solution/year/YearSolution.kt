package isdemidoff.utility.solution.year

class YearSolution(
    private val year: Int,
    private vararg val solutionBuildersWithArgs: SolutionData,
) {
    fun printSolutions() {
        println(
            """
            === AOC$year solutions ===
        
            """.trimIndent()
        )
        solutionBuildersWithArgs.forEach { (builder, args1, args2) ->
            builder.input { inputFile() }.printSolutions(args1, args2)
            println()
        }

        println(
            """
            === End of solutions ===
            """.trimIndent()
        )
    }
}