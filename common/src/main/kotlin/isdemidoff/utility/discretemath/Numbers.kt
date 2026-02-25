@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.discretemath

internal fun fastPow(base: Int, exponent: Int): Int {
    require(base >= 0) { "Base must be greater or equal to 0" }
    require(exponent >= 0) { "Exponent must be greater or equal to 0" }
    require(exponent != 0 || base != 0) { "Base and exponent can not be both 0" }
    if (base < 2 || exponent == 1) return base
    if (exponent == 0) return 1

    var multiplier = base
    var acc = 1
    var remainExponent = exponent
    while (remainExponent != 0) {
        if (remainExponent and 1 == 1) {
            acc *= multiplier
            check(acc > 0) { "Integer overflow" }
        }
        remainExponent = remainExponent shr 1
        multiplier *= multiplier
        check(multiplier > 0) { "Integer overflow" }
    }
    return acc
}

/**
 * Calculates the [greatest common divisor](https://en.wikipedia.org/wiki/Greatest_common_divisor) using factorization method.
 */
fun gcd(numbers: Iterable<Int>): Int {
    require(numbers.count() > 0) { "Cannot get gcd of zero numbers" }

    val factorizations = numbers.map { getFactorization(it) }

    val allKeys = factorizations.flatMap { it.keys }.toSet()
    return allKeys.map { it to reduceMapsForKey(factorizations, it, Math::min) }
        .toMap()
        .let { calculateNumberFromFactorization(it) }
}

/**
 * Calculates the [greatest common divisor](https://en.wikipedia.org/wiki/Greatest_common_divisor) using Euclid method.
 */
fun gcd(first: Int, second: Int): Int {
    var a = first
    var b = second
    while (a > 0 && b > 0) {
        if (a == b) return a
        if (a > b) a %= b else b %= a
    }
    return a + b
}

/**
 * Calculates the [least common multiple](https://en.wikipedia.org/wiki/Least_common_multiple) using factorization method.
 */
fun lcm(numbers: Iterable<Int>): Int {
    require(numbers.count() > 0) { "Cannot get lcm of zero numbers" }

    val factorizations = numbers.map { getFactorization(it) }

    val allKeys = factorizations.flatMap { it.keys }.toSet()
    return allKeys.map { it to reduceMapsForKey(factorizations, it, Math::max) }
        .toMap()
        .let { calculateNumberFromFactorization(it) }
}

/**
 * Calculates the [least common multiple](https://en.wikipedia.org/wiki/Least_common_multiple) using Euclid method.
 */
fun lcm(first: Int, second: Int): Int = first * second / gcd(first, second)

private fun reduceMapsForKey(maps: List<Map<Int, Int>>, key: Int, reduceOp: (Int, Int) -> Int): Int =
    maps.map { it[key] ?: 0 }.reduce(reduceOp)