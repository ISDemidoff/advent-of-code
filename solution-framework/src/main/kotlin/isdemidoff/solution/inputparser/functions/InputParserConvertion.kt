package isdemidoff.solution.inputparser.functions

import isdemidoff.solution.inputparser.InputParser
import isdemidoff.solution.inputparser.ReducingToBlocksInputParser

// One should understand that conversion goes only widening types relative to ReducingToBlocksInputParser <- InputParser and so on

infix fun <T, R> ReducingToBlocksInputParser<T>.andThen(another: ReducingToBlocksInputParser<R>): ReducingToBlocksInputParser<R> =
    ReducingToBlocksFunctionalInputParser(
        fn = { another.parse(this.convertToBlocks(this.parse(it))) },
        blocksFn = { another.convertToBlocks(it) },
    )

infix fun <T, R> ReducingToBlocksInputParser<T>.andThen(another: InputParser<R>): InputParser<R> =
    if (another is ReducingToBlocksInputParser) {
        this andThen another
    } else {
        FunctionalInputParser { another.parse(this.convertToBlocks(this.parse(it))) }
    }
