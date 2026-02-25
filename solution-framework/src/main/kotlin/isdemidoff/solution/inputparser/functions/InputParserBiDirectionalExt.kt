package isdemidoff.solution.inputparser.functions

import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.solution.inputparser.functions.bidirectional.BiDirectionalFunction
import isdemidoff.utility.other.mapFirst
import isdemidoff.utility.other.mapFirstLines
import isdemidoff.utility.other.mapSecond
import isdemidoff.utility.other.mapSecondLines
import isdemidoff.utility.other.transpose

infix fun <T, R> ReducingToBlocksInputParser<T>.andThen(biDirectionalFn: BiDirectionalFunction<T, R>) =
    ReducingToBlocksFunctionalInputParser(
        fn = { biDirectionalFn.fn(this.parse(it)) },
        blocksFn = { this.convertToBlocks(biDirectionalFn.inv(it)) },
    )

infix fun <T, R> ReducingToBlocksInputParser<List<T>>.andThenOnEveryLine(fn: BiDirectionalFunction<T, R>): ReducingToBlocksInputParser<List<R>> =
    ReducingToBlocksFunctionalInputParser(
        fn = { this.parse(it).map { fn.fn(it) } },
        blocksFn = { this.convertToBlocks(it.map { fn.inv(it) }) },
    )

fun <T, R> ReducingToBlocksInputParser<T>.map(fn: BiDirectionalFunction<T, R>): ReducingToBlocksInputParser<R> = this andThen fn
fun <T, R> ReducingToBlocksInputParser<List<T>>.mapLines(fn: BiDirectionalFunction<T, R>): ReducingToBlocksInputParser<List<R>> = this andThenOnEveryLine fn

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

fun <A, B, R> ReducingToBlocksFunctionalInputParser<Pair<A, B>>.mapFirst(
    transform: BiDirectionalFunction<A, R>,
): ReducingToBlocksFunctionalInputParser<Pair<R, B>> =
    ReducingToBlocksFunctionalInputParser(
        fn = { this.parse(it).mapFirst(transform.fn) },
        blocksFn = { this.convertToBlocks(it.mapFirst(transform.inv)) },
    )

fun <A, B, R> ReducingToBlocksFunctionalInputParser<Pair<List<A>, B>>.mapFirstLines(
    transform: BiDirectionalFunction<A, R>,
): ReducingToBlocksFunctionalInputParser<Pair<List<R>, B>> =
    ReducingToBlocksFunctionalInputParser(
        fn = { this.parse(it).mapFirstLines(transform.fn) },
        blocksFn = { this.convertToBlocks(it.mapFirstLines(transform.inv)) },
    )

fun <A, B, R> ReducingToBlocksFunctionalInputParser<Pair<A, B>>.mapSecond(
    transform: BiDirectionalFunction<B, R>,
): ReducingToBlocksFunctionalInputParser<Pair<A, R>> =
    ReducingToBlocksFunctionalInputParser(
        fn = { this.parse(it).mapSecond(transform.fn) },
        blocksFn = { this.convertToBlocks(it.mapSecond(transform.inv)) },
    )

fun <A, B, R> ReducingToBlocksFunctionalInputParser<Pair<A, List<B>>>.mapSecondLines(
    transform: BiDirectionalFunction<B, R>,
): ReducingToBlocksFunctionalInputParser<Pair<A, List<R>>> =
    ReducingToBlocksFunctionalInputParser(
        fn = { this.parse(it).mapSecondLines(transform.fn) },
        blocksFn = { this.convertToBlocks(it.mapSecondLines(transform.inv)) },
    )