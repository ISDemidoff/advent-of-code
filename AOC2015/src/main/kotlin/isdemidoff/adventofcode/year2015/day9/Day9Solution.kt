package isdemidoff.adventofcode.year2015.day9

import isdemidoff.adventofcode.year2015.day9.entity.Country
import isdemidoff.utility.solution.solution

/**
 * [Day 9: All in a Single Night](https://adventofcode.com/2015/day/9).
 */
val day9 = solution(9) {
    inputParser = singleBlockParser(::Country)

    part1Solver = solver({ "Shortest route distance is $it" }) { it.findShortestPath() }
    part2Solver = solver({ "Longest route distance is $it" }) { it.findLongestPath() }
}