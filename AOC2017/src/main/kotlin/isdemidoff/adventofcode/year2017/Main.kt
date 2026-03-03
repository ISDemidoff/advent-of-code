package isdemidoff.adventofcode.year2017

import isdemidoff.adventofcode.year2017.day1.day1
import isdemidoff.adventofcode.year2017.day2.day2
import isdemidoff.adventofcode.year2017.day3.day3
import isdemidoff.adventofcode.year2017.day4.day4
import isdemidoff.adventofcode.year2017.day5.day5
import isdemidoff.adventofcode.year2017.day6.day6
import isdemidoff.adventofcode.year2017.day7.day7
import isdemidoff.solution.year.YearSolutionConfig
import isdemidoff.solution.year.printYearSolution

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
)
