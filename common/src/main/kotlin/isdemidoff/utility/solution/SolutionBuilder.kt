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

    fun <R> emptySolver() = EmptySolver<INNER_DATA, R>()
}

class SolutionWithInputBuilder<INNER_DATA, R1, R2>(
    private val day: Int,
    private val inputSupplier: DataSupplier<INNER_DATA>,
    var part1Solver: Solver<INNER_DATA, R1>,
    var part2Solver: Solver<INNER_DATA, R2>,
) {
    private val isPart1Solved = part1Solver !is EmptySolver
    private val isPart2Solved = part2Solver !is EmptySolver

    fun solvePart1(): SolutionResult<R1> = part1Solver.solve(inputSupplier.getInputData())
    fun solvePart2(): SolutionResult<R2> = part2Solver.solve(inputSupplier.getInputData())

    fun printSolutions() {
        println("""Day $day solutions:""")

        if (isPart1Solved) {
            print("Part 1: ")
            solvePart1().printFormatted()
        } else {
            println("Part 1 is not solved yet.")
        }

        if (isPart2Solved) {
            print("Part 2: ")
            solvePart2().printFormatted()
        } else {
            println("Part 2 is not solved yet.")
        }
    }
}

fun <INNER_DATA, R1, R2> complexSolution(day: Int, prepare: SolutionBuilder<INNER_DATA, R1, R2>.() -> Unit): SolutionBuilder<INNER_DATA, R1, R2> =
    SolutionBuilder<INNER_DATA, R1, R2>(day).apply { prepare() }

fun <INNER_DATA, R> solution(day: Int, prepare: SolutionBuilder<INNER_DATA, R, R>.() -> Unit): SolutionBuilder<INNER_DATA, R, R> =
    SolutionBuilder<INNER_DATA, R, R>(day).apply { prepare() }