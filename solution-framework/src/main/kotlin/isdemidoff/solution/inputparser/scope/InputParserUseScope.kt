package isdemidoff.solution.inputparser.scope

import isdemidoff.solution.datasupplier.BlocksContent
import isdemidoff.solution.inputparser.InputParser
import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.FunctionalInputParser
import isdemidoff.solution.inputparser.functions.ReducingToBlocksFunctionalInputParser

interface InputParserUseScope {
    fun <R> inputParser(transform: (blocks: BlocksContent) -> R): InputParser<R> =
        FunctionalInputParser(transform)

    fun <R> inputParser(
        transform: (blocks: BlocksContent) -> R,
        inverse: (R) -> BlocksContent,
    ): ReducingToBlocksInputParser<R> =
        ReducingToBlocksFunctionalInputParser(transform, inverse)
}