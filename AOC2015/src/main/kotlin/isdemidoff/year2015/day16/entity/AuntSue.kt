package isdemidoff.year2015.day16.entity

import isdemidoff.utility.keyValueWith

data class AuntSue(
    val id: Int,
    val properties: Map<String, Int>,
) {
    fun seemsLike(
        other: AuntSue,
        vararg ruleOverride: Pair<String, (Int, Int) -> Boolean>,
    ) = this.properties.all { (prop, count) ->
        val compareRule = ruleOverride.find { it.first == prop }?.second ?: Int::equals
        val otherCount = other.properties[prop]

        otherCount?.let { compareRule(count, it) } ?: true
    }
}

internal fun parseAuntSueFromMemory(string: String) =
    AuntSue(
        id = string.substringBefore(": ").substringAfter("Sue ").toInt(),
        properties = string.substringAfter(": ").split(", ").associate { it.keyValueWith { it.toInt() } },
    )

internal fun analyseAuntSue(strings: List<String>) =
    AuntSue(
        id = 0,
        properties = strings.associate { it.keyValueWith { it.toInt() } }
    )
