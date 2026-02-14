package isdemidoff.utility.discretemath

/**
 * Calculates exact all the [combinations with repetitions](https://en.wikipedia.org/wiki/Combination#Number_of_combinations_with_repetition).
 *
 * **NB.** has factorial (?) growth rate in size with both [totalCount] and size of [elements].
 */
fun <E> combinationsWithRepetitions(elements: List<E>, totalCount: Int): List<Map<E, Int>> =
    combinationsWithRepetitionsInner(elements, totalCount.also { require(it >= 0) { "Cannot combine to negative total sum." } })

private fun <E> combinationsWithRepetitionsInner(
    elements: List<E>,
    totalSum: Int,
    alreadyCombined: Map<E, Int> = emptyMap(),
) : List<Map<E, Int>> {
    if (elements.isEmpty()) {
        if (totalSum == 0) return emptyList()
        throw IllegalArgumentException("Cannot combine empty list to positive sum.")
    }

    if (elements.size == 1) return listOf(alreadyCombined + mapOf(elements.single() to totalSum))

    val nextElement = elements.first()
    return (0..totalSum).flatMap {
        combinationsWithRepetitionsInner(
            elements = elements - nextElement,
            totalSum = totalSum - it,
            alreadyCombined = alreadyCombined + (nextElement to it),
        )
    }
}

/**
 * Finds all combinations of non-unique input [availableTerms] which have sum of [totalSum].
 *
 * In general, this algo has exponential time complexity regarding size of [availableTerms].
 */
fun combinationsHavingSum(availableTerms: List<Int>, totalSum: Int): List<List<Int>> =
    combinationsHavingSumInner(availableTerms.sorted(), totalSum)

private fun combinationsHavingSumInner(
    availableTerms: List<Int>,
    totalSum: Int,
    takenTerms: List<Int> = listOf(),
): List<List<Int>> {
    if (totalSum == 0) {
        return listOf(takenTerms)
    } else if (totalSum < 0) {
        return emptyList()
    }

    if (availableTerms.isEmpty()) return emptyList()

    return availableTerms.flatMapIndexed { index, v ->
        combinationsHavingSumInner(
            availableTerms.subList(index + 1, availableTerms.size),
            totalSum - v,
            takenTerms + v,
        )
    }
}

/**
 * [Long] variation of previous algo.
 */
fun combinationsHavingSum(availableTerms: List<Long>, totalSum: Long) =
    combinationsHavingSumInner(availableTerms.sorted(), totalSum)

private fun combinationsHavingSumInner(
    availableTerms: List<Long>,
    totalSum: Long,
    takenTerms: List<Long> = listOf(),
): List<List<Long>> {
    if (totalSum == 0L) {
        return listOf(takenTerms)
    } else if (totalSum < 0) {
        return emptyList()
    }

    if (availableTerms.isEmpty()) return emptyList()

    return availableTerms.flatMapIndexed { index, v ->
        combinationsHavingSumInner(
            availableTerms.subList(index + 1, availableTerms.size),
            totalSum - v,
            takenTerms + v,
        )
    }
}

/**
 * Creates all possible choices of elements types (aka given map keys) which can contain from 0 to fixed number
 * of elements (aka given map values).
 *
 * Number of such combinations can be calculated by increasing every map value by 1 and multiplying results.
 */
fun <E> createAllCombinations(elements: Map<E, Int>): List<Map<E, Int>> = elements.takeUnless { it.isEmpty() }
    ?.let { allCombinationsInner(it) } ?: emptyList()

private fun <E> allCombinationsInner(
    elements: Map<E, Int>,
    alreadyCombined: Map<E, Int> = emptyMap(),
): List<Map<E, Int>> {
    if (elements.isEmpty()) return listOf(alreadyCombined)

    val nextElement = elements.entries.first()
    require(nextElement.value >= 0) { "Cannot combine negative count, occurred at key '${nextElement.key}'" }

    return (0..nextElement.value).flatMap {
        allCombinationsInner(
            elements = (elements - nextElement.key),
            alreadyCombined = alreadyCombined + (nextElement.key to it),
        )
    }
}

/**
 * Produces [count]-combinations of given [elements]. More can be found on [wiki](https://en.wikipedia.org/wiki/Combination).
 */
fun <E> chooseItems(elements: Set<E>, count: Int): List<Set<E>> {
    require(count >=0) { "Cannot choose $count items" }
    require(count <= elements.size) { "Cannot choose $count items from list of size ${elements.size}" }
    return chooseItemsInner(elements, count)
}

private fun <E> chooseItemsInner(
    elements: Set<E>,
    count: Int,
    alreadyChosen: Set<E> = emptySet(),
): List<Set<E>> {
    if (count == 0) return listOf(alreadyChosen)

    return elements.flatMap {
        chooseItemsInner(
            elements = elements - it,
            count = count - 1,
            alreadyChosen = alreadyChosen + it,
        )
    }
}

/**
 * Infix variant of function [chooseItems].
 */
infix fun <E> Set<E>.chooseItemsCount(count: Int): List<Set<E>> = chooseItems(this, count)

/**
 * [List] variant of previous function, can be used for non-unique elements.
 */
fun <E> chooseItems(elements: List<E>, count: Int): List<List<E>> {
    require(count >=0) { "Cannot choose $count items" }
    require(count <= elements.size) { "Cannot choose $count items from list of size ${elements.size}" }
    return chooseItemsInner(elements, count)
}

private fun <E> chooseItemsInner(
    elements: List<E>,
    count: Int,
    alreadyChosen: List<E> = emptyList(),
): List<List<E>> {
    if (count == 0) return listOf(alreadyChosen)

    return elements.flatMap {
        chooseItemsInner(
            elements = elements - it,
            count = count - 1,
            alreadyChosen = alreadyChosen + it,
        )
    }
}

/**
 * Infix variant of function [chooseItems].
 */
infix fun <E> List<E>.chooseItemsCount(count: Int): List<List<E>> = chooseItems(this, count)