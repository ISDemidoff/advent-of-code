package isdemidoff.utility.solution

data class SolutionData(
    val builder: SolutionBuilder<*, *, *>,
    val part1Args: List<Int> = emptyList(),
    val part2Args: List<Int> = emptyList(),
)