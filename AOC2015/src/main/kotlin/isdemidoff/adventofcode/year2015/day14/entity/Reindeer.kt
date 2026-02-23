package isdemidoff.adventofcode.year2015.day14.entity

import kotlin.math.min

data class Reindeer(
    val name: String,
    val speed: Int,
    val flyingTime: Int,
    val restingTime: Int,
) {
    private val cycleTime = flyingTime + restingTime

    infix fun distanceAfter(seconds: Int) =
        (seconds / cycleTime * flyingTime + min(seconds % cycleTime, flyingTime)) * speed
}

internal fun readReindeerInfo(string: String) =
    """(.*) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds\.""".toRegex()
        .matchEntire(string)
        .let { requireNotNull(it?.destructured) { "Input string must match regexp" } }
        .let {
            Reindeer(
                name = it.component1(),
                speed = it.component2().toInt(),
                flyingTime = it.component3().toInt(),
                restingTime = it.component4().toInt(),
            )
        }