package isdemidoff.year2015.day17.entity

class SumCombiner(ints: List<Int>) {
    private val terms = ints.sorted()

    fun findCombinations(totalSum: Int): List<List<Int>> {
        return findCombinations(availableTerms = terms, totalSum = totalSum)
    }

    private fun findCombinations(
        availableTerms: List<Int>,
        takenTerms: List<Int> = listOf(),
        totalSum: Int,
    ): List<List<Int>> {
        if (totalSum == 0) {
            return listOf(takenTerms)
        } else if (totalSum < 0) {
            return emptyList()
        }

        if (availableTerms.isEmpty()) return emptyList()

        return availableTerms.flatMapIndexed { index, v ->
            findCombinations(
                availableTerms.subList(index + 1, availableTerms.size),
                takenTerms + v,
                totalSum - v,
            )
        }
    }
}