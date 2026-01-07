package isdemidoff.year2015.day6.entity

import kotlin.math.max

sealed interface Light {
    infix fun applyOperation(operation: Instruction.Operation)
    fun getValue(): Int
}

data class TogglingLight(
    private var lighted: Boolean = false,
) : Light {
    override fun applyOperation(operation: Instruction.Operation) = when (operation) {
        Instruction.Operation.TURN_ON -> lighted = true
        Instruction.Operation.TURN_OFF -> lighted = false
        Instruction.Operation.TOGGLE -> lighted = !lighted
    }

    override fun getValue(): Int = if (lighted) 1 else 0
}

data class BrightnessLight(
    private var brightness: Int = 0,
) : Light {
    override fun applyOperation(operation: Instruction.Operation) = when (operation) {
        Instruction.Operation.TURN_ON -> brightness += 1
        Instruction.Operation.TURN_OFF -> brightness = max(brightness - 1, 0)
        Instruction.Operation.TOGGLE -> brightness += 2
    }

    override fun getValue(): Int = brightness
}