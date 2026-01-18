package isdemidoff.utility

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactly

class UtilitiesTest : FreeSpec({
    "Check of cartesianProduct(List<A>, List<B>)" - {
        data class CartesianProductTestData<A, B>(
            val first: List<A>,
            val second: List<B>,
            val result: List<Pair<A, B>>,
        )

        withData(
            nameFn = { (first, second, result) ->
                "${first.formatShort()} x ${second.formatShort()} should be ${result.formatShort()}"
            },
            CartesianProductTestData(listOf(1), listOf('a'), listOf(1 to 'a')),
            CartesianProductTestData(listOf(1, 1), listOf('a'), listOf(1 to 'a', 1 to 'a')),
            CartesianProductTestData(listOf(1, 2), listOf('a'), listOf(1 to 'a', 2 to 'a')),
            CartesianProductTestData(listOf(), listOf('a', 'b', 'c', 'd', 'e'), listOf()),
            CartesianProductTestData(
                listOf(1, 2, 3),
                listOf('a', 'b', 'c', 'd', 'e'),
                listOf(
                    1 to 'a',
                    1 to 'b',
                    1 to 'c',
                    1 to 'd',
                    1 to 'e',
                    2 to 'a',
                    2 to 'b',
                    2 to 'c',
                    2 to 'd',
                    2 to 'e',
                    3 to 'a',
                    3 to 'b',
                    3 to 'c',
                    3 to 'd',
                    3 to 'e',
                ),
            )
        ) { (first, second, result) ->
            cartesianProduct(first, second) shouldContainExactly result
        }
    }
})
