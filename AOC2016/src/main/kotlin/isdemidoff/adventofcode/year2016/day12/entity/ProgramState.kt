package isdemidoff.adventofcode.year2016.day12.entity

interface ProgramState {
    fun getRegisterValue(name: String): Int
    fun updateRegisterValue(name: String, updateFn: (Int) -> Int): Int
}

class MapBasedProgramState : ProgramState {
    private val map = mutableMapOf<String, Int>()

    override fun updateRegisterValue(name: String, updateFn: (Int) -> Int) =
        requireNotNull(map.compute(name) { _, v -> updateFn(v ?: 0) })

    override fun getRegisterValue(name: String) = map[name] ?: 0
}