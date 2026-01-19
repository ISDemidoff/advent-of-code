package isdemidoff.utility.solution.datasupplier.scope

import isdemidoff.utility.solution.datasupplier.ConversionDataSupplier
import isdemidoff.utility.solution.datasupplier.DataSupplier
import isdemidoff.utility.solution.datasupplier.FileDataSupplier
import isdemidoff.utility.solution.datasupplier.FunctionDataSupplier
import isdemidoff.utility.solution.inputparser.InputParser

interface DataSupplierWithInputParserUseScope<INNER_DATA> : DataSupplierUseScope<INNER_DATA> {
    val inputParserNullable: InputParser<INNER_DATA>?
    val inputParser: InputParser<INNER_DATA>
        get() = requireNotNull(inputParserNullable) { "input parser can not be null" }

    override fun string(str: String): DataSupplier<INNER_DATA> =
        ConversionDataSupplier(FunctionDataSupplier { listOf(listOf(str)) }, inputParser)

    override fun filename(filename: String): DataSupplier<INNER_DATA> =
        ConversionDataSupplier(FileDataSupplier(filename), inputParser)
}