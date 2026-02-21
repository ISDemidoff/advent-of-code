package isdemidoff.utility.discretemath

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import isdemidoff.utility.formatShort

class NumbersTest : FreeSpec({
    "Check fastPow(Int, Int)" - {
        data class PowInputData(val base: Int, val exponent: Int, val result: Int)

        withData(
            nameFn = { (base, exponent, result) ->
                "$base in power of $exponent equals $result"
            },
            PowInputData(2, 1, 2),
            PowInputData(2, 2, 4),
            PowInputData(2, 3, 8),
            PowInputData(3, 3, 27),
            PowInputData(1, 123456789, 1),
            PowInputData(0, 123456789, 0),
            PowInputData(123456789, 1, 123456789),
            PowInputData(123456789, 0, 1),
        ) { (base, exponent, result) ->
            fastPow(base, exponent) shouldBe result
        }

        data class PowInputErrorData(val base: Int, val exponent: Int, val message: String)

        withData(
            nameFn = { (base, exponent, message) ->
                "$base in power of $exponent fails with $message"
            },
            PowInputErrorData(-1, 0, "Base must be greater or equal to 0"),
            PowInputErrorData(0, -1, "Exponent must be greater or equal to 0"),
            PowInputErrorData(0, 0, "Base and exponent can not be both 0"),
        ) { (base, exponent, message) ->
            shouldThrow<IllegalArgumentException> { fastPow(base, exponent) } shouldHaveMessage message
        }

        "Catch overflow" {
            shouldThrow<IllegalStateException> { fastPow(5, 124) } shouldHaveMessage "Integer overflow"
        }
    }

    "Check gcd(Iterable<Int>)" - {
        withData(
            nameFn = { (numbers, result) ->
                "gcd of ${numbers.formatShort(5)} is $result"
            },
            listOf(2, 4, 6) to 2,
            listOf(3, 2, 5, 6) to 1,
            listOf(245) to 245,
            listOf(10, 20, 30, 40, 50) to 10,
        ) { (numbers, result) ->
            gcd(numbers) shouldBe result
        }
    }

    "Check gcd(Int, Int)" - {
        data class GcdInputData(val first: Int, val second: Int, val result: Int)

        withData(
            nameFn = { (first, second, result) ->
                "gcd of $first and $second is $result"
            },
            GcdInputData(1, 2, 1),
            GcdInputData(8, 2, 2),
            GcdInputData(54, 24, 6),
        ) { (first, second, result) ->
            gcd(first, second) shouldBe result
        }
    }

    "Check lcm(Iterable<Int>)" - {
        withData(
            nameFn = { (numbers, result) ->
                "lcm of ${numbers.formatShort(5)} is $result"
            },
            listOf(2, 4, 6) to 12,
            listOf(3, 2, 5, 6) to 30,
            listOf(245) to 245,
            listOf(10, 20, 30, 40, 50) to 600,
        ) { (numbers, result) ->
            lcm(numbers) shouldBe result
        }
    }

    "Check lcm(Int, Int)" - {
        data class LcmInputData(val first: Int, val second: Int, val result: Int)

        withData(
            nameFn = { (first, second, result) ->
                "lcm of $first and $second is $result"
            },
            LcmInputData(1, 2, 2),
            LcmInputData(8, 2, 8),
            LcmInputData(54, 24, 216),
        ) { (first, second, result) ->
            lcm(first, second) shouldBe result
        }
    }
})