package isdemidoff.adventofcode.year2016.day23.entity

import isdemidoff.utility.isInvalidPosition

interface ProgramState {
    fun runProgram(limitOperations: Int = Int.MAX_VALUE)

    fun getRegisterValue(name: String): Int
    fun updateRegisterValue(name: String, updateFn: (Int) -> Int)
    fun updateInstruction(offset: Int, updateFn: (ProgramInstruction) -> ProgramInstruction)
    fun appendToOutput(value: Int)
    fun getOutput(): List<Int>

    fun extractValue(identifier: String): Int =
        identifier.toIntOrNull() ?: this.getRegisterValue(identifier)

    fun writeValue(identifierFrom: String, identifierTo: String): Unit =
        identifierTo.takeIf { it.toIntOrNull() == null }?.let {
            this.updateRegisterValue(it) { this.extractValue(identifierFrom) }
        } ?: Unit
}

class MapBasedProgramState(instructions: List<ProgramInstruction>) : ProgramState {
    private val registers = mutableMapOf<String, Int>()
    private val instructions = instructions.toMutableList()
    private val outputSignal = mutableListOf<Int>()
    private var currentInstructionIndex = 0
    private val optimizers = listOf(
//        DivisionOptimizer(),
//        MultiplicationOptimizer(),
//        SumOptimizer(),
        NoOptimization(),
    )

    override fun runProgram(limitOperations: Int) {
        var operationPassed = 0
        while (!currentInstructionIndex.isInvalidPosition(instructions.size)) {
            takeStep()
            if (++operationPassed == limitOperations) break
        }
    }

    private fun takeStep() {
        currentInstructionIndex += optimizers
            .first { it.applicable(instructions.toList(), currentInstructionIndex) }
            .perform(instructions.toList(), currentInstructionIndex, this)
    }

    override fun getRegisterValue(name: String) = registers[name] ?: 0

    override fun updateRegisterValue(name: String, updateFn: (Int) -> Int) =
        requireNotNull(registers.compute(name) { _, v -> updateFn(v ?: 0) }).let {  }

    override fun updateInstruction(offset: Int, updateFn: (ProgramInstruction) -> ProgramInstruction) {
        (currentInstructionIndex + offset)
            .takeUnless { it.isInvalidPosition(instructions.size) }
            ?.let { instructions[it] = updateFn(instructions[it]) }
    }

    override fun appendToOutput(value: Int) = outputSignal.add(value).let {  }

    override fun getOutput(): List<Int> = outputSignal.toList()
}