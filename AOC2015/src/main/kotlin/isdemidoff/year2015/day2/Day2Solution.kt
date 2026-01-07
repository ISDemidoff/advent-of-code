package isdemidoff.year2015.day2

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day2.entity.WrappedBox
import isdemidoff.year2015.day2.entity.toWrappedBox

/**
 * [Day 2: I Was Told There Would Be No Math](https://adventofcode.com/2015/day/2).
 */
class Day2SolutionBuilder(day2Path: String) : SimpleSolutionBuilder<Int, List<WrappedBox>>(
    inputsDir = day2Path,
    inputParser = { readLines(it).map { it.toWrappedBox() } },
    solver = { it.sumOf { it.calculateWrappingNeeded() } },
)