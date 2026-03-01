package isdemidoff.solution.datasupplier

class ConversionDataSupplier<FROM, TO>(
    private val fromDataSupplier: DataSupplier<FROM>,
    private val converter: (FROM) -> TO,
) : DataSupplier<TO> {
    override fun getInputData() = fromDataSupplier.getInputData().mapCatching(converter)
}
