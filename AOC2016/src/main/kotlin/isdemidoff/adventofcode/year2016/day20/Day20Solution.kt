package isdemidoff.adventofcode.year2016.day20

import isdemidoff.utility.solution.inputparser.functions.andThen
import isdemidoff.utility.solution.inputparser.scope.RangesInputParsers
import isdemidoff.utility.solution.solution
import isdemidoff.utility.solution.solver.solver

/**
 * [Day 20: Firewall Rules](https://adventofcode.com/2016/day/20).
 */
val day20 = solution(20) {
    inputParser = RangesInputParsers.uLongRanges andThen { it.sortedWith(compareBy<ULongRange> { it.first }.thenComparing { it.endInclusive }) }

    part1Solver = solver({
        "Lowest-valued accessible IP is $it."
    }) { ranges ->
        if (ranges.first().first > 0u.toULong()) {
            return@solver 0u.toULong()
        }
        var leftInterval: ULongRange = ranges.first()

        ranges.drop(1).forEach { range ->
            val nextCandidate = leftInterval.endInclusive + 1u
            if (range.first > nextCandidate) {
                return@solver nextCandidate
            } else if (range.endInclusive > leftInterval.endInclusive) {
                leftInterval = leftInterval.first..range.endInclusive
            }
        }

        error("Result not found")
    }

    part2Solver = solver<List<ULongRange>, ULong, ULong>({ res, _ ->
        "Total count of accessible IPs is $res."
    }) { ranges, upperBound ->
        var currentRange: ULongRange = ranges.first()
        var result = currentRange.first

        ranges.drop(1).forEach { range ->
            val nextCandidate = currentRange.endInclusive + 1u
            if (range.first > nextCandidate) {
                result += range.first - nextCandidate
                currentRange = range
            } else if (range.endInclusive > currentRange.endInclusive) {
                currentRange = currentRange.first..range.endInclusive
            }
        }

        return@solver result + upperBound - currentRange.endInclusive
    }
}