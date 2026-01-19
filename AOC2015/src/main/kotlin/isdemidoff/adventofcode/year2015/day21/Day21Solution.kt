package isdemidoff.adventofcode.year2015.day21

import isdemidoff.adventofcode.year2015.day21.entity.CharacterWithItems
import isdemidoff.adventofcode.year2015.day21.entity.Item
import isdemidoff.adventofcode.year2015.day21.entity.ItemType
import isdemidoff.adventofcode.year2015.day21.entity.armors
import isdemidoff.adventofcode.year2015.day21.entity.parseBossStats
import isdemidoff.adventofcode.year2015.day21.entity.rings
import isdemidoff.adventofcode.year2015.day21.entity.weaponChooses
import isdemidoff.adventofcode.year2015.day21.entity.winsAgainst
import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.discretemath.chooseItemsCount
import isdemidoff.utility.discretemath.createAllChoices
import isdemidoff.utility.solution.solution

internal fun allSetupsOfItems(counts: Map<ItemType, Int>): List<List<Item>> {
    return cartesianProduct(
        cartesianProduct(
            armors chooseItemsCount counts[ItemType.ARMOR]!!,
            rings chooseItemsCount counts[ItemType.RING]!!,
        ).map { (armorChoose, ringsChoose) -> armorChoose + ringsChoose },
        weaponChooses
    ).map { (armorAndRingChoose, weaponChoose) -> armorAndRingChoose + weaponChoose }
}

/**
 * [Day 21: RPG Simulator 20XX](https://adventofcode.com/2015/day/21).
 */
val day21 = solution(21) {
    inputParser = singleBlockParser { parseBossStats(it) }

    part1Solver = solver({
        "Least money to spend still winning is $it."
    }) { boss ->
        createAllChoices(
            mapOf(
                ItemType.ARMOR to 1,
                ItemType.RING to 2,
            )
        )
            .flatMap { setOfItemTypes -> allSetupsOfItems(setOfItemTypes) }
            .map {
                CharacterWithItems(
                    name = "player",
                    items = it,
                )
            }
            .filter { it winsAgainst boss }
            .minOf { it.getTotalCostOfItems() }
    }

    part2Solver = solver({
        "Most money to spend still losing is $it."
    }) { boss ->
        createAllChoices(
            mapOf(
                ItemType.ARMOR to 1,
                ItemType.RING to 2,
            )
        )
            .flatMap { setOfItemTypes -> allSetupsOfItems(setOfItemTypes) }
            .map {
                CharacterWithItems(
                    name = "player",
                    items = it,
                )
            }
            .filterNot { it winsAgainst boss }
            .maxOf { it.getTotalCostOfItems() }
    }
}
