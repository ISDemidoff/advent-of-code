package isdemidoff.year2015.day13.entity

import isdemidoff.utility.discretemath.permutations

class TableArrangement(
    peopleRaw: List<String>,
    addIgnorantMan: Boolean,
) {
    private val people = mutableMapOf<String, Man>()

    init {
        peopleRaw.map { rawMan ->
            val (fromAndHappiness, to) = rawMan.dropLast(1).split(" happiness units by sitting next to ")
                .also { check(it.size == 2) }
                .let { it.first() to it.last() }

            val (from, happiness) = fromAndHappiness.split(" would ")
                .also { check(it.size == 2) }
                .let {
                    it.first() to it.last().let {
                        when {
                            it.startsWith("lose ") -> it.substring(5).toInt().unaryMinus()
                            it.startsWith("gain ") -> it.substring(5).toInt()
                            else -> error("Wanted 'gain' or 'lose' as prefix of happiness, but got $it; full line: $rawMan")
                        }
                    }
                }

            (createMan(from) to createMan(to)) to happiness
        }.forEach { (connection, happiness) -> connection.first.assignHappiness(connection.second, happiness) }

        if (addIgnorantMan) {
            val allPeople = people.values.toList()
            val newMan = createMan("Ignorant man")

            allPeople.forEach {
                it.assignHappiness(newMan, 0)
                newMan.assignHappiness(it, 0)
            }
        }
    }

    private fun createMan(name: String) = people.computeIfAbsent(name) { Man(name) }

    private fun calculateHappiness(setup: List<String>) =
        (setup + setup.first()).zipWithNext { left, right -> people[left]!! bothHappyWith people[right]!! }.sum()

    fun findBestSetup() = people.keys.sorted().permutations().maxOf { calculateHappiness(it) }
}