package isdemidoff.utility.discretemath

infix fun <E> List<E>.combinationsWithSum(totalSum: Int): List<Map<E, Int>> =
    combinations(this, totalSum)

fun <E> combinations(elements: List<E>, totalSum: Int): List<Map<E, Int>> =
    elements.combinationsInner(totalSum.also { require(it >= 0) { "Cannot combine to negative total sum." } })

private fun <E> List<E>.combinationsInner(
    totalSum: Int,
    alreadyCombined: Map<E, Int> = emptyMap(),
) : List<Map<E, Int>> {
    if (this.isEmpty()) {
        if (totalSum == 0) return emptyList()
        throw IllegalArgumentException("Cannot combine empty list to positive sum.")
    }

    if (size == 1) return listOf(alreadyCombined + mapOf(this.single() to totalSum))

    val nextElement = this.first()
    return (0..totalSum).flatMap {
        (this - nextElement).combinationsInner(
            totalSum = totalSum - it,
            alreadyCombined = alreadyCombined + (nextElement to it),
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