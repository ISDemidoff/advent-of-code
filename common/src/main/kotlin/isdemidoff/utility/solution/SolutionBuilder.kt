package isdemidoff.utility.solution

import isdemidoff.utility.solution.datasupplier.DataSupplier
import isdemidoff.utility.solution.datasupplier.DataSupplierUseScope
import isdemidoff.utility.solution.datasupplier.SolutionBuilderDataSupplierUseScope
import isdemidoff.utility.solution.inputparser.InputParser
import isdemidoff.utility.solution.inputparser.InputParserUseScope
import isdemidoff.utility.solution.solver.Solver
import isdemidoff.utility.solution.solver.SolverUseScope

class SolutionBuilder<INNER_DATA, R1, R2>(
    day: Int,
) : InputParserUseScope, SolverUseScope<INNER_DATA> {
    val context = SolutionBuilderContext(day)
    var inputParser: InputParser<INNER_DATA>? = null
    var part1Solver: Solver<INNER_DATA, R1> = emptySolver()
    var part2Solver: Solver<INNER_DATA, R2> = emptySolver()

    fun input(inputConfiguration: DataSupplierUseScope<INNER_DATA>.() -> DataSupplier<INNER_DATA>): SolutionWithInputBuilder<INNER_DATA, R1, R2> =
        SolutionWithInputBuilder(
            inputSupplier = SolutionBuilderDataSupplierUseScope(
                context = context,
                inputParserNullable = inputParser,
            ).inputConfiguration(),
            part1Solver = part1Solver,
            part2Solver = part2Solver,
        )
}
