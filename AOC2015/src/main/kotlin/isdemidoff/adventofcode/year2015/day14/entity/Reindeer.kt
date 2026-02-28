package isdemidoff.adventofcode.year2015.day14.entity

import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields
import isdemidoff.utility.parsing.toIntOrError
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

internal val readReindeer: (String) -> Reindeer = regexMatch(
    """(.*) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds\.""".toRegex() yields {
        Reindeer(
            name = it.component1(),
            speed = it.component2().toIntOrError(),
            flyingTime = it.component3().toIntOrError(),
            restingTime = it.component4().toIntOrError(),
        )
    }
)
