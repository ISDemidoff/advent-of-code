package isdemidoff.year2015.day14.entity

import kotlin.math.min

data class Reindeer(
    val speed: Int,
    val flyingTime: Int,
    val restingTime: Int,
) {
    private val cycleTime = flyingTime + restingTime

    infix fun distanceAfter(seconds: Int) =
        (seconds / cycleTime * flyingTime + min(seconds % cycleTime, flyingTime)) * speed
}

internal fun String.createReindeer() =
    """.* can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds\.""".toRegex()
        .matchEntire(this)
        .let { requireNotNull(it) { "Input string must match regexp" } }
        .groups
        .let {
            Reindeer(
                speed = it[1]!!.value.toInt(),
                flyingTime = it[2]!!.value.toInt(),
                restingTime = it[3]!!.value.toInt(),
            )
        }