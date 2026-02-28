@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.other

fun <A, B, R> Pair<A, B>.mapFirst(transform: (A) -> R): Pair<R, B> = transform(first) to second
fun <A, B, R> Pair<A, B>.mapSecond(transform: (B) -> R): Pair<A, R> = first to transform(second)

fun <A, B, R> Pair<List<A>, B>.mapFirstLines(transform: (A) -> R): Pair<List<R>, B> = first.map(transform) to second
fun <A, B, R> Pair<A, List<B>>.mapSecondLines(transform: (B) -> R): Pair<A, List<R>> = first to second.map(transform)

fun <A, B, AR, BR> Pair<A, B>.mapWith(transforms: Pair<(A) -> AR, (B) -> BR>) =
    transforms.first(this.first) to transforms.second(this.second)

fun <E : Comparable<E>> Pair<E, E>.sorted(): Pair<E, E> = this.toList().sorted().let { (a, b) -> a to b }