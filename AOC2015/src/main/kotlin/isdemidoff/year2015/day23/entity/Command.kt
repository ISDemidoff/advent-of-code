package isdemidoff.year2015.day23.entity

import isdemidoff.utility.keyValueWith

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

fun parseCommand(command: String): Command {
    return when (command.substringBefore(" ")) {
        "hlf" -> HlfRegisterCommand(command.substringAfter(" "))
        "tpl" -> TplRegisterCommand(command.substringAfter(" "))
        "inc" -> IncRegisterCommand(command.substringAfter(" "))
        "jmp" -> JmpCommand(command.substringAfter(" ").toInt())
        "jie" -> command.substringAfter(" ").keyValueWith(", ") { it.toInt() }.let { JieCommand(it.first, it.second) }
        "jio" -> command.substringAfter(" ").keyValueWith(", ") { it.toInt() }.let { JioCommand(it.first, it.second) }
        else -> throw IllegalArgumentException("Unknown command: $command")
    }
}