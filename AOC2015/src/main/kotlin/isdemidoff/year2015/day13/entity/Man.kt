package isdemidoff.year2015.day13.entity

class Man(
    val name: String,
) {
    private val happiness = mutableMapOf<Man, Int>()

    infix fun happyWith(other: Man) = happiness[other]!!

    infix fun bothHappyWith(other: Man) = intArrayOf(this happyWith other, other happyWith this).sum()

    internal fun assignHappiness(other: Man, amount: Int) {
        happiness[other] = amount
    }
}