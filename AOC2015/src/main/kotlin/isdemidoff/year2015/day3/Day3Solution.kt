package isdemidoff.year2015.day3

import isdemidoff.SingleLineDeprecatedSolution
import isdemidoff.SingleLineDeprecatedSolutionBuilder
import isdemidoff.year2015.day3.entity.DeliveryResults
import isdemidoff.year2015.day3.entity.totalVisitedPoints

class Day3Solution(input: String, numberOfCouriers: Int = 1) : SingleLineDeprecatedSolution<Int>(
    input = input,
    solution = { instruction ->
        (0..<numberOfCouriers).map { courierIndex ->
            DeliveryResults()
                .apply { traversePath(instruction.filterIndexed { index, _ -> index % numberOfCouriers == courierIndex }) }
        }.totalVisitedPoints()
    },
) {
    init {
        require(numberOfCouriers > 0) { "Number of couriers must be a natural number" }
    }
}

/**
 * [Day 3: Perfectly Spherical Houses in a Vacuum](https://adventofcode.com/2015/day/3).
 */
class Day3SolutionBuilder(
    private val day3Path: String,
    private val numberOfCouriers: Int = 1,
) : SingleLineDeprecatedSolutionBuilder<Int>(
    inputsDir = day3Path,
    deprecatedSolutionSupplier = { Day3Solution(it, numberOfCouriers) },
) {
    init {
        require(numberOfCouriers > 0) { "Number of couriers must be a natural number" }
    }

    fun forNumberOfCouriers(count: Int) = Day3SolutionBuilder(day3Path, count)
}