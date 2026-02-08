package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.SolutionBuilder

fun SolutionBuilder<*, *, *>.withNoArgs(): SolutionData = withSameArgs()

fun SolutionBuilder<*, *, *>.withSameArgs(args: List<Any> = emptyList()): SolutionData =
    withArgs(args, args)

fun SolutionBuilder<*, *, *>.withArgs(
    part1Args: List<Any> = emptyList(),
    part2Args: List<Any> = emptyList(),
): SolutionData = SolutionData(
    builder = this,
    part1Args = part1Args,
    part2Args = part2Args,
)