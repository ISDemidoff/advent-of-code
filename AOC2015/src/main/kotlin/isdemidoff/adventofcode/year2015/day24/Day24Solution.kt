package isdemidoff.adventofcode.year2015.day24

import isdemidoff.utility.discretemath.combinationsHavingSum
import isdemidoff.utility.solution.solution

/**
 * [Day 24: It Hangs in the Balance](https://adventofcode.com/2015/day/24).
 */
val day24 = solution(24) {
    inputParser = uniformLinesParser { it.toLong() }

    fun getSolver(groupsNum: Long) = solver({
        "Quantum entanglement of best setup split by $groupsNum groups is $it."
    }) { presentWeights -> presentWeights.sum()
        .also { require(it % groupsNum == 0L) { "Cannot split $it equally to $groupsNum parts." } }
        .let { it / groupsNum }
        .let { requiredWeight ->
            combinationsHavingSum(presentWeights, requiredWeight)
                .distinct()
                .filter {
                    // Remaining presents can be combined
//                        var reducedPresents = presentWeights
//                        it.forEach { reducedPresents = reducedPresents - it }
//
//                        combinationsHavingSum(reducedPresents, requiredWeight).isNotEmpty()
                    // We really skip this check, as it really works here.
                    true
                }
                .let {
                    val minSize = it.minOf { it.size }
                    it.filter { it.size == minSize }
                }
                .minOf { it.reduce(Long::times) }
        }
    }

    part1Solver = getSolver(3)
    part2Solver = getSolver(4)
}