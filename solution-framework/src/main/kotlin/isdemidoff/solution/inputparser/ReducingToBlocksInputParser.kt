package isdemidoff.solution.inputparser

import isdemidoff.solution.datasupplier.BlocksContent

interface ReducingToBlocksInputParser<T> : InputParser<T> {
    fun convertToBlocks(parsedData: T): BlocksContent
}
