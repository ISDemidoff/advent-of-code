package isdemidoff.utility.solution.inputparser.functions

import isdemidoff.utility.solution.datasupplier.BlocksContent
import isdemidoff.utility.solution.inputparser.ReducingToBlocksInputParser

class ReducingToBlocksFunctionalInputParser<T>(
    fn: (BlocksContent) -> T,
    private val blocksFn: (T) -> BlocksContent,
) : ReducingToBlocksInputParser<T>, FunctionalInputParser<T>(fn) {
    override fun convertToBlocks(parsedData: T): BlocksContent = blocksFn(parsedData)
}