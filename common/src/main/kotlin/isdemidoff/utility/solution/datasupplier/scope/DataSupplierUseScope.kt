package isdemidoff.utility.solution.datasupplier.scope

import isdemidoff.utility.solution.datasupplier.DataSupplier
import isdemidoff.utility.solution.datasupplier.FunctionDataSupplier

interface DataSupplierUseScope<INNER_DATA> {
    fun lazyData(dataSupplier: () -> INNER_DATA): DataSupplier<INNER_DATA> = FunctionDataSupplier(dataSupplier)
    fun raw(data: INNER_DATA): DataSupplier<INNER_DATA> = lazyData { data }
    fun strings(vararg strings: String): DataSupplier<INNER_DATA>
    fun string(str: String): DataSupplier<INNER_DATA> = strings(str)
    fun filename(filename: String): DataSupplier<INNER_DATA>
    fun inputFile(): DataSupplier<INNER_DATA>
    fun inputFile(suffix: Any): DataSupplier<INNER_DATA>
    fun sampleFile(): DataSupplier<INNER_DATA>
    fun sampleFile(suffix: Any): DataSupplier<INNER_DATA>
}
