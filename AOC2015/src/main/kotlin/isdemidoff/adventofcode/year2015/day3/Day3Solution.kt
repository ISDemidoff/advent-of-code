package isdemidoff.adventofcode.year2015.day3

import isdemidoff.adventofcode.year2015.day3.entity.DeliveryResults
import isdemidoff.adventofcode.year2015.day3.entity.totalVisitedPoints
import isdemidoff.utility.solution.solution

/**
 * [Day 3: Perfectly Spherical Houses in a Vacuum](https://adventofcode.com/2015/day/3).
 */
val day3 = solution(3) {
    inputParser = singleLineParser { it }

    fun solverProvider(numberOfCouriers: Int) = solver({
        "$numberOfCouriers courier(s) visited total of $it houses."
    }) { instruction ->
        require(numberOfCouriers > 0) { "Number of couriers must be a positive number" }

        (0..<numberOfCouriers).map { courierIndex ->
            DeliveryResults()
                .apply { traversePath(instruction.filterIndexed { index, _ -> index % numberOfCouriers == courierIndex }) }
        }.totalVisitedPoints()
    }

    part1Solver = solverProvider(1)
    part2Solver = solverProvider(2)
}