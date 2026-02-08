package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.SolutionBuilder
import isdemidoff.utility.solution.SolutionWithInputBuilder

data class SolutionData(
    val builder: SolutionBuilder<*, *, *>,
    val part1Args: List<Any> = emptyList(),
    val part1DisableReason: String? = null,
    val part2Args: List<Any> = emptyList(),
    val part2DisableReason: String? = null,
) {
    fun disablePart1(reason: String): SolutionData = copy(part1DisableReason = reason)
    fun disablePart2(reason: String): SolutionData = copy(part2DisableReason = reason)
    fun disable(reason: String): SolutionData = copy(part1DisableReason = reason, part2DisableReason = reason)

    fun buildPart1Context() = SolutionWithInputBuilder.SolutionContext(disableReason = part1DisableReason, args = part1Args)
    fun buildPart2Context() = SolutionWithInputBuilder.SolutionContext(disableReason = part2DisableReason, args = part2Args)
}
