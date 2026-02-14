package isdemidoff.adventofcode.year2016

import isdemidoff.adventofcode.year2016.day1.day1
import isdemidoff.adventofcode.year2016.day10.day10
import isdemidoff.adventofcode.year2016.day11.day11
import isdemidoff.adventofcode.year2016.day12.day12
import isdemidoff.adventofcode.year2016.day2.day2
import isdemidoff.adventofcode.year2016.day3.day3
import isdemidoff.adventofcode.year2016.day4.day4
import isdemidoff.adventofcode.year2016.day5.day5
import isdemidoff.adventofcode.year2016.day6.day6
import isdemidoff.adventofcode.year2016.day7.day7
import isdemidoff.adventofcode.year2016.day8.day8
import isdemidoff.adventofcode.year2016.day9.day9
import isdemidoff.utility.solution.year.printYearSolution
import isdemidoff.utility.solution.year.withArgs
import isdemidoff.utility.solution.year.withNoArgs


fun main() = printYearSolution(
    2016,
    day1,
    day2,
    day3,
    day4,
    day5,
    day6,
    day7,
    day8,
    day9,
    day10.withArgs(listOf(61, 17), listOf(0, 1, 2)),
    day11.withNoArgs().disablePart2("It takes a long time to complete"),
    day12,
)