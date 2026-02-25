@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.discretemath

/**
 * Produces a [factorization](https://en.wikipedia.org/wiki/Factorization) of a given [value].
 *
 * Resulting map has factors as keys and corresponding powers as values. Produces empty map for 1.
 */
fun getFactorization(value: Int): Map<Int, Int> {
    require(value > 0) { "Input value should be positive, but got $value" }
    val result = mutableMapOf<Int, Int>()

    fun addFactor(factor: Int) = result.compute(factor) { _, v -> (v ?: 0) + 1 }

    var rem = value
    while (rem > 1) {
        var i = 2
        while (i * i <= rem) {
            if (rem % i == 0) {
                addFactor(i)
                rem /= i
                continue
            }
            ++i
        }
        if (rem > 1) {
            addFactor(rem)
            break
        }
    }

    return result
}

/**
 * Collects all [divisors](https://en.wikipedia.org/wiki/Divisor) of a given [value].
 *
 * Uses [getFactorization] and [createAllCombinations] as helper functions to calculate faster.
 */
fun getDivisors(value: Int): List<Int> =
    if (value == 1) listOf(1) else createAllCombinations(getFactorization(value)).map { calculateNumberFromFactorization(it) }

/**
 * Just [getDivisors]'s result with total sum of elements.
 */
fun getSumOfDivisors(value: Int): Int = getDivisors(value).sum()

fun calculateNumberFromFactorization(factorization: Map<Int, Int>) =
    factorization.map { (factor, power) -> fastPow(factor, power) }.reduce(Int::times)
