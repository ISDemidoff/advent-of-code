package isdemidoff.year2015.day2

import isdemidoff.SimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day2.entity.WrappedBox
import isdemidoff.year2015.day2.entity.toWrappedBox

class Day2SolutionBuilder(day2Path: String) : SimpleSolutionBuilder<Int, List<WrappedBox>>(
    day2Path,
    { readLines(it).map { it.toWrappedBox() } },
    { it.sumOf { it.calculateWrappingNeeded() } },
)