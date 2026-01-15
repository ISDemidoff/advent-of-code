package isdemidoff.utility.solution.datasupplier

import isdemidoff.utility.solution.inputparser.InputParser

interface DataSupplierWithInputParserUseScope<INNER_DATA> : DataSupplierUseScope<INNER_DATA> {
    val inputParser: InputParser<INNER_DATA>

    override fun string(str: String): DataSupplier<INNER_DATA> = lazyData { inputParser.parse(listOf(listOf(str))) }

    override fun filename(filename: String): DataSupplier<INNER_DATA> =
        ConversionDataSupplier(FileDataSupplier(filename), inputParser)
}