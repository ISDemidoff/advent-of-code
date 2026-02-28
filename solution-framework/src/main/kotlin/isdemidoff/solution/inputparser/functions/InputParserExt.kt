@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.functions

import isdemidoff.solution.datasupplier.BlocksContent
import isdemidoff.solution.inputparser.InputParser
import isdemidoff.solution.inputparser.ReducingToBlocksInputParser
import isdemidoff.utility.other.mapFirst
import isdemidoff.utility.other.mapFirstLines
import isdemidoff.utility.other.mapSecond
import isdemidoff.utility.other.mapSecondLines
import isdemidoff.utility.other.mapWith

infix fun <T> InputParser<T>.withToBlocksFunction(toBlocksFn: (T) -> BlocksContent): ReducingToBlocksInputParser<T> =
    ReducingToBlocksFunctionalInputParser(this::parse, toBlocksFn)

infix fun <T, R> InputParser<T>.andThen(fn: (T) -> R): InputParser<R> =
    FunctionalInputParser { fn(this.parse(it)) }

infix fun <T, R> InputParser<List<T>>.andThenOnEveryLine(fn: (T) -> R): InputParser<List<R>> =
    this andThen { it.map(fn) }

infix fun <T, R> InputParser<List<T>>.andThenOnEveryLine(fn: (index: Int, element: T) -> R): InputParser<List<R>> =
    this andThen { it.mapIndexed(fn) }

infix fun <T, R> InputParser<T>.map(fn: (T) -> R): InputParser<R> = this andThen fn
infix fun <T, R> InputParser<List<T>>.mapLines(fn: (T) -> R): InputParser<List<R>> = this andThenOnEveryLine fn
infix fun <T, R> InputParser<List<T>>.mapLinesIndexed(fn: (index: Int, element: T) -> R): InputParser<List<R>> = this andThenOnEveryLine fn

// Pairs

infix fun <A, B, R> InputParser<Pair<A, B>>.map(transform: (A, B) -> R): InputParser<R> =
    this andThen { (l, r) -> transform(l, r) }

infix fun <A, B, R> InputParser<Pair<A, B>>.mapFirst(transform: (A) -> R): InputParser<Pair<R, B>> =
    this andThen { it.mapFirst(transform) }

infix fun <A, B, R> InputParser<Pair<List<A>, B>>.mapFirstLines(transform: (A) -> R): InputParser<Pair<List<R>, B>> =
    this andThen { it.mapFirstLines(transform) }

infix fun <A, B, R> InputParser<Pair<A, B>>.mapSecond(transform: (B) -> R): InputParser<Pair<A, R>> =
    this andThen { it.mapSecond(transform) }

infix fun <A, B, R> InputParser<Pair<A, List<B>>>.mapSecondLines(transform: (B) -> R): InputParser<Pair<A, List<R>>> =
    this andThen { it.mapSecondLines(transform) }

infix fun <A, B, AR, BR> InputParser<Pair<A, B>>.mapWith(transform: Pair<(A) -> AR, (B) -> BR>): InputParser<Pair<AR, BR>> =
    this andThen { it.mapWith(transform) }
