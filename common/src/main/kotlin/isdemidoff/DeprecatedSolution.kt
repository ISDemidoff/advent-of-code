package isdemidoff

import isdemidoff.utility.input.readSingleLine

interface DeprecatedSolutionBuilder<RESULT> {
    fun build(filename: String): DeprecatedSolution<RESULT>
    fun buildAndSolve(filename: String) = build(filename).solve()
    fun revealResult(filename: String)
}

interface DeprecatedSolution<RESULT> {
    fun solve(): RESULT
}

open class SingleLineDeprecatedSolution<RESULT>(
    input: String,
    solution: (String) -> RESULT,
) : SimpleDeprecatedSolution<RESULT, String>(
    input = input,
    solver = solution,
)

abstract class SingleLineDeprecatedSolutionBuilder<RESULT>(
    inputsDir: String,
    solver: ((String) -> RESULT)? = null,
    deprecatedSolutionSupplier: ((String) -> DeprecatedSolution<RESULT>)? = null,
) : SimpleDeprecatedSolutionBuilder<RESULT, String>(
    inputsDir = inputsDir,
    inputParser = { readSingleLine(it) },
    solver = solver,
    deprecatedSolutionSupplier = deprecatedSolutionSupplier,
)

abstract class SimpleDeprecatedSolutionBuilder<RESULT, PARSED_INPUT>(
    private val inputsDir: String,
    private val inputParser: (filename: String) -> PARSED_INPUT,
    private val solver: ((parsedInput: PARSED_INPUT) -> RESULT)? = null,
    private val deprecatedSolutionSupplier: ((parsedInput: PARSED_INPUT) -> DeprecatedSolution<RESULT>)? = null,
) : DeprecatedSolutionBuilder<RESULT> {
    init {
        require((solver == null) xor (deprecatedSolutionSupplier == null)) { "Only one type of supplier must be provided" }
    }

    override fun build(filename: String): DeprecatedSolution<RESULT> =
        inputParser(filename inDir inputsDir).let {
            when {
                solver != null -> SimpleDeprecatedSolution(it, solver)
                deprecatedSolutionSupplier != null -> deprecatedSolutionSupplier(it)
                else -> error("No solution found (never happens)")
            }
        }

    open fun formatResult(result: RESULT): String = result.toString()

    override fun revealResult(filename: String) {
        println("${filename inDir inputsDir} located result: ${formatResult(buildAndSolve(filename))}")
    }
}

open class SimpleDeprecatedSolution<RESULT, PARSED_INPUT>(
    private val input: PARSED_INPUT,
    private val solver: (parsedInput: PARSED_INPUT) -> RESULT,
) : DeprecatedSolution<RESULT> {
    override fun solve() = solver(input)
}

private infix fun String.inDir(directory: String) = "$directory/$this"