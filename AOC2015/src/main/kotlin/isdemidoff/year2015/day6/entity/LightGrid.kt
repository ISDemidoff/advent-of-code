package isdemidoff.year2015.day6.entity

class LightGrid {
    private val lights = Array(1000) { Array(1000) { false } }

    fun applyInstruction(instruction: Instruction) {
        (instruction.xFrom..instruction.xTo).forEach { x ->
            (instruction.yFrom..instruction.yTo).forEach { y ->
                when (instruction.operation) {
                    Instruction.Operation.TURN_ON -> lights[x][y] = true
                    Instruction.Operation.TURN_OFF -> lights[x][y] = false
                    Instruction.Operation.TOGGLE -> lights[x][y] = !lights[x][y]
                }
            }
        }
    }

    fun applyInstructions(instructions: List<Instruction>) = instructions.forEach { applyInstruction(it) }

    fun countOfLitLights() = lights.sumOf { it.count { it } }
}