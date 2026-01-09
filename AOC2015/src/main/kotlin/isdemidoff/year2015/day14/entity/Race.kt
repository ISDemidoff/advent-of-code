package isdemidoff.year2015.day14.entity

interface Race {
    val participants: List<Reindeer>
    fun getRaceResults(raceDuration: Int): Map<Reindeer, Int>
}

class DistanceTravelledRace(override val participants: List<Reindeer>) : Race {
    override fun getRaceResults(raceDuration: Int) =
        participants.associateWith { it.distanceAfter(raceDuration) }
}

class LeadTimeRace(override val participants: List<Reindeer>) : Race {
    override fun getRaceResults(raceDuration: Int): Map<Reindeer, Int> {
        val scores = participants.associateWith { 0 }.toMutableMap()
        (1..raceDuration).forEach { elapsedTime ->
            val passedDistances = participants.associateWith { it.distanceAfter(elapsedTime) }
            val maxDistance = passedDistances.maxOf { it.value }

            passedDistances.filterValues { it == maxDistance }.keys.forEach { scores.compute(it) { _, v -> (v ?: 0) + 1 } }
        }

        return scores
    }
}

enum class RaceConditions(val raceGenerator: (List<Reindeer>) -> Race) {
    DISTANCE_TRAVELLED({ DistanceTravelledRace(it) }),
    TOTAL_LEAD_TIME({ LeadTimeRace(it) }),
}