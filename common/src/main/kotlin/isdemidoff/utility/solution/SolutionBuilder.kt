package isdemidoff.utility.solution

class SolutionBuilder<INNER_DATA, R1, R2>(
    private val day: Int,
) {
    var inputParser: InputParser<INNER_DATA>? = null
    var part1Solver: Solver<INNER_DATA, R1> = emptySolver()
    var part2Solver: Solver<INNER_DATA, R2> = emptySolver()

    fun rawInput(supplier: DataSupplier<INNER_DATA>) = SolutionWithInputBuilder(day, supplier, part1Solver, part2Solver)

    fun parseInput(supplier: DataSupplier<BlocksContent>) = when (supplier) {
        is FileDataSupplier -> SolutionWithInputBuilder(
            day,
            requireNotNull(inputParser) { "Input parser is not specified!" }.let {
                lazyData { it.parse(supplier.apply { dayIndex = day }.getInputData()) }
            },
            part1Solver,
            part2Solver,
        )
        is RawDataSupplier -> SolutionWithInputBuilder(
            day,
            requireNotNull(inputParser) { "Input parser is not specified!" }.let {
                lazyData { it.parse(supplier.getInputData()) }
            },
            part1Solver,
            part2Solver,
        )
    }

    // Parser dsl

    fun <R> inputParser(transform: (blocks: List<List<String>>) -> R) =
        InputParser(transform)

    fun <R> singleBlockParser(transform: (lines: List<String>) -> R): InputParser<R> =
        inputParser { transform(it.single()) }

    fun <R> uniformLinesParser(transform: (line: String) -> R): InputParser<List<R>> =
        singleBlockParser { it.map { row -> transform(row) } }

    fun <R> singleLineParser(transform: (line: String) -> R): InputParser<R> =
        singleBlockParser { transform(it.single()) }

    fun <R> twoBlocksParser(transform: (Pair<List<String>, List<String>>) -> R): InputParser<R> =
        inputParser {
            require(it.size == 2) { "Expected 2 blocks but got ${it.size}" }
            return@inputParser transform(it.first() to it.last())
        }

    // Solver dsl

    fun <R> emptySolver() = EmptySolver<INNER_DATA, R>()

    fun <R> solver(
        formatter: (result: R) -> String = { if (it is String) it else it.toString() },
        fn: (data: INNER_DATA) -> R,
    ) = SolverNoArgs<INNER_DATA, R> { solutionResult(formatter) { fn(it) } }

    inline fun <R, reified A : Any> solver(
        crossinline formatter: (result: R, A) -> String = { result, _ -> if (result is String) result else result.toString() },
        crossinline fn: (data: INNER_DATA, A) -> R,
    ) = object : Solver<INNER_DATA, R> {
        override fun expectedArgs() = listOf(A::class)
        override fun solve(input: INNER_DATA, vararg args: Any): SolutionResult<R> {
            require(args.size == 1) { "Args size must be exactly 1, got ${args.size}" }
            require(args[0] is A) { "First arg must be of type ${A::class}, got ${args[0]}" }
            val arg0 = args[0] as A
            return solutionResult({ res -> formatter(res, arg0) }) { fn(input, arg0) }
        }
    }

    inline fun <R, reified A1: Any, reified A2: Any> solver(
        crossinline formatter: (result: R, A1, A2) -> String = { result, _, _ -> if (result is String) result else result.toString() },
        crossinline fn: (data: INNER_DATA, A1, A2) -> R,
    ) = object : Solver<INNER_DATA, R> {
        override fun expectedArgs() = listOf(A1::class, A2::class)
        override fun solve(input: INNER_DATA, vararg args: Any): SolutionResult<R> {
            require(args.size == 2) { "Args size must be exactly 2, got ${args.size}" }
            require(args[0] is A1) { "First arg must be of type ${A1::class}, got ${args[0]} (type ${args[0]::class})" }
            require(args[1] is A2) { "First arg must be of type ${A2::class}, got ${args[1]} (type ${args[1]::class})" }
            val arg0 = args[0] as A1
            val arg1 = args[1] as A2
            return solutionResult({ res -> formatter(res, arg0, arg1) }) { fn(input, arg0, arg1) }
        }
    }
}

class SolutionWithInputBuilder<INNER_DATA, R1, R2>(
    private val day: Int,
    private val inputSupplier: DataSupplier<INNER_DATA>,
    var part1Solver: Solver<INNER_DATA, R1>,
    var part2Solver: Solver<INNER_DATA, R2>,
) {
    private val isPart1Solved = part1Solver !is EmptySolver
    private val isPart2Solved = part2Solver !is EmptySolver

    fun solvePart1(vararg args: Any): SolutionResult<R1> = part1Solver.solve(inputSupplier.getInputData(), *args)
    fun solvePart2(vararg args: Any): SolutionResult<R2> = part2Solver.solve(inputSupplier.getInputData(), *args)

    fun printSolutions(part1Args: List<Any>, part2Args: List<Any>) {
        println("""Day $day solutions:""")

        if (isPart1Solved) {
            print("Part 1: ")
            solvePart1(*part1Args.toTypedArray()).printFormatted()
        } else {
            println("Part 1 is not solved yet.")
        }

        if (isPart2Solved) {
            print("Part 2: ")
            solvePart2(*part2Args.toTypedArray()).printFormatted()
        } else {
            println("Part 2 is not solved yet.")
        }
    }
}

fun <INNER_DATA, R1, R2> complexSolution(day: Int, prepare: SolutionBuilder<INNER_DATA, R1, R2>.() -> Unit): SolutionBuilder<INNER_DATA, R1, R2> =
    SolutionBuilder<INNER_DATA, R1, R2>(day).apply { prepare() }

fun <INNER_DATA, R> solution(day: Int, prepare: SolutionBuilder<INNER_DATA, R, R>.() -> Unit): SolutionBuilder<INNER_DATA, R, R> =
    SolutionBuilder<INNER_DATA, R, R>(day).apply { prepare() }