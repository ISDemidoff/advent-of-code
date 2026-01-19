package isdemidoff.adventofcode.year2015.day21.entity

import isdemidoff.utility.discretemath.chooseItems

sealed interface Item {
    val type: ItemType
    val name: String
    val cost: Int
    val damage: Int
    val defence: Int
}

data class WeaponItem(
    override val name: String,
    override val cost: Int,
    override val damage: Int,
) : Item {
    override val type = ItemType.WEAPON
    override val defence = 0
}

data class ArmorItem(
    override val name: String,
    override val cost: Int,
    override val defence: Int,
) : Item {
    override val type = ItemType.ARMOR
    override val damage = 0
}

open class RingItem(
    override val name: String,
    override val cost: Int,
    override val damage: Int,
    override val defence: Int,
) : Item {
    override val type = ItemType.RING
}

data class DamageRingItem(
    override val name: String,
    override val cost: Int,
    override val damage: Int,
) : RingItem(name, cost, damage, 0)

data class DefenceRingItem(
    override val name: String,
    override val cost: Int,
    override val defence: Int,
) : RingItem(name, cost, 0, defence)

val allItems = listOf(
    // Weapons
    WeaponItem("Dagger", 8, 4),
    WeaponItem("Shortsword", 10, 5),
    WeaponItem("Warhammer", 25, 6),
    WeaponItem("Longsword", 40, 7),
    WeaponItem("Greataxe", 74, 8),

    // Armors
    ArmorItem("Leather", 13, 1),
    ArmorItem("Chainmail", 31, 2),
    ArmorItem("Splintmail", 53, 3),
    ArmorItem("Bandedmail", 75, 4),
    ArmorItem("Platemail", 102, 5),

    // Rings
    DamageRingItem("Damage +1", 25, 1),
    DamageRingItem("Damage +2", 50, 2),
    DamageRingItem("Damage +3", 100, 3),
    DefenceRingItem("Defence +1", 20, 1),
    DefenceRingItem("Defence +2", 40, 2),
    DefenceRingItem("Defence +3", 80, 3),
)

val weapons = allItems.filter { it.type == ItemType.WEAPON }
val armors = allItems.filter { it.type == ItemType.ARMOR }
val rings = allItems.filter { it.type == ItemType.RING }

val weaponChooses = chooseItems(weapons, 1)

enum class ItemType {
    WEAPON,
    ARMOR,
    RING,
}