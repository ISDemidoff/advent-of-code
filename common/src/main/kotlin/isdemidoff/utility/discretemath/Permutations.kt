package isdemidoff.utility.discretemath

/**
 * Create all [permutations](https://en.wikipedia.org/wiki/Permutation) of a given list.
 *
 * **NB.** Time complexity is n factorial regarding size of [elements].
 */
fun <E> permutations(elements: List<E>): List<List<E>> = permutationsInner(elements)

private fun <E> permutationsInner(leftElements: List<E>, prevSeq: List<E> = listOf()): List<List<E>> =
    if (leftElements.isEmpty()) listOf(prevSeq) else leftElements.flatMap { permutationsInner(leftElements - it, prevSeq + it) }
