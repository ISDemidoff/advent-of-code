package isdemidoff.solution.inputparser.functions

// One should understand that conversion goes only widening types relative to ReducingToBlocksInputParser <- InputParser and so on

infix fun <T, R> isdemidoff.solution.inputparser.ReducingToBlocksInputParser<T>.andThen(another: isdemidoff.solution.inputparser.ReducingToBlocksInputParser<R>): isdemidoff.solution.inputparser.ReducingToBlocksInputParser<R> =
    ReducingToBlocksFunctionalInputParser(
        fn = { another.parse(this.convertToBlocks(this.parse(it))) },
        blocksFn = { another.convertToBlocks(it) },
    )

infix fun <T, R> isdemidoff.solution.inputparser.ReducingToBlocksInputParser<T>.andThen(another: isdemidoff.solution.inputparser.InputParser<R>): isdemidoff.solution.inputparser.InputParser<R> =
    if (another is isdemidoff.solution.inputparser.ReducingToBlocksInputParser) {
        this andThen another
    } else {
        FunctionalInputParser { another.parse(this.convertToBlocks(this.parse(it))) }
    }
