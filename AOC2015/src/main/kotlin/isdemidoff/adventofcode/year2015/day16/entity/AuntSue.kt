package isdemidoff.adventofcode.year2015.day16.entity

import isdemidoff.utility.parsing.keyValueWith

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

internal val auntSueMemoryParser: (String) -> AuntSue = {
    AuntSue(
        id = it.substringBefore(": ").substringAfter("Sue ").toInt(),
        properties = it.substringAfter(": ").split(", ").associate { it.keyValueWith { it.toInt() } },
    )
}

internal val auntSueMemoriesParser: (List<String>) -> List<AuntSue> = { it.map(auntSueMemoryParser) }

internal val auntSueAnalyzer: (List<String>) -> AuntSue = {
    AuntSue(
        id = 0,
        properties = it.associate { it.keyValueWith { it.toInt() } }
    )
}
