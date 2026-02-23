package isdemidoff.utility.solution.inputparser.scope

import isdemidoff.utility.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.utility.solution.inputparser.functions.bidirectional.BiDirectionalFunctions
import isdemidoff.utility.solution.inputparser.functions.bidirectional.mapLines
import isdemidoff.utility.solution.inputparser.functions.bidirectional.single

object PrimitivesInputParsers : InputParserUseScope {
    val intLines: ReducingToBlocksInputParser<List<Int>> =
        StringsInputParsers.singleBlock.mapLines(BiDirectionalFunctions.toInt)

    val longLines: ReducingToBlocksInputParser<List<Long>> =
        StringsInputParsers.singleBlock.mapLines(BiDirectionalFunctions.toLong)

    val singleInt: ReducingToBlocksInputParser<Int> = intLines.single()
    val singleLong: ReducingToBlocksInputParser<Long> = longLines.single()
}