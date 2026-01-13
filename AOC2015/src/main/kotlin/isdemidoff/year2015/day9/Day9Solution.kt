package isdemidoff.year2015.day9

import isdemidoff.SimpleDeprecatedSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day9.entity.Country

/**
 * [Day 9: All in a Single Night](https://adventofcode.com/2015/day/9).
 */
class Day9SolutionBuilder(day9Path: String) : SimpleDeprecatedSolutionBuilder<Pair<Int, Int>, Country>(
    inputsDir = day9Path,
    inputParser = { Country(readLines(it)) },
    solver = { it.findShortestPath() to it.findLongestPath() },
)