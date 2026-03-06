package isdemidoff.adventofcode.year2025.day10.entity

import isdemidoff.utility.parseUnescapedCsvInputLine
import kotlin.math.min

data class Machine(
    val requiredLights: List<Boolean>,
    val buttons: List<Button>,
) {
    fun findLeastNumButtonsToTurnOn(): Int {
        var minValue = buttons.size + 1

        (0..<(1 shl buttons.size)).forEach { candidate ->
            // Calculate applying result
            val sampleLights = Array(requiredLights.size) { false }

            val usedButtons = Integer.toBinaryString(candidate).padStart(buttons.size, '0')

            buttons.forEachIndexed { index, btn ->
                if (usedButtons[index] == '1') {
                    btn.applyOnLights(sampleLights)
                }
            }

            if (sampleLights.toList() == requiredLights) {
                // Check number of 1
                minValue = min(minValue, usedButtons.count { it == '1' })
            }
        }

        return minValue
    }
}

val machineParser: (String) -> Machine = { input ->
    Machine(
        input.subSequence(input.indexOfFirst { it == '[' } + 1..<input.indexOfFirst { it == ']' }).map { it == '#' },
        input.subSequence(input.indexOfFirst { it == ']' } + 1..<input.indexOfFirst { it == '{' })
            .trim()
            .split("""\s+""".toRegex())
            .map { it.subSequence(1 ..< it.length - 1) }
            .map { Button(it.parseUnescapedCsvInputLine { it.toString().toInt() }) })
}
