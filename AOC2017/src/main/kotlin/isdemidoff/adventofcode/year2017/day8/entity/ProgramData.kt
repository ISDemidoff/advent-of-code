package isdemidoff.adventofcode.year2017.day8.entity

class ProgramData {
    private val data: MutableMap<String, Int> = mutableMapOf()
    private var maxValueHeld = 0

    fun runInstructions(instructions: List<ProgramInstruction>) {
        instructions.forEach {
            if (compareRegister(it.conditionRegister, it.condition, it.conditionValue)) {
                applyOperation(it.register, it.operation, it.operationValue)
            }
        }
    }

    fun findLargestRegisterValue(): Int = data.maxOf { it.value }

    fun getLargestValueHeld(): Int = maxValueHeld

    private fun getRegisterValue(register: String): Int = data[register] ?: 0

    private fun compareRegister(register: String, operation: ProgramInstruction.Condition, value: Int): Boolean =
        operation.predicate(getRegisterValue(register), value)

    private fun applyOperation(register: String, operation: ProgramInstruction.Operation, value: Int) =
        data.compute(register) { _, v ->
            operation.operation(v ?: 0, value)
                .also { if (it > maxValueHeld) maxValueHeld = it }
        }
}
