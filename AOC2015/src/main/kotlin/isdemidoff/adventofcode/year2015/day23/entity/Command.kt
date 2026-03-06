package isdemidoff.adventofcode.year2015.day23.entity

import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields
import isdemidoff.utility.parsing.toIntOrError

sealed interface Command {
    /**
     * Human-readable
     */
    val description: String

    /**
     * Receives a modifiable program state, returns line offset to next execution.
     */
    fun execute(state: ProgramEnvironment.MutableRegistersState): Int
}

abstract class NoJumpCommand(
    private val modifyState: (ProgramEnvironment.MutableRegistersState) -> Unit,
) : Command {
    override fun execute(state: ProgramEnvironment.MutableRegistersState) = modifyState(state).let { 1 }
}

abstract class UpdateRegisterCommand(
    protected val registerId: String,
    private val modificationRule: (UInt) -> UInt,
) : NoJumpCommand(modifyState = { it.modifyRegister(registerId, modificationRule) })

class HlfRegisterCommand(
    registerId: String,
) : UpdateRegisterCommand(
    registerId = registerId,
    modificationRule = { it / 2u },
) {
    override val description = "Half the register '$registerId'"
}

class TplRegisterCommand(
    registerId: String,
) : UpdateRegisterCommand(
    registerId = registerId,
    modificationRule = { it * 3u },
) {
    override val description = "Triple the register '$registerId'"
}

class IncRegisterCommand(
    registerId: String,
) : UpdateRegisterCommand(
    registerId = registerId,
    modificationRule = { it + 1u },
) {
    override val description = "Increment register '$registerId' by 1"
}

abstract class AbstractJumpCommand(
    protected val offset: Int,
    private val condition: (ProgramEnvironment.MutableRegistersState) -> Boolean
) : Command {
    override fun execute(state: ProgramEnvironment.MutableRegistersState): Int = if (condition(state)) offset else 1
}

class JmpCommand(
    offset: Int,
) : AbstractJumpCommand(
    offset = offset,
    condition = { true },
) {
    override val description = "Jump $offset lines"
}

abstract class ConditionalOnSingleRegisterCommand(
    offset: Int,
    protected val registerId: String,
    private val registerCondition: (UInt) -> Boolean,
) : AbstractJumpCommand(
    offset = offset,
    condition = { registerCondition(it.getRegisterValue(registerId)) },
)

class JieCommand(
    registerId: String,
    offset: Int,
) : ConditionalOnSingleRegisterCommand(
    offset = offset,
    registerId = registerId,
    registerCondition = { it % 2u == 0u },
) {
    override val description: String = "Jump $offset if register '$registerId' is even"
}

class JioCommand(
    registerId: String,
    offset: Int,
) : ConditionalOnSingleRegisterCommand(
    offset = offset,
    registerId = registerId,
    registerCondition = { it == 1u },
) {
    override val description: String = "Jump $offset if register '$registerId' is 1"
}

val commandParser: (String) -> Command = regexMatch(
    """hlf ([a-z]+)""".toRegex() yields { (register) -> HlfRegisterCommand(register) },
    """tpl ([a-z]+)""".toRegex() yields { (register) -> TplRegisterCommand(register) },
    """inc ([a-z]+)""".toRegex() yields { (register) -> IncRegisterCommand(register) },
    """jmp (([-+])?\d+)""".toRegex() yields { (value) -> JmpCommand(value.toIntOrError()) },
    """jie ([a-z]+), (([-+])?\d+)""".toRegex() yields { (register, value) -> JieCommand(register, value.toIntOrError()) },
    """jio ([a-z]+), (([-+])?\d+)""".toRegex() yields { (register, value) -> JioCommand(register, value.toIntOrError()) },
)
