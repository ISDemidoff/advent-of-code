package isdemidoff.utility.discretemath

/**
 * Create all permutations of a given list. Note that complexity is n factorial, so time spent is huge.
 */
fun <E> List<E>.permutations(): List<List<E>> = permutationsInner()

private fun <E> List<E>.permutationsInner(prevSeq: List<E> = listOf()): List<List<E>> =
    if (isEmpty()) listOf(prevSeq) else flatMap { (this - it).permutationsInner(prevSeq + it) }