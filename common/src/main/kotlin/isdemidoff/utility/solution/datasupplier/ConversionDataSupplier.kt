package isdemidoff.utility.solution.datasupplier

class ConversionDataSupplier<FROM, TO>(
    fromDataSupplier: DataSupplier<FROM>,
    converter: (FROM) -> TO,
) : FunctionDataSupplier<TO>({ converter(fromDataSupplier.getInputData()) })