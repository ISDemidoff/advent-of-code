package isdemidoff.utility.solution.year

import isdemidoff.utility.solution.model.PartSolutionConfig

data class YearSolutionConfig(
    val year: Int,
    val forceTimerEnabled: Boolean? = null,
    val forceShowAllSolutions: Boolean = false,
) {
    fun adjust(partConfig: PartSolutionConfig) =
        partConfig.copy(
            timerEnabled = this.forceTimerEnabled ?: partConfig.timerEnabled,
            disableReason = partConfig.disableReason.takeUnless { this.forceShowAllSolutions }
        )
}
