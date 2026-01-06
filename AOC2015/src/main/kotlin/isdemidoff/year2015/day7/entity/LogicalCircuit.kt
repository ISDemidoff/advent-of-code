package isdemidoff.year2015.day7.entity

class LogicalCircuit {
    private val wires = mutableMapOf<String, LogicalWire>()

    fun importWires(input: List<LogicalWire>) {
        input.forEach { wires[it.identifier] = it }
        input.forEach { it.assignInputs(wires) }
    }

    fun getValue(identifier: String): UShort {
        check(wires.containsKey(identifier)) { "Not existing wire: $identifier" }
        return wires[identifier]!!.signalValue
    }
}