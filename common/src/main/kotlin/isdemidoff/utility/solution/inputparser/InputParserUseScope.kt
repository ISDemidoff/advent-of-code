package isdemidoff.utility.solution.inputparser

interface InputParserUseScope {
    fun <R> inputParser(transform: (blocks: List<List<String>>) -> R) =
        InputParser(transform)

    fun <R> singleBlockParser(transform: (lines: List<String>) -> R): InputParser<R> =
        inputParser { transform(it.single()) }

    fun <R> uniformLinesParser(transform: (line: String) -> R): InputParser<List<R>> =
        singleBlockParser { it.map { row -> transform(row) } }

    fun <R> singleLineParser(transform: (line: String) -> R): InputParser<R> =
        singleBlockParser { transform(it.single()) }

    fun <R> twoBlocksParser(transform: (Pair<List<String>, List<String>>) -> R): InputParser<R> =
        inputParser {
            require(it.size == 2) { "Expected 2 blocks but got ${it.size}" }
            return@inputParser transform(it.first() to it.last())
        }
}