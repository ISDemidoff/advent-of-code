package isdemidoff.adventofcode.year2015.day22.entity

sealed interface Effect {
    val turnsRemaining: Int
    fun passTurn(): Effect
    fun logText(): String
    fun tickDown(enableLogging: Boolean): Effect? {
        return passTurn().also {
            if (enableLogging) {
                println("${this::class.simpleName} takes effect: ${logText()}; ${it.turnsRemaining} turns remaining.")
            }
        }.takeUnless { it.turnsRemaining <= 0 }
    }
}

data class ShieldEffect(
    override val turnsRemaining: Int,
    val armorIncrease: Int,
) : Effect {
    override fun passTurn() = copy(turnsRemaining = turnsRemaining - 1)
    override fun logText(): String = "armor equals $armorIncrease"
}

data class PoisonEffect(
    override val turnsRemaining: Int,
    val damagePerTurn: Int,
) : Effect {
    override fun passTurn() = copy(turnsRemaining = turnsRemaining - 1)
    override fun logText(): String = "damage boss for $damagePerTurn"
}

data class RechargeEffect(
    override val turnsRemaining: Int,
    val manaReplenishment: Int,
) : Effect {
    override fun passTurn() = copy(turnsRemaining = turnsRemaining - 1)
    override fun logText(): String = "mana gain $manaReplenishment"
}