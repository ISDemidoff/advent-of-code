package isdemidoff.utility.solution.inputparser

import isdemidoff.utility.solution.datasupplier.BlocksContent

interface InputParser<T> {
    fun parse(input: BlocksContent): T
}