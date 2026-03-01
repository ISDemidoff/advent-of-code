package isdemidoff.solution.datasupplier.scope

import isdemidoff.solution.datasupplier.ConversionDataSupplier
import isdemidoff.solution.datasupplier.DataSupplier
import isdemidoff.solution.datasupplier.FileDataSupplier
import isdemidoff.solution.datasupplier.FunctionDataSupplier
import isdemidoff.solution.inputparser.InputParser

interface DataSupplierWithInputParserUseScope<INNER_DATA> : DataSupplierUseScope<INNER_DATA> {
    val inputParserNullable: InputParser<INNER_DATA>?
    val inputParser: InputParser<INNER_DATA>
        get() = requireNotNull(inputParserNullable) { "input parser can not be null" }

    override fun strings(vararg strings: String): DataSupplier<INNER_DATA> =
        ConversionDataSupplier(FunctionDataSupplier { listOf(strings.toList()) }, { inputParser.parse(it) })

    override fun filename(filename: String): DataSupplier<INNER_DATA> =
        ConversionDataSupplier(FileDataSupplier(filename), { inputParser.parse(it) })
}
