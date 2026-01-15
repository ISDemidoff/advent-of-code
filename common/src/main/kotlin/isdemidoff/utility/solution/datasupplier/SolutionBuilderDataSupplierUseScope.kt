package isdemidoff.utility.solution.datasupplier

import isdemidoff.utility.solution.SolutionBuilderContext
import isdemidoff.utility.solution.inputparser.InputParser

class SolutionBuilderDataSupplierUseScope<INNER_DATA>(
    private val context: SolutionBuilderContext,
    override val inputParserNullable: InputParser<INNER_DATA>?,
) : DataSupplierWithInputParserUseScope<INNER_DATA> {
    override fun inputFile(): DataSupplier<INNER_DATA> = filename("day${context.day}-input.txt")
    override fun inputFile(suffix: Any): DataSupplier<INNER_DATA> = filename("day${context.day}-input-$suffix.txt")
    override fun sampleFile(): DataSupplier<INNER_DATA> = filename("day${context.day}-sample.txt")
    override fun sampleFile(suffix: Any): DataSupplier<INNER_DATA> = filename("day${context.day}-sample-$suffix.txt")
}