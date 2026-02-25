package isdemidoff.utility.solution.inputparser.scope

import isdemidoff.utility.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.utility.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.utility.solution.inputparser.functions.bidirectional.BiDirectionalFunctions
import isdemidoff.utility.solution.inputparser.functions.single

object PrimitivesInputParsers : InputParserUseScope {
    val intLines: ReducingToBlocksInputParser<List<Int>> =
        StringsInputParsers.singleBlock andThenOnEveryLine BiDirectionalFunctions.toInt
    val uIntLines: ReducingToBlocksInputParser<List<UInt>> =
        StringsInputParsers.singleBlock andThenOnEveryLine BiDirectionalFunctions.toUInt
    val longLines: ReducingToBlocksInputParser<List<Long>> =
        StringsInputParsers.singleBlock andThenOnEveryLine BiDirectionalFunctions.toLong
    val uLongLines: ReducingToBlocksInputParser<List<ULong>> =
        StringsInputParsers.singleBlock andThenOnEveryLine BiDirectionalFunctions.toULong

    val singleInt: ReducingToBlocksInputParser<Int> = intLines.single()
    val singleUInt: ReducingToBlocksInputParser<UInt> = uIntLines.single()
    val singleLong: ReducingToBlocksInputParser<Long> = longLines.single()
    val singleULong: ReducingToBlocksInputParser<ULong> = uLongLines.single()
}