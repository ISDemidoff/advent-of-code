package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.SolutionBuilder

fun SolutionBuilder<*, *, *>.withNoArgs() = withSameArgs()

fun SolutionBuilder<*, *, *>.withSameArgs(args: List<Any> = emptyList()) =
    withArgs(args, args)

fun SolutionBuilder<*, *, *>.withArgs(
    part1Args: List<Any> = emptyList(),
    part2Args: List<Any> = emptyList(),
) = SolutionData(this, part1Args, part2Args)