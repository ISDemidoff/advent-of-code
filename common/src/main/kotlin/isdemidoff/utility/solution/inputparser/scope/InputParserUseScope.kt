package isdemidoff.utility.solution.inputparser.scope

import isdemidoff.utility.solution.datasupplier.BlocksContent
import isdemidoff.utility.solution.inputparser.InputParser
import isdemidoff.utility.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.utility.solution.inputparser.functions.FunctionalInputParser
import isdemidoff.utility.solution.inputparser.functions.ReducingToBlocksFunctionalInputParser

interface InputParserUseScope {
    fun <R> inputParser(transform: (blocks: BlocksContent) -> R): InputParser<R> =
        FunctionalInputParser(transform)

    fun <R> inputParser(
        transform: (blocks: BlocksContent) -> R,
        inverse: (R) -> BlocksContent,
    ): ReducingToBlocksInputParser<R> =
        ReducingToBlocksFunctionalInputParser(transform, inverse)
}