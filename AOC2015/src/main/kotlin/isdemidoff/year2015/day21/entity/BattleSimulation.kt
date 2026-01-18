package isdemidoff.year2015.day21.entity

class BattleSimulation(
    val firstChar: GameCharacter,
    val secondChar: GameCharacter,
    val logBattle: Boolean = false,
) {
    enum class Winner {
        FIRST,
        SECOND,
    }

    private fun Winner.nextTurn() = when (this) {
        Winner.FIRST -> Winner.SECOND
        Winner.SECOND -> Winner.FIRST
    }

    fun decideWinner(): Winner {
        var (firstHealth, secondHealth) = firstChar.hitPoints to secondChar.hitPoints

        var currentTurn = Winner.FIRST
        while (firstHealth > 0 && secondHealth > 0) {
            when (currentTurn) {
                Winner.FIRST -> {
                    val damageDealt = firstChar.damageDealtTo(secondChar)
                    secondHealth -= damageDealt
                    if (logBattle) println("${firstChar.name} deals $damageDealt damage; ${secondChar.name} has $secondHealth health left.")
                }
                Winner.SECOND -> {
                    val damageDealt = secondChar.damageDealtTo(firstChar)
                    firstHealth -= damageDealt
                    if (logBattle) println("${secondChar.name} deals $damageDealt damage; ${firstChar.name} has $firstHealth health left.")
                }
            }
            currentTurn = currentTurn.nextTurn()
        }
        return currentTurn.nextTurn()
    }
}

internal infix fun GameCharacter.winsAgainst(other: GameCharacter): Boolean = BattleSimulation(this, other).decideWinner() == BattleSimulation.Winner.FIRST