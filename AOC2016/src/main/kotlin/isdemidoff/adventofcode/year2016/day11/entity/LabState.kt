package isdemidoff.adventofcode.year2016.day11.entity

import isdemidoff.utility.cartesianProduct
import isdemidoff.utility.discretemath.chooseItems

data class LabState(
    val currentFloor: Int = 0, // 0-index
    val floors: List<Set<Component>>,
) {
    fun isValidConfiguration(): Boolean = floors.all { isSafeFloor(it) }

    fun isSafeFloor(floor: Set<Component>): Boolean =
        floor.filter { it.type == Component.ComponentType.CHIP }
            .all { chip -> floor.contains(Generator(chip.element)) || floor.none { it.type == Component.ComponentType.GENERATOR } }

    fun isWinningPosition(): Boolean =
        floors.mapIndexed { index, components ->
            when (index) {
                this.floors.lastIndex -> components.isNotEmpty()
                else -> components.isEmpty()
            }
        }.all { it }

    fun generateAllNextMoves(): List<LabState> =
        floors[currentFloor].let { currentFloorComponents ->
            (2 downTo 1).filterNot { it > currentFloorComponents.size }.flatMap { countOfTakenComponents ->
                cartesianProduct(
                    chooseItems(currentFloorComponents, countOfTakenComponents)
                        .filter { isSafeFloor(currentFloorComponents - it) },
                    possibleFloors()
                )
                    .filter { (variant, floor) -> isSafeFloor(this.floors[floor] + variant) }
                    .map { (variant, floor) ->
                        LabState(
                            currentFloor = floor,
                            floors = this.floors.mapIndexed { index, components ->
                                when (index) {
                                    this.currentFloor -> components - variant
                                    floor -> components + variant
                                    else -> components
                                }
                            },
                        )
                    }
            }
        }

    private fun possibleFloors(): List<Int> = when (currentFloor) {
        0 -> listOf(1)
        floors.lastIndex -> listOf(currentFloor - 1)
        else -> listOf(currentFloor + 1, currentFloor - 1)
    }
}

sealed interface Component {
    val type: ComponentType
    val element: String

    enum class ComponentType {
        GENERATOR,
        CHIP,
    }
}

data class Generator(
    override val element: String,
) : Component {
    override val type = Component.ComponentType.GENERATOR
}

data class Chip(
    override val element: String,
) : Component {
    override val type = Component.ComponentType.CHIP
}

fun parseComponents(input: String): Set<Component> = getChips(input) + getGenerators(input)

private fun getChips(input: String): Set<Component> =
    """([a-z]+)-compatible microchip""".toRegex()
        .findAll(input)
        .map { Chip(it.groupValues[1]) }
        .toSet()

private fun getGenerators(input: String): Set<Component> =
    """([a-z]+) generator""".toRegex()
        .findAll(input)
        .map { Generator(it.groupValues[1]) }
        .toSet()