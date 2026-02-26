package isdemidoff.adventofcode.year2016.day23.entity

import kotlin.reflect.KClass

sealed interface ProgramOptimizer {
    fun applicable(program: List<ProgramInstruction>, currentPosition: Int): Boolean

    /**
     * @return position offset
     */
    fun perform(program: List<ProgramInstruction>, currentPosition: Int, state: ProgramState): Int
}

abstract class PatternProgramOptimizer : ProgramOptimizer {
    abstract val operationsPattern: List<KClass<out ProgramInstruction>>
    protected val cache = mutableMapOf<Pair<List<ProgramInstruction>, Int>, Boolean>()

    override fun applicable(program: List<ProgramInstruction>, currentPosition: Int): Boolean {
        if (cache.containsKey(program to currentPosition)) return cache[program to currentPosition]!!
        return checkNoCache(program, currentPosition).also { cache[program to currentPosition] = it }
    }

    private fun checkNoCache(program: List<ProgramInstruction>, currentPosition: Int): Boolean {
        val operations = program.drop(currentPosition).take(operationsPattern.size)
        if (operations.size != operationsPattern.size) return false
        if (operations.zip(operationsPattern).any { it.first::class != it.second::class }) return false
        return doCheckApplicable(operations)
    }

    abstract fun doCheckApplicable(operations: List<ProgramInstruction>): Boolean

    override fun perform(program: List<ProgramInstruction>, currentPosition: Int, state: ProgramState): Int {
        val operations = program.drop(currentPosition).take(operationsPattern.size)
        doPerform(operations, state)
        return operations.size
    }

    abstract fun doPerform(operations: List<ProgramInstruction>, state: ProgramState)
}

class MultiplicationOptimizer : PatternProgramOptimizer() {
    override val operationsPattern = listOf(
        CopyValueProgramInstruction::class,
        IncreaseProgramInstruction::class,
        DecreaseProgramInstruction::class,
        JumpWhenNotZeroProgramInstruction::class,
        DecreaseProgramInstruction::class,
        JumpWhenNotZeroProgramInstruction::class,
    )

    override fun doCheckApplicable(operations: List<ProgramInstruction>): Boolean {
        val appendantId = (operations[1] as IncreaseProgramInstruction).register
        val multiplierId = (operations[4] as DecreaseProgramInstruction).register
        val lastOp = operations[5] as JumpWhenNotZeroProgramInstruction
        if (lastOp.jumpValue != "-5" || lastOp.argToCompare != multiplierId) return false

        val fillerId = (operations[0] as CopyValueProgramInstruction).to
        if ((operations[2] as DecreaseProgramInstruction).register != fillerId) return false
        val jnzInMiddle = operations[3] as JumpWhenNotZeroProgramInstruction
        if (jnzInMiddle.jumpValue != "-2" || jnzInMiddle.argToCompare != fillerId) return false

        if (multiplierId == fillerId || appendantId == multiplierId) return false

        return true
    }

    override fun doPerform(operations: List<ProgramInstruction>, state: ProgramState) {
        val appendantId = (operations[1] as IncreaseProgramInstruction).register
        val multiplierId = (operations[4] as DecreaseProgramInstruction).register
        val (secondMultiplier, fillerId) = operations[0] as CopyValueProgramInstruction

        state.updateRegisterValue(appendantId) { it + state.extractValue(multiplierId) * state.extractValue(secondMultiplier) }
        state.updateRegisterValue(multiplierId) { 0 }
        state.updateRegisterValue(fillerId) { 0 }
    }
}

class SumOptimizer : PatternProgramOptimizer() {
    override val operationsPattern = listOf(
        IncreaseProgramInstruction::class,
        DecreaseProgramInstruction::class,
        JumpWhenNotZeroProgramInstruction::class,
    )

    override fun doCheckApplicable(operations: List<ProgramInstruction>): Boolean {
        val (value, jump) = operations[2] as JumpWhenNotZeroProgramInstruction
        if (jump != "-2") return false

        val decreaseId = (operations[1] as DecreaseProgramInstruction).register
        if (decreaseId != value) return false
        if ((operations[0] as IncreaseProgramInstruction).register == decreaseId) return false

        return true
    }

    override fun doPerform(operations: List<ProgramInstruction>, state: ProgramState) {
        val appendant = (operations[0] as IncreaseProgramInstruction).register
        val second = (operations[1] as DecreaseProgramInstruction).register

        state.updateRegisterValue(appendant) { it + state.extractValue(second) }
        state.updateRegisterValue(second) { 0 }
    }
}

class DivisionOptimizer : PatternProgramOptimizer() {
    override val operationsPattern = listOf(
        CopyValueProgramInstruction::class,
        CopyValueProgramInstruction::class,
        CopyValueProgramInstruction::class,
        JumpWhenNotZeroProgramInstruction::class,
        JumpWhenNotZeroProgramInstruction::class,
        DecreaseProgramInstruction::class,
        DecreaseProgramInstruction::class,
        JumpWhenNotZeroProgramInstruction::class,
        IncreaseProgramInstruction::class,
        JumpWhenNotZeroProgramInstruction::class,
    )

    override fun doCheckApplicable(operations: List<ProgramInstruction>): Boolean {
        val copy = operations[0] as CopyValueProgramInstruction
        val copyZero = operations[1] as CopyValueProgramInstruction
        val copyTwo = operations[2] as CopyValueProgramInstruction
        val jumpSkip = operations[3] as JumpWhenNotZeroProgramInstruction
        val jumpOutside = operations[4] as JumpWhenNotZeroProgramInstruction
        val dec1 = operations[5] as DecreaseProgramInstruction
        val dec2 = operations[6] as DecreaseProgramInstruction
        val jumpBack1 = operations[7] as JumpWhenNotZeroProgramInstruction
        val inc = operations[8] as IncreaseProgramInstruction
        val jumpBack2 = operations[9] as JumpWhenNotZeroProgramInstruction

        if (jumpBack1.jumpValue != "-4" || jumpBack2.jumpValue != "-7") return false
        if (jumpSkip.jumpValue != "2" || jumpOutside.jumpValue != "6") return false
        if (copyTwo.from != "2") return false
        if (copyZero.from != "0") return false

        val cId = copyTwo.to
        if (jumpBack1.argToCompare != cId || dec2.register != cId) return false

        val bId = jumpSkip.argToCompare
        if (dec1.register != bId) return false

        val aId = inc.register
        if (aId != copyZero.to) return false

        if (copy.from != aId || copy.to != bId) return false

        if (cId == aId || cId == bId || aId == bId) return false

        return true
    }

    override fun doPerform(operations: List<ProgramInstruction>, state: ProgramState) {
        val cId = (operations[2] as CopyValueProgramInstruction).to
        val bId = (operations[3] as JumpWhenNotZeroProgramInstruction).argToCompare
        val aId = (operations[8] as IncreaseProgramInstruction).register
        state.updateRegisterValue(cId) { 2 - state.extractValue(aId) % 2 }
        state.updateRegisterValue(aId) { it / 2 }
        state.updateRegisterValue(bId) { 0 }
    }
}

class NoOptimization : ProgramOptimizer {
    override fun applicable(program: List<ProgramInstruction>, currentPosition: Int) = true
    override fun perform(program: List<ProgramInstruction>, currentPosition: Int, state: ProgramState) = program[currentPosition].execute(state)
}