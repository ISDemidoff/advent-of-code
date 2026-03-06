@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.scope

import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toIntRange
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toLongRange
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toUIntRange
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toULongRange
import isdemidoff.solution.inputparser.functions.single

val InputParserUseScope.intRanges: ReducingToBlocksInputParser<List<IntRange>>
    get() = uniformLinesParser(toIntRange)
val InputParserUseScope.uIntRanges: ReducingToBlocksInputParser<List<UIntRange>>
    get() = uniformLinesParser(toUIntRange)
val InputParserUseScope.longRanges: ReducingToBlocksInputParser<List<LongRange>>
    get() = uniformLinesParser(toLongRange)
val InputParserUseScope.uLongRanges: ReducingToBlocksInputParser<List<ULongRange>>
    get() = uniformLinesParser(toULongRange)

val InputParserUseScope.intRange: ReducingToBlocksInputParser<IntRange>
    get() = intRanges.single()
val InputParserUseScope.uIntRange: ReducingToBlocksInputParser<UIntRange>
    get() = uIntRanges.single()
val InputParserUseScope.longRange: ReducingToBlocksInputParser<LongRange>
    get() = longRanges.single()
val InputParserUseScope.uLongRange: ReducingToBlocksInputParser<ULongRange>
    get() = uLongRanges.single()
