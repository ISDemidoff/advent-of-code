package isdemidoff.year2015.day22.entity

import kotlin.reflect.KClass

sealed interface Spell {
    val manaCost: Int
}

interface ApplyingEffectSpell<T : Effect> : Spell {
    val effectType: KClass<T>
    fun produceEffect() : T
}

object MagicMissileSpell : Spell {
    override val manaCost = 53
    const val damage = 4
}

object DrainSpell : Spell {
    override val manaCost = 73
    const val damage = 2
    const val heal = 2
}

object ShieldSpell : ApplyingEffectSpell<ShieldEffect> {
    override val manaCost = 113
    override val effectType = ShieldEffect::class
    override fun produceEffect() = ShieldEffect(6, 7)
}

object PoisonSpell : ApplyingEffectSpell<PoisonEffect> {
    override val manaCost = 173
    override val effectType = PoisonEffect::class
    override fun produceEffect() = PoisonEffect(6, 3)
}

object RechargeSpell : ApplyingEffectSpell<RechargeEffect> {
    override val manaCost = 229
    override val effectType = RechargeEffect::class
    override fun produceEffect() = RechargeEffect(5, 101)
}