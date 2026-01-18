package isdemidoff.utility.discretemath

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import isdemidoff.utility.formatShort
import kotlin.collections.listOf

class CombinationsTest : FreeSpec({
    "Check of combinations(List<E>, Int) / List<E>.combinationsWithSum(Int)" - {
        data class CombinationsTestData(
            val inputList: List<String>,
            val totalCount: Int,
            val result: List<Map<String, Int>>,
        )

        withData(
            nameFn = { (inputList, totalCount, result) ->
                "${inputList.formatShort()} combinations with total sum $totalCount are ${result.formatShort()}"
            },
            CombinationsTestData(
                emptyList(),
                0,
                emptyList(),
            ),
            CombinationsTestData(
                listOf("a"),
                0,
                listOf(mapOf("a" to 0)),
            ),
            CombinationsTestData(
                listOf("a", "b", "c"),
                0,
                listOf(mapOf("a" to 0, "b" to 0, "c" to 0)),
            ),
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
            inputList combinationsWithSum totalSum shouldContainExactlyInAnyOrder result
        }

        data class CombinationsErrorTestData(
            val inputList: List<String>,
            val totalCount: Int,
            val exceptionMessage: String,
        )

        withData(
            data = mapOf(
                "Empty list with positive sum" to CombinationsErrorTestData(
                    emptyList(),
                    1,
                    "Cannot combine empty list to positive sum."
                ),
                "Empty list with negative sum" to CombinationsErrorTestData(
                    emptyList(),
                    -1,
                    "Cannot combine to negative total sum."
                ),
                "Non-empty list with negative sum" to CombinationsErrorTestData(
                    listOf("a"),
                    -1,
                    "Cannot combine to negative total sum."
                ),
            )
        ) { (inputList, totalCount, exceptionMessage) ->
            shouldThrow<IllegalArgumentException> {
                inputList combinationsWithSum totalCount
            } shouldHaveMessage exceptionMessage
        }
    }

    "Check of createAllChoices(Map<E, Int>" - {
        withData(
            nameFn = { (input, result) ->
                "$input choices are ${result.map { it.formatShort() }.formatShort()}"
            },
            emptyMap<String, Int>() to emptyList(),
            mapOf("a" to 0) to listOf(mapOf("a" to 0)),
            mapOf("a" to 1) to listOf(mapOf("a" to 0), mapOf("a" to 1)),
            mapOf("a" to 2) to listOf(
                mapOf("a" to 0),
                mapOf("a" to 1),
                mapOf("a" to 2),
            ),
            mapOf("a" to 1, "b" to 1) to listOf(
                mapOf("a" to 0, "b" to 0),
                mapOf("a" to 0, "b" to 1),
                mapOf("a" to 1, "b" to 0),
                mapOf("a" to 1, "b" to 1),
            ),
            mapOf("a" to 1, "b" to 0) to listOf(
                mapOf("a" to 0, "b" to 0),
                mapOf("a" to 1, "b" to 0),
            ),
            mapOf("a" to 2, "b" to 1) to listOf(
                mapOf("a" to 0, "b" to 0),
                mapOf("a" to 0, "b" to 1),
                mapOf("a" to 1, "b" to 0),
                mapOf("a" to 1, "b" to 1),
                mapOf("a" to 2, "b" to 0),
                mapOf("a" to 2, "b" to 1),
            ),
        ) { (input, result) ->
            createAllChoices(input) shouldBe result
        }

        "For negative count in input throws exception" {
            shouldThrow<IllegalArgumentException> {
                createAllChoices(mapOf("a" to -1))
            } shouldHaveMessage "Cannot combine negative count, occurred at key 'a'"
        }
    }
})