@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.functions.bidirectional

fun <R> Function1<String, R>.toBiDirectionalFlat() =
    BiDirectionalFunction(this, { it.toString() })

infix fun <A, B, C> BiDirectionalFunction<A, B>.andThen(other: BiDirectionalFunction<B, C>): BiDirectionalFunction<A, C> =
    BiDirectionalFunction(
        fn = { other.fn(this.fn(it)) },
        inv = { this.inv(other.inv(it)) },
    )

infix fun <A, B, C> BiDirectionalFunction<A, List<B>>.mapWith(other: BiDirectionalFunction<B, C>): BiDirectionalFunction<A, List<C>> =
    BiDirectionalFunction(
        fn = { this.fn(it).map { other.fn(it) } },
        inv = { this.inv(it.map { other.inv(it) }) },
    )
