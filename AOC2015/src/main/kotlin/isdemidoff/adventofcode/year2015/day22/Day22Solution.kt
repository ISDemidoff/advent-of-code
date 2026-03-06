package isdemidoff.adventofcode.year2015.day22

import isdemidoff.adventofcode.year2015.day22.entity.GameState
import isdemidoff.adventofcode.year2015.day22.entity.InProgressGameState
import isdemidoff.adventofcode.year2015.day22.entity.Lose
import isdemidoff.adventofcode.year2015.day22.entity.Victory
import isdemidoff.adventofcode.year2015.day22.entity.parseInitialGameState
import isdemidoff.solution.inputparser.scope.singleBlock
import isdemidoff.solution.solution
import java.util.*
import kotlin.math.min

/**
 * [Day 22: Wizard Simulator 20XX](https://adventofcode.com/2015/day/22).
 */
val day22 = solution(22) {
    inputParser = singleBlock

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
