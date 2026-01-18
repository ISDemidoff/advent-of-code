package isdemidoff.year2015.day22

import isdemidoff.utility.solution.solution
import isdemidoff.year2015.day22.entity.GameState
import isdemidoff.year2015.day22.entity.InProgressGameState
import isdemidoff.year2015.day22.entity.Lose
import isdemidoff.year2015.day22.entity.Victory
import isdemidoff.year2015.day22.entity.parseInitialGameState
import java.util.LinkedList
import kotlin.math.min

/**
 * [Day 22: Wizard Simulator 20XX](https://adventofcode.com/2015/day/22).
 */
val day22 = solution(22) {
    inputParser = singleBlockParser { it }

    fun findMinimalManaSpentForAWin(initialState: GameState): Int {
        val queue = LinkedList<GameState>()
        queue.offer(initialState)

        var minManaSpent = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val currentGameState = queue.remove()
            if (currentGameState.totalManaSpentSoFar > minManaSpent) continue

            when (currentGameState) {
                is Lose -> continue
                is Victory -> minManaSpent = min(minManaSpent, currentGameState.totalManaSpentSoFar)
                is InProgressGameState -> {
                    val availableSpells = currentGameState.getAvailableSpells()
                    availableSpells.forEach { queue.offer(currentGameState.useSpell(it)) }
                }
            }
        }

        return minManaSpent
    }

    part1Solver = solver({
        "Least mana to use to win is $it."
    }) { initialState -> findMinimalManaSpentForAWin(parseInitialGameState(initialState)) }

    part2Solver = solver({
        "Least mana to use to win hard mode is $it."
    }) { initialState -> findMinimalManaSpentForAWin(parseInitialGameState(initialState, true)) }
}