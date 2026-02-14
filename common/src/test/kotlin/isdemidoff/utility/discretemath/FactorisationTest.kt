package isdemidoff.utility.discretemath

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import io.kotest.property.Arb
import io.kotest.property.arbitrary.nonPositiveInt
import io.kotest.property.checkAll

class FactorisationTest : FreeSpec({
    "Check getFactorisation(Int)" - {
        withData(
            nameFn = { (input, result) ->
                "Number $input has factorisation ${result.formatFactors()}"
            },
            1 to emptyMap(),
            2 to mapOf(2 to 1),
            3 to mapOf(3 to 1),
            4 to mapOf(2 to 2),
            5 to mapOf(5 to 1),
            6 to mapOf(2 to 1, 3 to 1),
            7 to mapOf(7 to 1),
            8 to mapOf(2 to 3),
            9 to mapOf(3 to 2),
            10 to mapOf(2 to 1, 5 to 1),
            11 to mapOf(11 to 1),
            12 to mapOf(2 to 2, 3 to 1),
            13 to mapOf(13 to 1),
            14 to mapOf(2 to 1, 7 to 1),
            15 to mapOf(3 to 1, 5 to 1),
            16 to mapOf(2 to 4),
        ) { (input, result) ->
            getFactorization(input) shouldBe result
        }

        "Check for positive value" - {
            checkAll(Arb.nonPositiveInt()) {
                shouldThrow<IllegalArgumentException> {
                    getFactorization(it)
                } shouldHaveMessage "Input value should be positive, but got $it"
            }
        }
    }

    "Check getDivisors(Int)" - {
        withData(
            nameFn = { (input, result) ->
                "Number $input has divisors $result"
            },
            1 to listOf(1),
            2 to listOf(1, 2),
            3 to listOf(1, 3),
            4 to listOf(1, 2, 4),
            5 to listOf(1, 5),
            6 to listOf(1, 2, 3, 6),
            7 to listOf(1, 7),
            8 to listOf(1, 2, 4, 8),
            9 to listOf(1, 3, 9),
            10 to listOf(1, 2, 5, 10),
        ) { (input, result) ->
            getDivisors(input) shouldContainExactlyInAnyOrder result
        }
    }

    "Check getSumOfDivisors(Int)" - {
        withData(
            nameFn = { (input, result) ->
                "Number $input has sum of divisors $result"
            },
            1 to 1,
            2 to 3,
            3 to 4,
            4 to 7,
            5 to 6,
            6 to 12,
            7 to 8,
            8 to 15,
            9 to 13,
        ) { (input, result) ->
            getSumOfDivisors(input) shouldBe result
        }
    }
})

private fun Map<Int, Int>.formatFactors() =
    if (this.isEmpty()) "<no prime factors>" else
    this.entries.joinToString(separator = " * ") { (factor, power) -> "$factor^$power" }