package isdemidoff.year2015.day9

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day9.entity.Country

class Day9SolutionBuilder(day9Path: String) : SimpleSolutionBuilder<Int, Country>(
    day9Path,
    { Country(readLines(it)) },
    { it.findShortestPath() },
)