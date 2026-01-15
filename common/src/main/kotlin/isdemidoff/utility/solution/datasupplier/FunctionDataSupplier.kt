package isdemidoff.utility.solution.datasupplier

open class FunctionDataSupplier<INNER_DATA>(
    private val dataSupplier: () -> INNER_DATA,
) : DataSupplier<INNER_DATA> {
    override fun getInputData(): INNER_DATA = dataSupplier()
}