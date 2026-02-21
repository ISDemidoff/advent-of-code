package isdemidoff.utility.solution.model

data class PartSolutionConfig(
    val args: List<Any> = emptyList(),
    val disableReason: String? = null,
    val timerEnabled: Boolean = false,
)