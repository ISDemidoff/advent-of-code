package isdemidoff.year2015.day22.entity

import kotlin.math.max

sealed interface GameState {
    val totalManaSpentSoFar: Int
}

data class Victory(override val totalManaSpentSoFar: Int) : GameState {}
data class Lose(override val totalManaSpentSoFar: Int) : GameState {}

data class Stats(
    val playerHealth: Int,
    val currentMana: Int,
    val bossHealth: Int,
    val bossDamage: Int,
    val currentEffects: List<Effect> = emptyList(),
) {
    private fun manaChange(): Int =
        currentEffects.mapNotNull { it as? RechargeEffect }
            .singleOrNull()
            ?.manaReplenishment ?: 0

    private fun bossHealthChange(): Int =
        currentEffects.mapNotNull { it as? PoisonEffect }
            .singleOrNull()
            ?.damagePerTurn?.unaryMinus() ?: 0

    fun applyEffects(enableLogging: Boolean): Stats = copy(
        currentMana = currentMana + manaChange(),
        bossHealth = bossHealth + bossHealthChange(),
        currentEffects = currentEffects.mapNotNull { it.tickDown(enableLogging) },
    )

    private fun bossDamage(enableLogging: Boolean): Int {
        val playerArmor = currentEffects.mapNotNull { it as? ShieldEffect }
            .singleOrNull()
            ?.armorIncrease ?: 0

        val actualDamage = max(1, bossDamage - playerArmor)

        if (enableLogging) println("Boss deals $actualDamage damage (player armor $playerArmor).")

        return actualDamage
    }

    fun bossDealDamage(enableLogging: Boolean): Stats = copy(
        playerHealth = playerHealth - bossDamage(enableLogging)
    )
}

fun parseInitialGameState(input: List<String>, hardMode: Boolean = false): InProgressGameState {
    require(input.size == 2) { "Input must contain exactly 2 lines." }
    return InProgressGameState(
        stats = Stats(
            playerHealth = 50 - if (hardMode) 1 else 0,
            currentMana = 500,
            bossHealth = input[0].substringAfterLast(' ').toInt(),
            bossDamage = input[1].substringAfterLast(' ').toInt(),
        ),
        hardMode = hardMode
    )
}

class InProgressGameState(
    val stats: Stats,
    override val totalManaSpentSoFar: Int = 0,
    val enableLogging: Boolean = false,
    val hardMode: Boolean = false,
) : GameState {

    fun getAvailableSpells(): List<Spell> {
        val effects = stats.currentEffects.map { it::class }

        return allSpells
            .filter { it.manaCost < stats.currentMana }
            .filterNot { effects.contains((it as? ApplyingEffectSpell<*>)?.effectType) }
    }

    fun useSpell(spell: Spell) : GameState {
        require(spell in getAvailableSpells()) { "Incorrect spell to cast!" }
        val newManaSpent = totalManaSpentSoFar + spell.manaCost

        // Player turn
        var newStats = when (spell) {
            is ApplyingEffectSpell<*> -> stats.copy(
                currentMana = stats.currentMana - spell.manaCost,
                currentEffects = stats.currentEffects + spell.produceEffect()
            )
            is DrainSpell -> stats.copy(
                currentMana = stats.currentMana - spell.manaCost,
                playerHealth = stats.playerHealth + spell.heal,
                bossHealth = stats.bossHealth - spell.damage,
            )
            is MagicMissileSpell -> stats.copy(
                currentMana = stats.currentMana - spell.manaCost,
                bossHealth = stats.bossHealth - spell.damage,
            )
        }

        if (enableLogging) println("Player casts ${spell::class.simpleName}, new state is $newStats")

        if (newStats.bossHealth <= 0) {
            if (enableLogging) println("Boss has no more health, victory!")
            return Victory(newManaSpent)
        }

        // Boss turn
        if (enableLogging) println("Boss's turn.")
        // Apply effects
        newStats = newStats.applyEffects(enableLogging)

        if (newStats.bossHealth <= 0) {
            if (enableLogging) println("Boss has no more health, victory!")
            return Victory(newManaSpent)
        }

        if (enableLogging) println("New state is $newStats")

        // Damage from boss
        newStats = newStats.bossDealDamage(enableLogging)

        if (newStats.playerHealth <= 0) {
            if (enableLogging) println("Player has no more health, lost!")
            return Lose(newManaSpent)
        }

        if (enableLogging) println("Player's turn.")
        // Lose health if hard
        if (hardMode) {
            newStats = newStats.copy(playerHealth = newStats.playerHealth - 1)
            if (enableLogging) println("Hard mode is enabled, player lose 1 health, new state is $newStats")

            if (newStats.playerHealth <= 0) {
                if (enableLogging) println("Player has no more health, lost!")
                return Lose(newManaSpent)
            }
        }
        // Apply effects
        newStats = newStats.applyEffects(enableLogging)

        if (newStats.bossHealth <= 0) {
            if (enableLogging) println("Boss has no more health, victory!")
            return Victory(newManaSpent)
        }

        val nextGameState = InProgressGameState(
            stats = newStats,
            totalManaSpentSoFar = newManaSpent,
            enableLogging = enableLogging,
            hardMode = hardMode,
        )

        if (nextGameState.getAvailableSpells().isEmpty()) {
            if (enableLogging) println("Player has no available spells, lost!.")
            return Lose(newManaSpent)
        } else {
            return nextGameState
        }
    }


}

val allSpells = listOf(
    MagicMissileSpell,
    DrainSpell,
    ShieldSpell,
    PoisonSpell,
    RechargeSpell,
)

