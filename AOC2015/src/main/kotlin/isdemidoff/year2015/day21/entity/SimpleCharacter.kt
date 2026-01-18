package isdemidoff.year2015.day21.entity

data class SimpleCharacter(
    override val name: String,
    override var hitPoints: Int,
    override var damage: Int,
    override var armor: Int,
) : GameCharacter

internal fun parseBossStats(rawInput: List<String>) : GameCharacter {
    require(rawInput.size == 3) { "Boss's stats must contain 3 lines!" }
    return rawInput.let { (hitPointsStr, damageStr, armorStr) ->
        SimpleCharacter(
            name = "boss",
            hitPoints = hitPointsStr.substringAfterLast(' ').toInt(),
            damage = damageStr.substringAfterLast(' ').toInt(),
            armor = armorStr.substringAfterLast(' ').toInt(),
        )
    }
}