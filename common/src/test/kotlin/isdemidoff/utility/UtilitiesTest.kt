package isdemidoff.utility

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class UtilitiesTest : FreeSpec({
    "Check of cartesianProduct(List<A>, List<B>)" - {
        data class CartesianProductTestData<A, B>(
            val first: List<A>,
            val second: List<B>,
            val result: List<Pair<A, B>>,
        )

        withData(
            nameFn = { "${it.first.formatShort()} x ${it.second.formatShort()} should be ${it.result.formatShort()}" },
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

    "Check of List<E>.permutations()" - {
        data class PermutationsTestData(
            val input: List<Int>,
            val result: List<List<Int>>,
        )

        withData(
            nameFn = { "${it.input.formatShort()} has permutations ${it.result.formatShort()}" },
            PermutationsTestData(listOf(1), listOf(listOf(1))),
            PermutationsTestData(listOf(1, 2), listOf(listOf(1, 2), listOf(2, 1)),),
            PermutationsTestData(listOf(1, 1), listOf(listOf(1, 1), listOf(1, 1)),),
            PermutationsTestData(
                listOf(1, 2, 3),
                listOf(
                    listOf(1, 2, 3),
                    listOf(1, 3, 2),
                    listOf(2, 1, 3),
                    listOf(2, 3, 1),
                    listOf(3, 1, 2),
                    listOf(3, 2, 1),
                ),
            ),
        ) { (input, result) ->
            input.permutations() shouldContainExactly result
        }
    }

    "Check of List<E>.combinations(Int)" - {
        data class CombinationsTestData(
            val inputList: List<String>,
            val totalCount: Int,
            val result: List<Map<String, Int>>,
        )

        withData(
            nameFn = { "${it.inputList.formatShort()} combinations with total sum ${it.totalCount} are ${it.result.formatShort()}" },
            CombinationsTestData(
                listOf("a"),
                1,
                listOf(mapOf("a" to 1)),
            ),
            CombinationsTestData(
                listOf("a"),
                2,
                listOf(mapOf("a" to 2)),
            ),
            CombinationsTestData(
                listOf("a", "b", "c", "d", "e"),
                1,
                listOf(
                    mapOf("a" to 1, "b" to 0, "c" to 0, "d" to 0, "e" to 0),
                    mapOf("a" to 0, "b" to 1, "c" to 0, "d" to 0, "e" to 0),
                    mapOf("a" to 0, "b" to 0, "c" to 1, "d" to 0, "e" to 0),
                    mapOf("a" to 0, "b" to 0, "c" to 0, "d" to 1, "e" to 0),
                    mapOf("a" to 0, "b" to 0, "c" to 0, "d" to 0, "e" to 1),
                ),
            ),
            CombinationsTestData(
                listOf("a", "b", "c"),
                2,
                listOf(
                    mapOf("a" to 2, "b" to 0, "c" to 0),
                    mapOf("a" to 0, "b" to 2, "c" to 0),
                    mapOf("a" to 0, "b" to 0, "c" to 2),
                    mapOf("a" to 1, "b" to 1, "c" to 0),
                    mapOf("a" to 1, "b" to 0, "c" to 1),
                    mapOf("a" to 0, "b" to 1, "c" to 1),
                ),
            ),
            CombinationsTestData(
                listOf("a", "b"),
                5,
                listOf(
                    mapOf("a" to 0, "b" to 5),
                    mapOf("a" to 1, "b" to 4),
                    mapOf("a" to 2, "b" to 3),
                    mapOf("a" to 3, "b" to 2),
                    mapOf("a" to 4, "b" to 1),
                    mapOf("a" to 5, "b" to 0),
                ),
            )
        ) { (inputList, totalSum, result) ->
            inputList combinations totalSum shouldContainExactlyInAnyOrder result
        }
    }
})

private fun List<*>.formatShort() =
    if (isEmpty()) {
        "<empty list>"
    } else {
        joinToString(prefix = "[", postfix = "]", separator = ", ", limit = 3, truncated = "<truncated>")
    }
