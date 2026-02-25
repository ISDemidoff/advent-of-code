package isdemidoff.adventofcode.year2015

import isdemidoff.adventofcode.year2015.day1.day1
import isdemidoff.adventofcode.year2015.day10.day10
import isdemidoff.adventofcode.year2015.day11.day11
import isdemidoff.adventofcode.year2015.day12.day12
import isdemidoff.adventofcode.year2015.day13.day13
import isdemidoff.adventofcode.year2015.day14.day14
import isdemidoff.adventofcode.year2015.day15.day15
import isdemidoff.adventofcode.year2015.day16.day16
import isdemidoff.adventofcode.year2015.day17.day17
import isdemidoff.adventofcode.year2015.day18.day18
import isdemidoff.adventofcode.year2015.day19.day19
import isdemidoff.adventofcode.year2015.day2.day2
import isdemidoff.adventofcode.year2015.day20.day20
import isdemidoff.adventofcode.year2015.day21.day21
import isdemidoff.adventofcode.year2015.day22.day22
import isdemidoff.adventofcode.year2015.day23.day23
import isdemidoff.adventofcode.year2015.day24.day24
import isdemidoff.adventofcode.year2015.day25.day25
import isdemidoff.adventofcode.year2015.day3.day3
import isdemidoff.adventofcode.year2015.day4.day4
import isdemidoff.adventofcode.year2015.day5.day5
import isdemidoff.adventofcode.year2015.day6.day6
import isdemidoff.adventofcode.year2015.day7.day7
import isdemidoff.adventofcode.year2015.day8.day8
import isdemidoff.adventofcode.year2015.day9.day9
import isdemidoff.solution.year.DisableReason
import isdemidoff.solution.year.YearSolutionConfig
import isdemidoff.solution.year.printYearSolution
import isdemidoff.solution.year.withArgs
import isdemidoff.solution.year.withNoArgs
import isdemidoff.solution.year.withSameArgs

fun main() = printYearSolution(
    config = YearSolutionConfig(
        year = 2015,
        forceTimerEnabled = true,
    ),
    day1,
    day2,
    day3,
    day4.withArgs(
        part1Args = listOf("0".repeat(5)),
        part2Args = listOf("0".repeat(6)),
    ).disablePart2(DisableReason.LONG_TIME),
    day5,
    day6,
    day7,
    day8,
    day9,
    day10,
    day11,
    day12,
    day13,
    day14.withSameArgs(listOf(2503)),
    day15,
    day16,
    day17.withSameArgs(listOf(150)),
    day18.withSameArgs(listOf(100)),
    day19,
    day20.withNoArgs().disableAll(DisableReason.LONG_TIME),
    day21,
    day22,
    day23.withSameArgs(listOf("b")),
    day24.withNoArgs().disableAll(DisableReason.LONG_TIME),
    day25,
)