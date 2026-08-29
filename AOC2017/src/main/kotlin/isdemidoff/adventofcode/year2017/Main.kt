package isdemidoff.adventofcode.year2017

import isdemidoff.adventofcode.year2017.day1.day1
import isdemidoff.adventofcode.year2017.day10.day10
import isdemidoff.adventofcode.year2017.day11.day11
import isdemidoff.adventofcode.year2017.day2.day2
import isdemidoff.adventofcode.year2017.day3.day3
import isdemidoff.adventofcode.year2017.day4.day4
import isdemidoff.adventofcode.year2017.day5.day5
import isdemidoff.adventofcode.year2017.day6.day6
import isdemidoff.adventofcode.year2017.day7.day7
import isdemidoff.adventofcode.year2017.day8.day8
import isdemidoff.adventofcode.year2017.day9.day9
import isdemidoff.solution.year.YearSolutionConfig
import isdemidoff.solution.year.printYearSolution
import isdemidoff.solution.year.withArgs

fun main() = printYearSolution(
    config = YearSolutionConfig(
        year = 2017,
        forceTimerEnabled = true,
    ),
    day1,
    day2,
    day3,
    day4,
    day5,
    day6,
    day7,
    day8,
    day9,
    day10.withArgs(part1Args = listOf(256)),
    day11,
)
