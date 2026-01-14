package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.SolutionBuilder

data class SolutionData(
    val builder: SolutionBuilder<*, *, *>,
    val part1Args: List<Any> = emptyList(),
    val part2Args: List<Any> = emptyList(),
)