package isdemidoff

import isdemidoff.utility.input.readSingleLine

interface SolutionBuilder<RESULT> {
    fun build(filename: String): Solution<RESULT>
    fun buildAndSolve(filename: String) = build(filename).solve()
}

interface Solution<RESULT> {
    fun solve(): RESULT
}

open class SingleLineSolution<RESULT>(
    input: String,
    solution: (String) -> RESULT,
) : RealSimpleSolution<RESULT, String>(
    input = input,
    solver = solution,
)

open class SingleLineSolutionBuilder<RESULT>(
    inputsDir: String,
    solver: ((String) -> RESULT)? = null,
    solutionSupplier: ((String) -> Solution<RESULT>)? = null,
) : RealSimpleSolutionBuilder<RESULT, String>(
    inputsDir = inputsDir,
    inputParser = { readSingleLine(it) },
    solver = solver,
    solutionSupplier = solutionSupplier,
)

open class RealSimpleSolutionBuilder<RESULT, PARSED_INPUT>(
    private val inputsDir: String,
    private val inputParser: (filename: String) -> PARSED_INPUT,
    private val solver: ((parsedInput: PARSED_INPUT) -> RESULT)? = null,
    private val solutionSupplier: ((parsedInput: PARSED_INPUT) -> Solution<RESULT>)? = null,
) : SolutionBuilder<RESULT> {
    init {
        require((solver == null) xor (solutionSupplier == null)) { "Only one type of supplier must be provided" }
    }

    override fun build(filename: String): Solution<RESULT> =
        inputParser(filename inDir inputsDir).let {
            when {
                solver != null -> RealSimpleSolution(it, solver)
                solutionSupplier != null -> solutionSupplier(it)
                else -> error("No solution found (never happens)")
            }
        }
}

open class RealSimpleSolution<RESULT, PARSED_INPUT>(
    private val input: PARSED_INPUT,
    private val solver: (parsedInput: PARSED_INPUT) -> RESULT,
) : Solution<RESULT> {
    override fun solve() = solver(input)
}

private infix fun String.inDir(directory: String) = "$directory/$this"