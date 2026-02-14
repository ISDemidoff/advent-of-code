package isdemidoff.adventofcode.year2016.day12.entity

fun runProgram(state: ProgramState = MapBasedProgramState(), program: List<ProgramInstruction>): ProgramState = state.apply {
    var runningLine = 0
    while (runningLine < program.size) {
        runningLine += program[runningLine].execute(this)
    }
}