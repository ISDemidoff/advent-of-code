package isdemidoff.adventofcode.year2025

import isdemidoff.adventofcode.year2025.day1.day1
import isdemidoff.adventofcode.year2025.day10.day10
import isdemidoff.adventofcode.year2025.day11.day11
import isdemidoff.adventofcode.year2025.day12.day12
import isdemidoff.adventofcode.year2025.day2.day2
import isdemidoff.adventofcode.year2025.day3.day3
import isdemidoff.adventofcode.year2025.day4.day4
import isdemidoff.adventofcode.year2025.day5.day5
import isdemidoff.adventofcode.year2025.day6.day6
import isdemidoff.adventofcode.year2025.day7.day7
import isdemidoff.adventofcode.year2025.day8.day8
import isdemidoff.adventofcode.year2025.day9.day9
import isdemidoff.solution.year.YearSolutionConfig
import isdemidoff.solution.year.printYearSolution
import isdemidoff.solution.year.withSameArgs

fun main() = printYearSolution(
    config = YearSolutionConfig(
        year = 2025,
        forceTimerEnabled = true,
    ),
    day1,
    day2,
    day3,
    day4,
    day5,
    day6,
    day7,
    day8.withSameArgs(listOf(1000)),
    day9,
    day10,
    day11,
    day12,
)