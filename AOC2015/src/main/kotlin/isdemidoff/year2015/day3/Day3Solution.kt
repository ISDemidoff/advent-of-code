package isdemidoff.year2015.day3

import isdemidoff.SingleLineSolution
import isdemidoff.SingleLineSolutionBuilder
import isdemidoff.year2015.day3.entity.DeliveryResults

class Day3Solution(input: String) : SingleLineSolution<Int>(
    input = input,
    solution = {
        DeliveryResults()
            .apply { traversePath(it) }
            .getNumberOfVisitedPoints()
    },
)

/**
 * [Day 3: Perfectly Spherical Houses in a Vacuum](https://adventofcode.com/2015/day/3).
 */
class Day3SolutionBuilder(day3Path: String) : SingleLineSolutionBuilder<Int>(
    inputsDir = day3Path,
    solutionSupplier = { Day3Solution(it) },
)