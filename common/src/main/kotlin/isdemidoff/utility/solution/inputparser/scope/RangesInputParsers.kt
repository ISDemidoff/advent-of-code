package isdemidoff.utility.solution.inputparser.scope

import isdemidoff.utility.parsing.intRange
import isdemidoff.utility.parsing.longRange
import isdemidoff.utility.parsing.uIntRange
import isdemidoff.utility.parsing.uLongRange
import isdemidoff.utility.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.utility.solution.inputparser.functions.bidirectional.BiDirectionalFunctions
import isdemidoff.utility.solution.inputparser.functions.bidirectional.mapLines
import isdemidoff.utility.solution.inputparser.functions.bidirectional.single

object RangesInputParsers : InputParserUseScope {
    val longRanges: ReducingToBlocksInputParser<List<LongRange>> = getRangesParser(::longRange)
    val uLongRanges: ReducingToBlocksInputParser<List<ULongRange>> = getRangesParser(::uLongRange)
    val intRanges: ReducingToBlocksInputParser<List<IntRange>> = getRangesParser(::intRange)
    val uIntRanges: ReducingToBlocksInputParser<List<UIntRange>> = getRangesParser(::uIntRange)

    val longRange: ReducingToBlocksInputParser<LongRange> = longRanges.single()
    val uLongRange: ReducingToBlocksInputParser<ULongRange> = uLongRanges.single()
    val intRange: ReducingToBlocksInputParser<IntRange> = intRanges.single()
    val uIntRange: ReducingToBlocksInputParser<UIntRange> = uIntRanges.single()

    private fun <E: Comparable<E>, R : ClosedRange<E>> getRangesParser(rangeParse: (String) -> R): ReducingToBlocksInputParser<List<R>> =
        StringsInputParsers.singleBlock.mapLines(BiDirectionalFunctions.toRange(rangeParse))
}