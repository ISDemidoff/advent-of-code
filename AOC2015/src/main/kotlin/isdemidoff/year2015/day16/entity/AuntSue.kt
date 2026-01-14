package isdemidoff.year2015.day16.entity

import isdemidoff.utility.keyValueWith
import kotlin.collections.all

data class AuntSue(
    val id: Int,
    val properties: Map<String, Int>,
) {
    fun seemsLike(
        other: AuntSue,
        ruleOverride: Map<String, (Int, Int) -> Boolean>,
    ) = this.properties.all { (prop, count) ->
        val compareRule = ruleOverride[prop] ?: Int::equals
        val otherCount = other.properties[prop]

        otherCount?.let { compareRule(count, it) } ?: true
    }

    fun seemsLike(
        other: AuntSue,
        vararg ruleOverride: Pair<String, (Int, Int) -> Boolean>,
    ) = this.properties.all { (prop, count) ->
        val compareRule = ruleOverride.find { it.first == prop }?.second ?: Int::equals
        val otherCount = other.properties[prop]

        otherCount?.let { compareRule(count, it) } ?: true
    }
}

fun String.parseProperty() = this.keyValueWith { it.toInt() }

fun String.rememberAuntSue() =
    AuntSue(
        id = this.substringBefore(": ").substringAfter("Sue ").toInt(),
        properties = this.substringAfter(": ").split(", ").associate { it.parseProperty() },
    )

fun List<String>.analysis() =
    AuntSue(
        id = 0,
        properties = this.associate { it.parseProperty() }
    )

enum class ComparingRules(vararg val overrides: Pair<String, (Int, Int) -> Boolean>) {
    DEFAULT,
    COMPLICATED(
        "cats" to { analisys, actual -> actual > analisys },
        "trees" to { analisys, actual -> actual > analisys },
        "pomeranians" to { analisys, actual -> actual < analisys },
        "goldfish" to { analisys, actual -> actual < analisys },
    ),
}