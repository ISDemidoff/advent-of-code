package isdemidoff.year2015.day2

import isdemidoff.RealSimpleSolutionBuilder
import isdemidoff.utility.input.readLines
import isdemidoff.year2015.day2.entity.WrappedBox
import isdemidoff.year2015.day2.entity.toWrappedBox

class Day2SolutionBuilder(day2Path: String) : RealSimpleSolutionBuilder<Int, List<WrappedBox>>(
    inputsDir = day2Path,
    inputParser = { readLines(it).map { it.toWrappedBox() } },
    solver = { it.sumOf { it.calculateWrappingNeeded() } },
)