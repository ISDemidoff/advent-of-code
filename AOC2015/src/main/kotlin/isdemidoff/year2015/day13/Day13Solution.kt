package isdemidoff.year2015.day13

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day13.entity.TableArrangement

/**
 * [Day 13: Knights of the Dinner Table](https://adventofcode.com/2015/day/13).
 */
class Day13SolutionBuilder(day13Path: String) : SimpleSolutionBuilder<Int, TableArrangement>(
    inputsDir = day13Path,
    inputParser = { TableArrangement(readLines(it)) },
    solver = { it.findBestSetup() },
)