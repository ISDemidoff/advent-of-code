@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.scope

import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.functions.bidirectional.CollectionsBiDirectionalFunctions.mapMatrix
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toInt
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toLong
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toUInt
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toULong
import isdemidoff.solution.inputparser.functions.single

val InputParserUseScope.intLines: ReducingToBlocksInputParser<List<Int>>
    get() = uniformLinesParser(toInt)
val InputParserUseScope.uIntLines: ReducingToBlocksInputParser<List<UInt>>
    get() = uniformLinesParser(toUInt)
val InputParserUseScope.longLines: ReducingToBlocksInputParser<List<Long>>
    get() = uniformLinesParser(toLong)
val InputParserUseScope.uLongLines: ReducingToBlocksInputParser<List<ULong>>
    get() = uniformLinesParser(toULong)

val InputParserUseScope.singleInt: ReducingToBlocksInputParser<Int>
    get() = intLines.single()
val InputParserUseScope.singleUInt: ReducingToBlocksInputParser<UInt>
    get() = uIntLines.single()
val InputParserUseScope.singleLong: ReducingToBlocksInputParser<Long>
    get() = longLines.single()
val InputParserUseScope.singleULong: ReducingToBlocksInputParser<ULong>
    get() = uLongLines.single()

val InputParserUseScope.intTable: ReducingToBlocksInputParser<List<List<Int>>>
    get() = spaceDelimitedTable andThen mapMatrix(toInt)
val InputParserUseScope.uIntTable: ReducingToBlocksInputParser<List<List<UInt>>>
    get() = spaceDelimitedTable andThen mapMatrix(toUInt)
val InputParserUseScope.longTable: ReducingToBlocksInputParser<List<List<Long>>>
    get() = spaceDelimitedTable andThen mapMatrix(toLong)
val InputParserUseScope.uLongTable: ReducingToBlocksInputParser<List<List<ULong>>>
    get() = spaceDelimitedTable andThen mapMatrix(toULong)
