package isdemidoff

interface SolutionBuilder<T> {
    fun build(filename: String): Solution<T>
    fun buildAndSolve(filename: String) = build(filename).solve()
}

interface Solution<T> {
    fun solve(): T
}

open class SimpleSolutionBuilder<RESULT, T>(
    private val inputsDir: String,
    private val inputParser: (filename: String) -> T,
    private val solver: (parsedInput: T) -> RESULT,
): SolutionBuilder<RESULT> {
    override fun build(filename: String) = SimpleSolution(
        "$inputsDir/$filename",
        inputParser,
        solver
    )
}

class SimpleSolution<RESULT, T>(
    private val filename: String,
    private val inputParser: (filename: String) -> T,
    private val solver: (parsedInput: T) -> RESULT,
) : Solution<RESULT> {
    override fun solve() = solver(inputParser(filename))
}