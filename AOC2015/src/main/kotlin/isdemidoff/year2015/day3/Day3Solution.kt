package isdemidoff.year2015.day3

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.Solution
import isdemidoff.utility.input.readSingleLine
import isdemidoff.year2015.day3.entity.DeliveryResults

class Day3Solution(val input: String) : Solution<Int> {
    override fun solve() = DeliveryResults()
        .apply { traversePath(input) }
        .getNumberOfVisitedPoints()
}

class Day3SolutionBuilder(day3Path: String) : SimpleSolutionBuilder<Int, String>(
    day3Path,
    { readSingleLine(it) },
    { Day3Solution(it).solve() },
)