package isdemidoff.year2015

import isdemidoff.utility.solution.year.withArgs
import isdemidoff.utility.solution.year.withSameArgs
import isdemidoff.utility.solution.year.printYearSolution
import isdemidoff.year2015.day1.day1
import isdemidoff.year2015.day10.day10
import isdemidoff.year2015.day11.day11
import isdemidoff.year2015.day12.day12
import isdemidoff.year2015.day13.day13
import isdemidoff.year2015.day14.day14
import isdemidoff.year2015.day15.day15
import isdemidoff.year2015.day16.day16
import isdemidoff.year2015.day17.day17
import isdemidoff.year2015.day18.day18
import isdemidoff.year2015.day19.day19
import isdemidoff.year2015.day2.day2
import isdemidoff.year2015.day20.day20
import isdemidoff.year2015.day21.day21
import isdemidoff.year2015.day22.day22
import isdemidoff.year2015.day23.day23
import isdemidoff.year2015.day24.day24
import isdemidoff.year2015.day25.day25
import isdemidoff.year2015.day3.day3
import isdemidoff.year2015.day4.day4
import isdemidoff.year2015.day5.day5
import isdemidoff.year2015.day6.day6
import isdemidoff.year2015.day7.day7
import isdemidoff.year2015.day8.day8
import isdemidoff.year2015.day9.day9

fun main() = printYearSolution(
    2015,
    day1,
    day2,
    day3,
    day4.withArgs(listOf("0".repeat(5)), listOf("0".repeat(6))),
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
    // Time-consuming solution, skip
//    day20,
    day21,
    day22,
    day23.withSameArgs(listOf("b")),
    day24,
    day25,
)