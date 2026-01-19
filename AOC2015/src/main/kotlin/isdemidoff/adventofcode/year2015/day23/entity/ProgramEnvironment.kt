package isdemidoff.adventofcode.year2015.day23.entity

import isdemidoff.utility.isInvalidPosition

class ProgramEnvironment {
    interface RegistersState {
        fun getRegisterValue(id: String): UInt
    }

    interface MutableRegistersState : RegistersState {
        fun modifyRegister(id: String, modification: (UInt) -> UInt)
    }

    private data class SimpleMapMutableState(
        private val registerMap: MutableMap<String, UInt> = mutableMapOf(),
    ) : MutableRegistersState {
        override fun getRegisterValue(id: String): UInt =
            registerMap.computeIfAbsent(id) { UInt.MIN_VALUE }

        override fun modifyRegister(id: String, modification: (UInt) -> UInt) =
            registerMap.compute(id) { _, value -> modification(value ?: UInt.MIN_VALUE) }.let { }

        fun toReadOnlyModel() = MapState(registerMap.toMap())
    }

    private data class MapState(private val registerMap: Map<String, UInt>) : RegistersState {
        override fun getRegisterValue(id: String): UInt = requireNotNull(registerMap[id]) { "Not found register $id" }
    }

    private val state = SimpleMapMutableState()

    /**
     * Runs passed program against saved state and returns its RO view.
     * This method does not reset registers values.
     */
    fun runProgram(commands: List<Command>): RegistersState {
        var currentLine = 0

        while (!currentLine.isInvalidPosition(commands.size)) {
            val command = commands[currentLine]
            currentLine += command.execute(state)
        }

        return state.toReadOnlyModel()
    }
}