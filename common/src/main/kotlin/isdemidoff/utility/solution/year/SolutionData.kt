package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.SolutionBuilder
import isdemidoff.utility.solution.model.PartSolutionConfig

data class SolutionData(
    val builder: SolutionBuilder<*, *, *>,
    val part1Config: PartSolutionConfig = PartSolutionConfig(),
    val part2Config: PartSolutionConfig = PartSolutionConfig(),
) {
    fun updatePart1(tr: PartSolutionConfig.() -> PartSolutionConfig): SolutionData = copy(part1Config = part1Config.tr())
    fun updatePart2(tr: PartSolutionConfig.() -> PartSolutionConfig): SolutionData = copy(part2Config = part2Config.tr())

    fun disablePart1(reason: String): SolutionData = updatePart1 { copy(disableReason = reason) }
    fun disablePart2(reason: String): SolutionData = updatePart2 { copy(disableReason = reason) }
    fun disableAll(reason: String): SolutionData = disablePart1(reason).disablePart2(reason)
    fun enableTimer(): SolutionData = updatePart1 { copy(timerEnabled = true) }.updatePart2 { copy(timerEnabled = true) }
}