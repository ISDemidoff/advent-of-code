package isdemidoff.adventofcode.year2016.day10.entity

data class ProcessingLog(
    val type: ProcessingUnitType,
    val id: Int,
    val operation: ProcessingOperation,
)

sealed interface ProcessingOperation
data class ReceiveChipOperation(val chipValue: Int) : ProcessingOperation
data class CompareChipsOperation(val chipValues: Set<Int>) : ProcessingOperation

enum class ProcessingUnitType {
    OUTPUT,
    BOT,
}

sealed interface ProcessingUnit {
    val type: ProcessingUnitType
    val id: Int
    fun receiveChip(chipValue: Int)
    fun getLogs(): List<ProcessingLog>
}

abstract class AbstractProcessingUnit(
    override val type: ProcessingUnitType,
    override val id: Int,
) : ProcessingUnit {
    protected val receivedChips = mutableListOf<Int>()
    protected val processingLog = mutableListOf<ProcessingLog>()

    protected open fun processChips() = Unit

    protected fun createLog(operation: ProcessingOperation) {
        processingLog.add(ProcessingLog(type, id, operation))
    }

    override fun receiveChip(chipValue: Int) {
        createLog(ReceiveChipOperation(chipValue))
        receivedChips.add(chipValue)
        processChips()
    }

    override fun getLogs(): List<ProcessingLog> = processingLog.toList()
}

class Output(id: Int) : AbstractProcessingUnit(ProcessingUnitType.OUTPUT, id)

class Bot(id: Int) : AbstractProcessingUnit(ProcessingUnitType.BOT, id) {
    /**
     * Instruction of this bot: where to put lowest (left) and highest (right) chip.
     */
    private lateinit var instruction: Pair<ProcessingUnit, ProcessingUnit>

    fun receiveInstruction(instruction: Pair<ProcessingUnit, ProcessingUnit>) {
        this.instruction = instruction
        processChips()
    }

    private fun haveInstruction() = this::instruction.isInitialized
    private fun enoughChips() = this.receivedChips.size > 1

    fun readyToProcess() = haveInstruction() and enoughChips()

    override fun processChips() {
        if (!readyToProcess()) return
        createLog(CompareChipsOperation(this.receivedChips.toSet()))
        val (min, max) = this.receivedChips.min() to this.receivedChips.max()
        this.receivedChips.removeAll(listOf(min, max))
        this.instruction.first.receiveChip(min)
        this.instruction.second.receiveChip(max)
    }
}