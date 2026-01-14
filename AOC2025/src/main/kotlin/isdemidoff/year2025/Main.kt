package isdemidoff.year2025

import isdemidoff.utility.solution.year.SolutionData
import isdemidoff.utility.solution.year.YearSolution
import isdemidoff.year2025.day1.day1
import isdemidoff.year2025.day10.day10
import isdemidoff.year2025.day11.day11
import isdemidoff.year2025.day12.day12
import isdemidoff.year2025.day2.day2
import isdemidoff.year2025.day3.day3
import isdemidoff.year2025.day4.day4
import isdemidoff.year2025.day5.day5
import isdemidoff.year2025.day6.day6
import isdemidoff.year2025.day7.day7
import isdemidoff.year2025.day8.day8
import isdemidoff.year2025.day9.day9

fun main() {
    YearSolution(
        2025,
        SolutionData(day1),
        SolutionData(day2),
        SolutionData(day3),
        SolutionData(day4),
        SolutionData(day5),
        SolutionData(day6),
        SolutionData(day7),
        SolutionData(day8, listOf(1000)),
        SolutionData(day9),
        SolutionData(day10),
        SolutionData(day11),
        SolutionData(day12),
    ).printSolutions()
}