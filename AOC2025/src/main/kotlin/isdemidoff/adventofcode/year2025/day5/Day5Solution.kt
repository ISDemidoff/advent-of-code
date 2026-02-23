package isdemidoff.adventofcode.year2025.day5

import isdemidoff.utility.parsing.toLongRanges
import isdemidoff.utility.parsing.toLongsList
import isdemidoff.utility.solution.inputparser.functions.mapFirst
import isdemidoff.utility.solution.inputparser.functions.mapSecond
import isdemidoff.utility.solution.inputparser.scope.StringsInputParsers
import isdemidoff.utility.solution.solution

/**
 * [Day 5: Cafeteria](https://adventofcode.com/2025/day/5).
 */
val day5 = solution<Pair<List<LongRange>, List<Long>>, Int>(5) {
//    inputParser = StringsInputParsers.twoBlocksParser { (ranges, ids) -> toLongRanges(ranges) to ids.toLongsList() }
    inputParser = StringsInputParsers.twoBlocks
        .mapFirst { toLongRanges(it) }
        .mapSecond { toLongsList(it) }

    part1Solver = solver({ "There are $it fresh ingredients." }) { (ranges, ids) ->
        ids.count { id -> ranges.any { id in it } }
    }
}