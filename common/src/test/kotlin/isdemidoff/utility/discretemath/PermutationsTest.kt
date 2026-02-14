package isdemidoff.utility.discretemath

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactly
import isdemidoff.utility.formatShort

class PermutationsTest : FreeSpec({
    "Check of List<E>.permutations()" - {
        data class PermutationsTestData(
            val input: List<Int>,
            val result: List<List<Int>>,
        )

        withData(
            nameFn = { (input, result) ->
                "${input.formatShort()} has permutations ${result.formatShort()}"
            },
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
            permutations(input) shouldContainExactly result
        }
    }
})