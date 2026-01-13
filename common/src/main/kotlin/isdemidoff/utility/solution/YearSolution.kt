package isdemidoff.utility.solution

class YearSolution(
    private val year: Int,
    private vararg val solutionBuilders: SolutionBuilder<*, *, *>,
) {
    fun printSolutions() {
        println(
            """
            === AOC$year solutions ===
        
            """.trimIndent()
        )
        solutionBuilders.forEach {
            it.parseInput(file()).printSolutions()
            println()
        }

        println(
            """
            === End of solutions ===
            """.trimIndent()
        )
    }
}