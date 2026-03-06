@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.scope

import isdemidoff.solution.datasupplier.BlocksContent
import isdemidoff.solution.inputparser.InputParser
import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.andThenOnEveryLine
import isdemidoff.solution.inputparser.functions.bidirectional.BiDirectionalFunction
import isdemidoff.solution.inputparser.functions.single
import isdemidoff.solution.inputparser.functions.withToBlocksFunction
import isdemidoff.utility.other.transpose

val InputParserUseScope.allBlocks: ReducingToBlocksInputParser<BlocksContent>
    get() = inputParser(
        transform = { it },
        inverse = { it },
    )

// ----------------------------------------
// Two blocks parsing
// ----------------------------------------

val InputParserUseScope.twoBlocks: ReducingToBlocksInputParser<Pair<List<String>, List<String>>>
    get() = inputParser(
        transform = {
            require(it.size == 2) { "Expected 2 blocks but got ${it.size} blocks" }
            it.first() to it.last()
        },
        inverse = { it.toList() },
    )

// ----------------------------------------
// Single block parsing
// ----------------------------------------

/**
 * Expects single block of strings in input.
 * Although it can be used in combination with others parsers, it performs no data transformation.
 */
val InputParserUseScope.singleBlock: ReducingToBlocksInputParser<List<String>>
    get() = allBlocks.single()

fun <R> InputParserUseScope.uniformLinesParser(transform: (line: String) -> R): InputParser<List<R>> =
    singleBlock andThenOnEveryLine transform

fun <R> InputParserUseScope.uniformLinesParser(transform: BiDirectionalFunction<String, R>): ReducingToBlocksInputParser<List<R>> =
    singleBlock andThenOnEveryLine transform

fun <R> InputParserUseScope.uniformLinesParserIndexed(transform: (index: Int, line: String) -> R): InputParser<List<R>> =
    singleBlock andThenOnEveryLine transform

// ----------------------------------------
// Single line parsing
// ----------------------------------------

/**
 * Expects single line in input.
 * Although it can be used in combination with others parsers, it performs no data transformation.
 */
val InputParserUseScope.singleLine: ReducingToBlocksInputParser<String>
    get() = singleBlock.single()

// ----------------------------------------
// More complex parsing
// ----------------------------------------

/**
 * Expects single block and splits each input line by given [delimiter].
 * When used in combination with other parser, passes them tokens of every line as separate block.
 */
fun InputParserUseScope.splitLinesBy(delimiter: String): ReducingToBlocksInputParser<BlocksContent> =
    uniformLinesParser { it.split(delimiter) } withToBlocksFunction { it }

fun InputParserUseScope.splitLinesBy(regex: Regex): ReducingToBlocksInputParser<BlocksContent> =
    uniformLinesParser { it.split(regex) } withToBlocksFunction { it }

/**
 * Splits single input line by given [delimiter].
 * When used in combination with other parser, passes them tokens as list.
 */
fun InputParserUseScope.splitLineBy(delimiter: String): ReducingToBlocksInputParser<List<String>> =
    splitLinesBy(delimiter).single()

fun InputParserUseScope.splitLineBy(regex: Regex): ReducingToBlocksInputParser<List<String>> =
    splitLinesBy(regex).single()

/**
 * Expects single block input where data located with any spacing between columns, i.e.
 * ```
 * a    b  c
 * 1231 2  1
 * 23   52 454
 * aaa  b  c
 * ```
 * produces output
 * ```
 * [
 *   ["a", "b", "c"],
 *   ["1231", "2", "1"],
 *   ["23", "52", "454"],
 *   ["aaa", "b", "c"]
 * ]
 * ```
 * When used in combination with other parser, passes each row as independent block.
 */
val InputParserUseScope.spaceDelimitedTable: ReducingToBlocksInputParser<BlocksContent>
    get() = singleBlock andThenOnEveryLine { it.trim().split("""\s+""".toRegex()) } withToBlocksFunction { it }

/**
 * Expects any number of blocks. Performs virtual transposition of data with idea that every block is a row and lines are cells.
 */
val InputParserUseScope.transpose: ReducingToBlocksInputParser<BlocksContent>
    get() = inputParser(
        transform = { it.transpose() },
        inverse = { it },
    )
