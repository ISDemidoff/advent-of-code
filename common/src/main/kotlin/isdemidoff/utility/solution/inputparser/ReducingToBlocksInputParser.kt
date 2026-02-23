package isdemidoff.utility.solution.inputparser

import isdemidoff.utility.solution.datasupplier.BlocksContent

interface ReducingToBlocksInputParser<T> : InputParser<T> {
    fun convertToBlocks(parsedData: T): BlocksContent
}