import Machine.Companion.toMachine
import isdemidoff.utility.input.readLines
import isdemidoff.utility.parseUnescapedCsvInputLine
import kotlin.math.min

data class Button(val toggles: List<Int>) {
    fun applyOnLights(lights: Array<Boolean>) = toggles.forEach { lights[it] = !lights[it] }
}

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

    companion object {
        fun String.toMachine() = Machine(
            subSequence(indexOfFirst { it == '[' } + 1 ..< indexOfFirst { it == ']' }).map { it == '#' },
            subSequence(indexOfFirst { it == ']' } + 1 ..< indexOfFirst { it == '{' })
                .trim()
                .split("""\s+""".toRegex())
                .map { it.subSequence(1 ..< it.length - 1) }
                .map { Button(it.parseUnescapedCsvInputLine { it.toString().toInt() }) })
    }
}

fun solveForFileName(fileName: String) = readLines(fileName)
    .map { it.toMachine() }
    .sumOf { it.findLeastNumButtonsToTurnOn() }