package isdemidoff.adventofcode.year2016.day10.entity

import isdemidoff.utility.matching.regexMatch
import isdemidoff.utility.matching.yields

class ProcessingField {
    private val units = mutableListOf<ProcessingUnit>()

    fun getAllLogs() = units.flatMap { it.getLogs() }

    fun processInstructions(instructions: List<Instruction>) {
        instructions.forEach {
            when (it) {
                is InputInstruction -> getUnit(ProcessingUnitType.BOT, it.botId).receiveChip(it.chipValue)

                is BotInstruction -> (getUnit(ProcessingUnitType.BOT, it.botId) as Bot)
                    .receiveInstruction(getUnit(it.lowType, it.lowId) to getUnit(it.highType, it.highId))
            }
        }
    }

    private fun getUnit(type: ProcessingUnitType, id: Int): ProcessingUnit =
        units.find { (it.id == id) and (it.type == type) } ?: createUnit(type, id)

    private fun createUnit(
        type: ProcessingUnitType,
        id: Int,
    ): ProcessingUnit = when (type) {
        ProcessingUnitType.BOT -> Bot(id)
        ProcessingUnitType.OUTPUT -> Output(id)
    }.also { units.add(it) }
}

sealed interface Instruction

data class InputInstruction(
    val chipValue: Int,
    val botId: Int,
) : Instruction

data class BotInstruction(
    val botId: Int,
    val lowType: ProcessingUnitType,
    val lowId: Int,
    val highType: ProcessingUnitType,
    val highId: Int,
) : Instruction

val instructionParser: (String) -> Instruction = regexMatch(
    """value ([0-9]+) goes to bot ([0-9]+)""".toRegex() yields {
        InputInstruction(
            chipValue = it.component1().toInt(),
            botId = it.component2().toInt(),
        )
    },
    """bot ([0-9]+) gives low to (bot|output) ([0-9]+) and high to (bot|output) ([0-9]+)""".toRegex() yields {
        BotInstruction(
            botId = it.component1().toInt(),
            lowType = ProcessingUnitType.valueOf(it.component2().uppercase()),
            lowId = it.component3().toInt(),
            highType = ProcessingUnitType.valueOf(it.component4().uppercase()),
            highId = it.component5().toInt(),
        )
    }
)
