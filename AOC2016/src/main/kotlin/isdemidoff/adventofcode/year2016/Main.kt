package isdemidoff.adventofcode.year2016

import isdemidoff.adventofcode.year2016.day1.day1
import isdemidoff.adventofcode.year2016.day10.day10
import isdemidoff.adventofcode.year2016.day11.day11
import isdemidoff.adventofcode.year2016.day12.day12
import isdemidoff.adventofcode.year2016.day13.day13
import isdemidoff.adventofcode.year2016.day14.day14
import isdemidoff.adventofcode.year2016.day15.day15
import isdemidoff.adventofcode.year2016.day16.day16
import isdemidoff.adventofcode.year2016.day17.day17
import isdemidoff.adventofcode.year2016.day18.day18
import isdemidoff.adventofcode.year2016.day19.day19
import isdemidoff.adventofcode.year2016.day2.day2
import isdemidoff.adventofcode.year2016.day3.day3
import isdemidoff.adventofcode.year2016.day4.day4
import isdemidoff.adventofcode.year2016.day5.day5
import isdemidoff.adventofcode.year2016.day6.day6
import isdemidoff.adventofcode.year2016.day7.day7
import isdemidoff.adventofcode.year2016.day8.day8
import isdemidoff.adventofcode.year2016.day9.day9
import isdemidoff.utility.solution.year.DisableReason
import isdemidoff.utility.solution.year.YearSolutionConfig
import isdemidoff.utility.solution.year.printYearSolution
import isdemidoff.utility.solution.year.withArgs
import isdemidoff.utility.solution.year.withNoArgs


fun main() = printYearSolution(
    config = YearSolutionConfig(
        year = 2016,
        forceTimerEnabled = true,
//        forceShowAllSolutions = true,
    ),
    day1,
    day2,
    day3,
    day4,
    day5.withNoArgs().disableAll(DisableReason.LONG_TIME),
    day6,
    day7,
    day8,
    day9,
    day10.withArgs(listOf(61, 17), listOf(0, 1, 2)),
    day11.withNoArgs().disablePart2(DisableReason.LONG_TIME),
    day12,
    day13,
    day14.withNoArgs().disablePart2(DisableReason.LONG_TIME),
    day15,
    day16.withArgs(listOf(272), listOf(35651584)).disablePart2(DisableReason.LONG_TIME),
    day17,
    day18.withArgs(listOf(40), listOf(400000)).disablePart2(DisableReason.LONG_TIME),
    day19,
)