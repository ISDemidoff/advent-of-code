package isdemidoff.adventofcode.year2016.day23.entity

import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields

sealed interface ProgramInstruction {
    /**
     * @return next line offset
     */
    fun execute(state: ProgramState): Int
    fun getToggledInstructions(): ProgramInstruction
}

// 1 arg
data class IncreaseProgramInstruction(val register: String) : ProgramInstruction {
    override fun execute(state: ProgramState) = 1.also { state.updateRegisterValue(register) { it + 1 } }
    override fun getToggledInstructions() = DecreaseProgramInstruction(register)
    override fun toString() = "inc $register"
}

// 1 arg
data class DecreaseProgramInstruction(val register: String) : ProgramInstruction {
    override fun execute(state: ProgramState) = 1.also{ state.updateRegisterValue(register) { it - 1 } }
    override fun getToggledInstructions() = IncreaseProgramInstruction(register)
    override fun toString() = "dec $register"
}

// 1 arg
data class ToggleInstructionProgramInstruction(val arg: String): ProgramInstruction {
    override fun execute(state: ProgramState) = 1.also { state.updateInstruction(state.extractValue(arg)) { it.getToggledInstructions() } }
    override fun getToggledInstructions() = IncreaseProgramInstruction(arg)
    override fun toString() = "tgl $arg"
}

// 1 arg
data class EmitOutProgramInstruction(val arg: String): ProgramInstruction {
    override fun execute(state: ProgramState) = 1.also { state.appendToOutput(state.extractValue(arg)) }
    override fun getToggledInstructions() = IncreaseProgramInstruction(arg)
    override fun toString() = "out $arg"
}

// 2 arg
data class CopyValueProgramInstruction(val from: String, val to: String) : ProgramInstruction {
    override fun execute(state: ProgramState) = 1.also { state.writeValue(from, to) }
    override fun getToggledInstructions() = JumpWhenNotZeroProgramInstruction(from, to)
    override fun toString() = "cpy $from $to"
}

// 2 arg
data class JumpWhenNotZeroProgramInstruction(val argToCompare: String, val jumpValue: String) : ProgramInstruction {
    override fun execute(state: ProgramState) = state.extractValue(jumpValue).takeUnless { state.extractValue(argToCompare) == 0 } ?: 1
    override fun getToggledInstructions() = CopyValueProgramInstruction(argToCompare, jumpValue)
    override fun toString() = "jnz $argToCompare $jumpValue"
}

val instructionReader: (String) -> ProgramInstruction = regexMatch(
    """inc ([a-z])""".toRegex() yields { (arg) -> IncreaseProgramInstruction(arg) },
    """dec ([a-z])""".toRegex() yields { (arg) -> DecreaseProgramInstruction(arg) },
    """jnz ([a-z0-9\-]+) ([a-z0-9\-]+)""".toRegex() yields { (arg0, arg1) -> JumpWhenNotZeroProgramInstruction(arg0, arg1) },
    """cpy ([a-z0-9\-]+) ([a-z0-9\-]+)""".toRegex() yields { (arg0, arg1) -> CopyValueProgramInstruction(arg0, arg1) },
    """tgl ([a-z0-9\-]+)""".toRegex() yields { (arg) -> ToggleInstructionProgramInstruction(arg) },
    """out ([a-z0-9\-]+)""".toRegex() yields { (arg) -> EmitOutProgramInstruction(arg) },
)