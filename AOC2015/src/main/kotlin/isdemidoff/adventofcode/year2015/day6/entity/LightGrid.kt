package isdemidoff.adventofcode.year2015.day6.entity

class LightGrid(
    lightGenerator: () -> Light,
) {
    private val lights = Array(1000) { Array(1000) { lightGenerator() } }

    fun applyInstruction(instruction: Instruction) {
        (instruction.xFrom..instruction.xTo).forEach { x ->
            (instruction.yFrom..instruction.yTo).forEach { y ->
                lights[x][y] applyOperation instruction.operation
            }
        }
    }

    fun applyInstructions(instructions: List<Instruction>) = instructions.forEach { applyInstruction(it) }

    fun totalValueOfLitLights() = lights.sumOf { it.sumOf { it.getValue() } }
}