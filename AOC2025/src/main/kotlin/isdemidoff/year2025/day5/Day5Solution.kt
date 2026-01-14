package isdemidoff.year2025.day5

import isdemidoff.utility.solution.solution
import isdemidoff.utility.toLongRanges
import isdemidoff.utility.toLongsList

/**
 * [Day 5: Cafeteria](https://adventofcode.com/2025/day/5).
 */
val day5 = solution<Pair<List<LongRange>, List<Long>>, Int>(5) {
    inputParser = twoBlocksParser { (ranges, ids) -> ranges.toLongRanges() to ids.toLongsList() }

    part1Solver = solver({ "There are $it fresh ingredients." }) { (ranges, ids) ->
        ids.count { id -> ranges.any { id in it } }
    }
}