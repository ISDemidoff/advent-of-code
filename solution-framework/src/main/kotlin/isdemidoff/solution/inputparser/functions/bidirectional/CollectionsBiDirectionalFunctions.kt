@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.functions.bidirectional

import isdemidoff.utility.other.mapMatrix

object CollectionsBiDirectionalFunctions {
    fun <E, R> mapMatrix(transform: BiDirectionalFunction<E, R>): BiDirectionalFunction<List<List<E>>, List<List<R>>> =
        BiDirectionalFunction(
            fn = { it.mapMatrix(transform.fn) },
            inv = { it.mapMatrix(transform.inv) },
        )

    fun <E> single(): BiDirectionalFunction<List<E>, E> = BiDirectionalFunction(
        fn = { it.single() },
        inv = { listOf(it) },
    )
}
