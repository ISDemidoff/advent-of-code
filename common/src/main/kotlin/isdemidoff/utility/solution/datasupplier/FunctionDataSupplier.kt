package isdemidoff.utility.solution.datasupplier

/**
 * Unsafe variant of getting a value.
 */
open class FunctionDataSupplier<INNER_DATA>(
    private val dataSupplier: () -> INNER_DATA,
) : DataSupplier<INNER_DATA> {
    override fun getInputData(): Result<INNER_DATA> = runCatching { dataSupplier() }
}