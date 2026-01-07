package isdemidoff.year2025.day5

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readTwoBlocks
import isdemidoff.utility.toLongRanges
import isdemidoff.utility.toLongsList

class Day5SolutionBuilder(day5Path: String) : SimpleSolutionBuilder<Int, Pair<List<LongRange>, List<Long>>>(
    inputsDir = day5Path,
    inputParser = { readTwoBlocks(it).let { (ranges, ids) -> ranges.toLongRanges() to ids.toLongsList() } },
    solver = { (ranges, ids) -> ids.count { id -> ranges.any { id in it } } },
)