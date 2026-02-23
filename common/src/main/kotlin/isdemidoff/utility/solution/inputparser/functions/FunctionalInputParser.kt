package isdemidoff.utility.solution.inputparser.functions

import isdemidoff.utility.solution.datasupplier.BlocksContent
import isdemidoff.utility.solution.inputparser.InputParser

open class FunctionalInputParser<T>(
    private val fn: (BlocksContent) -> T,
) : (BlocksContent) -> T by fn, InputParser<T> {
    override fun parse(input: BlocksContent): T = fn(input)
}
