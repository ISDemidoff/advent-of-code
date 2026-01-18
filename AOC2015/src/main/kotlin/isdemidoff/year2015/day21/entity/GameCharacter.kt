package isdemidoff.year2015.day21.entity

import kotlin.math.max

interface GameCharacter {
    val name: String
    val hitPoints: Int
    val damage: Int
    val armor: Int

    fun damageDealtTo(other: GameCharacter): Int = max(1, this.damage - other.armor)
}