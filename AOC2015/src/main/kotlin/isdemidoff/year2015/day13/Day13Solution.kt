package isdemidoff.year2015.day13

import isdemidoff.SimpleDeprecatedSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day13.entity.TableArrangement

/**
 * [Day 13: Knights of the Dinner Table](https://adventofcode.com/2015/day/13).
 */
class Day13SolutionBuilder(
    private val day13Path: String,
    private val addIgnorantMan: Boolean = false,
) : SimpleDeprecatedSolutionBuilder<Int, TableArrangement>(
    inputsDir = day13Path,
    inputParser = { TableArrangement(readLines(it), addIgnorantMan) },
    solver = { it.findBestSetup() },
) {
    fun shouldAddIgnorantMan(add: Boolean) = Day13SolutionBuilder(day13Path, add)
}