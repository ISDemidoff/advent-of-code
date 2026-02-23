package isdemidoff.utility.solution.inputparser.functions.bidirectional

import isdemidoff.utility.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.utility.solution.inputparser.functions.ReducingToBlocksFunctionalInputParser
import isdemidoff.utility.transpose

infix fun <T, R> ReducingToBlocksInputParser<T>.andThen(biDirectionalFn: BiDirectionalFunction<T, R>) =
    ReducingToBlocksFunctionalInputParser(
        fn = { biDirectionalFn.fn(this.parse(it)) },
        blocksFn = { this.convertToBlocks(biDirectionalFn.inv(it)) },
    )

fun <T, R> ReducingToBlocksInputParser<T>.map(fn: BiDirectionalFunction<T, R>): ReducingToBlocksInputParser<R> = this andThen fn
fun <T, R> ReducingToBlocksInputParser<List<T>>.mapLines(fn: BiDirectionalFunction<T, R>): ReducingToBlocksInputParser<List<R>> =
    ReducingToBlocksFunctionalInputParser(
        fn = { this.parse(it).map { fn.fn(it) } },
        blocksFn = { this.convertToBlocks(it.map { fn.inv(it) }) },
    )

fun <E> ReducingToBlocksInputParser<List<E>>.single(): ReducingToBlocksFunctionalInputParser<E> =
    this andThen BiDirectionalFunction(
        fn = { it.single() },
        inv = { listOf(it) },
    )

fun <E> ReducingToBlocksFunctionalInputParser<List<List<E>>>.transpose(): ReducingToBlocksFunctionalInputParser<List<List<E>>> =
    this andThen BiDirectionalFunction(
        fn = { it.transpose() },
        inv = { it }
    )