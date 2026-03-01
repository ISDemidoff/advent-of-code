package isdemidoff.solution.inputparser

import isdemidoff.solution.datasupplier.BlocksContent

interface InputParser<T> {
    fun parse(input: BlocksContent): T
}
