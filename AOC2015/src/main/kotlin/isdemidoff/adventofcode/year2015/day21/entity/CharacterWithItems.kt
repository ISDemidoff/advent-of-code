package isdemidoff.adventofcode.year2015.day21.entity

data class CharacterWithItems(
    override val name: String,
    override val hitPoints: Int = 100,
    val items: List<Item>
) : GameCharacter {
    override val damage by lazy { items.sumOf { it.damage } }
    override val armor by lazy { items.sumOf { it.defence } }

    fun getTotalCostOfItems() = items.sumOf { it.cost }
}
