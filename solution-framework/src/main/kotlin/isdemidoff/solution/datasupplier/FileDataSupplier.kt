package isdemidoff.solution.datasupplier

import isdemidoff.utility.input.readBlocks

class FileDataSupplier(
    filename: String,
) : FunctionDataSupplier<BlocksContent>({ readBlocks(filename) })
