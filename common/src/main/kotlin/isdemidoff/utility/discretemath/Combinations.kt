package isdemidoff.utility.discretemath

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

fun combinationsHavingSum(availableTerms: List<Int>, totalSum: Int) =
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

fun <E> createAllChoices(elements: Map<E, Int>): List<Map<E, Int>> = elements.takeUnless { it.isEmpty() }
    ?.let { allChoicesInner(it) } ?: emptyList()

private fun <E> allChoicesInner(
    elements: Map<E, Int>,
    alreadyCombined: Map<E, Int> = emptyMap(),
): List<Map<E, Int>> {
    if (elements.isEmpty()) return listOf(alreadyCombined)

    val nextElement = elements.entries.first()
    require(nextElement.value >= 0) { "Cannot combine negative count, occurred at key '${nextElement.key}'" }

    return (0..nextElement.value).flatMap {
        allChoicesInner(
            elements = (elements - nextElement.key),
            alreadyCombined = alreadyCombined + (nextElement.key to it),
        )
    }
}

infix fun <E> List<E>.chooseItemsCount(count: Int): List<List<E>> = chooseItems(this, count)

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

infix fun <E> Set<E>.chooseItemsCount(count: Int): List<Set<E>> = chooseItems(this, count)

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