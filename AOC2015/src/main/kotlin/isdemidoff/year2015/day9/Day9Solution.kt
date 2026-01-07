package isdemidoff.year2015.day9

import isdemidoff.RealSimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day9.entity.Country

class Day9SolutionBuilder(day9Path: String) : RealSimpleSolutionBuilder<Int, Country>(
    inputsDir = day9Path,
    inputParser = { Country(readLines(it)) },
    solver = { it.findShortestPath() },
)