package isdemidoff.adventofcode.year2016.day12.entity

import isdemidoff.utility.exception.validationError
import isdemidoff.utility.keyValue
import isdemidoff.utility.keyValueWith

sealed interface ProgramInstruction {
    /**
     * @return next line offset
     */
    fun execute(state: ProgramState): Int
}

abstract class RegisterMutatingProgramInstruction : ProgramInstruction {
    abstract fun updateState(state: ProgramState)
    override fun execute(state: ProgramState) = 1.also { updateState(state) }
}

class IncreaseProgramInstruction(private val register: String) : RegisterMutatingProgramInstruction() {
    override fun updateState(state: ProgramState) = state.updateRegisterValue(register) { it + 1 }.let {  }
}

class DecreaseProgramInstruction(private val register: String) : RegisterMutatingProgramInstruction() {
    override fun updateState(state: ProgramState) = state.updateRegisterValue(register) { it - 1 }.let {  }
}

abstract class CopyValueProgramInstruction(private val register: String) : RegisterMutatingProgramInstruction() {
    abstract fun getValueToCopy(state: ProgramState): Int
    override fun updateState(state: ProgramState) = state.updateRegisterValue(register) { getValueToCopy(state) }.let {  }
}

class CopyConcreteValueProgramInstruction(private val value: Int, register: String) : CopyValueProgramInstruction(register) {
    override fun getValueToCopy(state: ProgramState) = value
}

class CopyFromRegisterProgramInstruction(private val fromRegister: String, toRegister: String) : CopyValueProgramInstruction(toRegister) {
    override fun getValueToCopy(state: ProgramState) = state.getRegisterValue(fromRegister)
}

abstract class JumpProgramInstruction(private val jumpValue: Int) : ProgramInstruction {
    abstract fun condition(state: ProgramState): Boolean
    override fun execute(state: ProgramState) = if (condition(state)) jumpValue  else 1
}

abstract class JumpWhenNotZeroProgramInstruction(jumpValue: Int) : JumpProgramInstruction(jumpValue) {
    abstract fun getValueToCompare(state: ProgramState): Int
    override fun condition(state: ProgramState) = getValueToCompare(state) != 0
}

class JumpWhenValueNotZeroProgramInstruction(private val value: Int, jumpValue: Int) : JumpWhenNotZeroProgramInstruction(jumpValue) {
    override fun getValueToCompare(state: ProgramState) = value
}

class JumpWhenRegisterNotZeroProgramInstruction(private val register: String, jumpValue: Int) : JumpWhenNotZeroProgramInstruction(jumpValue) {
    override fun getValueToCompare(state: ProgramState) = state.getRegisterValue(register)
}

fun readInstruction(str: String): ProgramInstruction = when {
    str.startsWith("inc") -> IncreaseProgramInstruction(str.removePrefix("inc "))

    str.startsWith("dec") -> DecreaseProgramInstruction(str.removePrefix("dec "))

    str.startsWith("jnz") -> str.removePrefix("jnz ").keyValueWith(" ") { it.toInt() }.let { (value, jmp) ->
        value.toIntOrNull()?.run { return@let JumpWhenValueNotZeroProgramInstruction(this, jmp) }
        JumpWhenRegisterNotZeroProgramInstruction(value, jmp)
    }

    str.startsWith("cpy") -> str.removePrefix("cpy ").keyValue(" ").let { (from, to) ->
        from.toIntOrNull()?.run { return@let CopyConcreteValueProgramInstruction(this, to) }
        CopyFromRegisterProgramInstruction(from, to)
    }

    else -> validationError { "String $str not matched as any program instruction." }
}