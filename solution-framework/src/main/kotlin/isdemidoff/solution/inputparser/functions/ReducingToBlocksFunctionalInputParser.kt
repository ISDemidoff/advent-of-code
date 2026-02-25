package isdemidoff.solution.inputparser.functions

import isdemidoff.solution.datasupplier.BlocksContent
import isdemidoff.solution.inputparser.ReducingToBlocksInputParser

class ReducingToBlocksFunctionalInputParser<T>(
    fn: (BlocksContent) -> T,
    private val blocksFn: (T) -> BlocksContent,
) : ReducingToBlocksInputParser<T>, FunctionalInputParser<T>(fn) {
    override fun convertToBlocks(parsedData: T): BlocksContent = blocksFn(parsedData)
}