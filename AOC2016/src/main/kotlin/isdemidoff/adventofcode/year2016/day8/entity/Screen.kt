package isdemidoff.adventofcode.year2016.day8.entity

class Screen(
    private val width: Int,
    private val height: Int,
) {
    private val pixels = Array(height) { BooleanArray(width) { false } }

    fun executeCommands(commands: List<Command>) = commands.forEach { command ->
        when (command) {
            is RectCommand -> (0..<command.width).forEach { x ->
                (0..<command.height).forEach { y ->
                    pixels[y][x] = true
                }
            }
            is ColumnShiftCommand -> repeat(command.shift) {
                val temp = pixels.last()[command.x]
                (1..<this.height).reversed().forEach { y ->
                    pixels[y][command.x] = pixels[y-1][command.x]
                }
                pixels.first()[command.x] = temp
            }
            is RowShiftCommand -> repeat(command.shift) {
                val temp = pixels[command.y].last()
                (1..<this.width).reversed().forEach { x ->
                    pixels[command.y][x] = pixels[command.y][x - 1]
                }
                pixels[command.y][0] = temp
            }
        }
    }

    fun showGrid() = this.pixels.joinToString(separator = "\n") { row -> row.joinToString(separator = "") { if (it) "#" else "." } }

    fun splitGridChunked(width: Int) = this.pixels.joinToString(separator = "\n") { row ->
        row.joinToString(separator = "") { if (it) "#" else "." }
            .chunked(width).joinToString(separator = " ")
    }

    fun getNumberOfLitPixels() = pixels.sumOf { it.count { it } }
}