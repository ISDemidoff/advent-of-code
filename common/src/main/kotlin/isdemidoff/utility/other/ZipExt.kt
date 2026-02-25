@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.other

fun <E, R> Iterable<E>.zipTriple(transform: (E, E, E) -> R) =
    zipWithNext().zipWithNext { leftPair, rightPair -> transform(leftPair.first, leftPair.second, rightPair.second) }

fun <R> CharSequence.zipTriple(transform: (Char, Char, Char) -> R) = this.asIterable().zipTriple(transform)