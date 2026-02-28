@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.scope

import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.andThen
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.functions.bidirectional.CollectionsBiDirectionalFunctions.mapMatrix
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toInt
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toLong
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toUInt
import isdemidoff.solution.inputparser.functions.bidirectional.SimpleBiDirectionalFunctions.toULong
import isdemidoff.solution.inputparser.functions.single
import isdemidoff.solution.inputparser.scope.StringsInputParsers.singleBlock
import isdemidoff.solution.inputparser.scope.StringsInputParsers.spaceDelimitedTable

object PrimitivesInputParsers : InputParserUseScope {
    val intLines: ReducingToBlocksInputParser<List<Int>> =
        singleBlock andThenOnEveryLine toInt
    val uIntLines: ReducingToBlocksInputParser<List<UInt>> =
        singleBlock andThenOnEveryLine toUInt
    val longLines: ReducingToBlocksInputParser<List<Long>> =
        singleBlock andThenOnEveryLine toLong
    val uLongLines: ReducingToBlocksInputParser<List<ULong>> =
        singleBlock andThenOnEveryLine toULong

    val singleInt: ReducingToBlocksInputParser<Int> = intLines.single()
    val singleUInt: ReducingToBlocksInputParser<UInt> = uIntLines.single()
    val singleLong: ReducingToBlocksInputParser<Long> = longLines.single()
    val singleULong: ReducingToBlocksInputParser<ULong> = uLongLines.single()

    val intTable: ReducingToBlocksInputParser<List<List<Int>>> =
        spaceDelimitedTable andThen mapMatrix(toInt)
    val uIntTable: ReducingToBlocksInputParser<List<List<UInt>>> =
        spaceDelimitedTable andThen mapMatrix(toUInt)
    val longTable: ReducingToBlocksInputParser<List<List<Long>>> =
        spaceDelimitedTable andThen mapMatrix(toLong)
    val uLongTable: ReducingToBlocksInputParser<List<List<ULong>>> =
        spaceDelimitedTable andThen mapMatrix(toULong)
}
