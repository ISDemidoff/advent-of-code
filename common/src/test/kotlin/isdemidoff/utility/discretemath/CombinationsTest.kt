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
            combinationsWithRepetitions(inputList, totalSum) shouldContainExactlyInAnyOrder result
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
                combinationsWithRepetitions(inputList, totalCount)
            } shouldHaveMessage exceptionMessage
        }
    }

    "Check of createAllChoices(Map<E, Int>)" - {
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
            createAllCombinations(input) shouldBe result
        }

        "For negative count in input throws exception" {
            shouldThrow<IllegalArgumentException> {
                createAllCombinations(mapOf("a" to -1))
            } shouldHaveMessage "Cannot combine negative count, occurred at key 'a'"
        }
    }

    "Check of chooseItems(List<E>, Int)" - {
        data class ChooseItemsTestData(
            val inputList: List<String>,
            val totalCount: Int,
            val expectedResult: List<List<String>>,
        )

        withData(
            nameFn = { (inputList, totalCount, expectedResult) ->
                "Choosing $totalCount items from ${inputList.formatShort()} should be ${expectedResult.map { it.formatShort() }.formatShort()}"
            },
            ChooseItemsTestData(listOf(), 0, listOf(listOf())),
            ChooseItemsTestData(listOf("a", "b"), 0, listOf(listOf())),
            ChooseItemsTestData(listOf("a"), 1, listOf(listOf("a"))),
            ChooseItemsTestData(listOf("a", "b", "c"), 1, listOf(listOf("a"), listOf("b"), listOf("c"))),
            ChooseItemsTestData(
                listOf("a", "b", "c"),
                2,
                listOf(
                    listOf("a", "b"),
                    listOf("a", "c"),
                    listOf("b", "a"),
                    listOf("b", "c"),
                    listOf("c", "a"),
                    listOf("c", "b"),
                )
            ),
        ) { (inputList, totalCount, expectedResult) ->
            chooseItems(inputList, totalCount) shouldBe expectedResult
        }

        data class ChooseItemsErrorTestData(
            val inputList: List<String>,
            val totalCount: Int,
            val expectedMessage: String,
        )

        withData(
            nameFn = { (inputList, totalCount, expectedMessage) ->
                "Trying to choose $totalCount items from ${inputList.formatShort()} results into error '$expectedMessage'"
            },
            ChooseItemsErrorTestData(listOf("1", "2"), -1, "Cannot choose -1 items"),
            ChooseItemsErrorTestData(listOf(), 1, "Cannot choose 1 items from list of size 0"),
            ChooseItemsErrorTestData(listOf("a"), 2, "Cannot choose 2 items from list of size 1"),
        ) { (inputList, totalCount, expectedMessage) ->
            shouldThrow<IllegalArgumentException> {
                chooseItems(inputList, totalCount)
            } shouldHaveMessage expectedMessage
        }
    }
})