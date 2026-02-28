package isdemidoff.solution.year

import isdemidoff.solution.SolutionBuilder
import isdemidoff.solution.model.PartSolutionConfig

fun SolutionBuilder<*, *, *>.withNoArgs(): SolutionData = withSameArgs()

fun SolutionBuilder<*, *, *>.withSameArgs(args: List<Any> = emptyList()): SolutionData =
    withArgs(args, args)

fun SolutionBuilder<*, *, *>.withArgs(
    part1Args: List<Any> = emptyList(),
    part2Args: List<Any> = emptyList(),
): SolutionData = SolutionData(
    builder = this,
    part1Config = PartSolutionConfig(args = part1Args),
    part2Config = PartSolutionConfig(args = part2Args),
)
