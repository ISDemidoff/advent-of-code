package isdemidoff.adventofcode.year2025.day10.entity

data class Button(val toggles: List<Int>) {
    fun applyOnLights(lights: Array<Boolean>) = toggles.forEach { lights[it] = !lights[it] }
}