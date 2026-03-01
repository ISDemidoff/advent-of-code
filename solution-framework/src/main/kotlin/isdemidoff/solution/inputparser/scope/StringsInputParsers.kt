@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.scope

import isdemidoff.solution.datasupplier.BlocksContent
import isdemidoff.solution.inputparser.InputParser
import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.mapLines
import isdemidoff.solution.inputparser.functions.mapLinesIndexed
import isdemidoff.solution.inputparser.functions.single
import isdemidoff.solution.inputparser.functions.withToBlocksFunction
import isdemidoff.utility.other.transpose

object StringsInputParsers : InputParserUseScope {

    val allBlocks: ReducingToBlocksInputParser<BlocksContent> = inputParser(
        transform = { it },
        inverse = { it },
    )

    // ----------------------------------------
    // Two blocks parsing
    // ----------------------------------------

    val twoBlocks: ReducingToBlocksInputParser<Pair<List<String>, List<String>>> = inputParser(
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
    val singleBlock: ReducingToBlocksInputParser<List<String>> = allBlocks.single()

    fun <R> uniformLinesParser(transform: (line: String) -> R): InputParser<List<R>> =
        singleBlock.mapLines(transform)

    fun <R> uniformLinesParserIndexed(transform: (index: Int, line: String) -> R): InputParser<List<R>> =
        singleBlock.mapLinesIndexed(transform)

    // ----------------------------------------
    // Single line parsing
    // ----------------------------------------

    /**
     * Expects single line in input.
     * Although it can be used in combination with others parsers, it performs no data transformation.
     */
    val singleLine: ReducingToBlocksInputParser<String> = singleBlock.single()

    // ----------------------------------------
    // More complex parsing
    // ----------------------------------------

    /**
     * Expects single block and splits each input line by given [delimiter].
     * When used in combination with other parser, passes them tokens of every line as separate block.
     */
    fun splitLinesBy(delimiter: String): ReducingToBlocksInputParser<BlocksContent> =
        uniformLinesParser { it.split(delimiter) } withToBlocksFunction { it }

    fun splitLinesBy(regex: Regex): ReducingToBlocksInputParser<BlocksContent> =
        uniformLinesParser { it.split(regex) } withToBlocksFunction { it }

    /**
     * Splits single input line by given [delimiter].
     * When used in combination with other parser, passes them tokens as list.
     */
    fun splitLineBy(delimiter: String): ReducingToBlocksInputParser<List<String>> =
        splitLinesBy(delimiter).single()

    fun splitLineBy(regex: Regex): ReducingToBlocksInputParser<List<String>> =
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
    val spaceDelimitedTable: ReducingToBlocksInputParser<BlocksContent> =
        singleBlock.mapLines { it.trim().split("""\s+""".toRegex()) } withToBlocksFunction { it }

    /**
     * Expects any number of blocks. Performs virtual transposition of data with idea that every block is a row and lines are cells.
     */
    val transpose: ReducingToBlocksInputParser<BlocksContent> =
        inputParser(
            transform = { it.transpose() },
            inverse = { it },
        )
}
