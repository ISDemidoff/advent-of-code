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
import isdemidoff.adventofcode.year2016.day20.day20
import isdemidoff.adventofcode.year2016.day21.day21
import isdemidoff.adventofcode.year2016.day22.day22
import isdemidoff.adventofcode.year2016.day3.day3
import isdemidoff.adventofcode.year2016.day4.day4
import isdemidoff.adventofcode.year2016.day5.day5
import isdemidoff.adventofcode.year2016.day6.day6
import isdemidoff.adventofcode.year2016.day7.day7
import isdemidoff.adventofcode.year2016.day8.day8
import isdemidoff.adventofcode.year2016.day9.day9
import isdemidoff.solution.year.DisableReason
import isdemidoff.solution.year.YearSolutionConfig
import isdemidoff.solution.year.printYearSolution
import isdemidoff.solution.year.withArgs
import isdemidoff.solution.year.withNoArgs


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
    day10.withArgs(part1Args = listOf(61, 17), part2Args = listOf(0, 1, 2)),
    day11.withNoArgs().disablePart2(DisableReason.LONG_TIME),
    day12,
    day13,
    day14.withNoArgs().disablePart2(DisableReason.LONG_TIME),
    day15,
    day16.withArgs(part1Args = listOf(272), part2Args = listOf(35651584)).disablePart2(DisableReason.LONG_TIME),
    day17,
    day18.withArgs(part1Args = listOf(40), part2Args = listOf(400000)).disablePart2(DisableReason.LONG_TIME),
    day19,
    day20.withArgs(part2Args = listOf(UInt.MAX_VALUE.toULong())),
    day21.withArgs(part1Args = listOf("abcdefgh"), part2Args = listOf("fbgdceah")),
    day22,
)