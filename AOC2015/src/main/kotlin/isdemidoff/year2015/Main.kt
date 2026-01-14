package isdemidoff.year2015

import isdemidoff.utility.solution.year.SolutionData
import isdemidoff.utility.solution.year.YearSolution
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
import isdemidoff.year2015.day3.day3
import isdemidoff.year2015.day4.day4
import isdemidoff.year2015.day5.day5
import isdemidoff.year2015.day6.day6
import isdemidoff.year2015.day7.day7
import isdemidoff.year2015.day8.day8
import isdemidoff.year2015.day9.day9

fun main() {
    YearSolution(
        2015,
        SolutionData(day1),
        SolutionData(day2),
        SolutionData(day3),
        SolutionData(day4, listOf("0".repeat(5)), listOf("0".repeat(6))),
        SolutionData(day5),
        SolutionData(day6),
        SolutionData(day7),
        SolutionData(day8),
        SolutionData(day9),
        SolutionData(day10),
        SolutionData(day11),
        SolutionData(day12),
        SolutionData(day13),
        SolutionData(day14, listOf(2503), listOf(2503)),
        SolutionData(day15),
        SolutionData(day16),
        SolutionData(day17, listOf(150), listOf(150)),
        SolutionData(day18, listOf(100), listOf(100)),
        SolutionData(day19),
    ).printSolutions()
}