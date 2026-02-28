@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.scope

import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions
import isdemidoff.solution.inputparser.functions.single

object RangesInputParsers : InputParserUseScope {
    val intRanges: ReducingToBlocksInputParser<List<IntRange>> =
        StringsInputParsers.singleBlock andThenOnEveryLine SimpleBiDirectionalFunctions.toIntRange
    val uIntRanges: ReducingToBlocksInputParser<List<UIntRange>> =
        StringsInputParsers.singleBlock andThenOnEveryLine SimpleBiDirectionalFunctions.toUIntRange
    val longRanges: ReducingToBlocksInputParser<List<LongRange>> =
        StringsInputParsers.singleBlock andThenOnEveryLine SimpleBiDirectionalFunctions.toLongRange
    val uLongRanges: ReducingToBlocksInputParser<List<ULongRange>> =
        StringsInputParsers.singleBlock andThenOnEveryLine SimpleBiDirectionalFunctions.toULongRange

    val intRange: ReducingToBlocksInputParser<IntRange> = intRanges.single()
    val uIntRange: ReducingToBlocksInputParser<UIntRange> = uIntRanges.single()
    val longRange: ReducingToBlocksInputParser<LongRange> = longRanges.single()
    val uLongRange: ReducingToBlocksInputParser<ULongRange> = uLongRanges.single()
}
