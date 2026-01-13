package isdemidoff.utility.solution

class InputParser<INNER_DATA>(private val fn: (List<List<String>>) -> INNER_DATA) {
    fun parse(input: List<List<String>>) = fn(input)
}

fun <INNER_DATA> inputParser(transform: (List<List<String>>) -> INNER_DATA) =
    InputParser(transform)

fun <INNER_DATA> singleBlockParser(transform: (List<String>) -> INNER_DATA): InputParser<INNER_DATA> =
    inputParser { transform(it.single()) }

fun <INNER_DATA> uniformLinesParser(transform: (String) -> INNER_DATA): InputParser<List<INNER_DATA>> =
    singleBlockParser { it.map { row -> transform(row) } }

fun <INNER_DATA> singleLineParser(transform: (String) -> INNER_DATA): InputParser<INNER_DATA> =
    singleBlockParser { transform(it.single()) }

fun <INNER_DATA> twoBlocksParser(transform: (Pair<List<String>, List<String>>) -> INNER_DATA): InputParser<INNER_DATA> =
    inputParser {
        require(it.size == 2) { "Expected 2 blocks but got ${it.size}" }
        return@inputParser transform(it.first() to it.last())
    }