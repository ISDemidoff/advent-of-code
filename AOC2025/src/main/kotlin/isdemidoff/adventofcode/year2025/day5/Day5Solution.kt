package isdemidoff.adventofcode.year2025.day5

import isdemidoff.solution.inputparser.functions.mapFirst
import isdemidoff.solution.inputparser.functions.mapSecond
import isdemidoff.solution.inputparser.scope.twoBlocks
import isdemidoff.solution.solution
import isdemidoff.utility.parsing.toLongRanges
import isdemidoff.utility.parsing.toLongsList

/**
 * [Day 5: Cafeteria](https://adventofcode.com/2025/day/5).
 */
val day5 = solution<Pair<List<LongRange>, List<Long>>, Int>(5) {
//    inputParser = StringsInputParsers.twoBlocksParser { (ranges, ids) -> toLongRanges(ranges) to ids.toLongsList() }
    inputParser = twoBlocks
        .mapFirst { toLongRanges(it) }
        .mapSecond { toLongsList(it) }

    part1Solver = solver({ "There are $it fresh ingredients." }) { (ranges, ids) ->
        ids.count { id -> ranges.any { id in it } }
    }
}
