package isdemidoff.utility.solution.inputparser

object InputParsers : InputParserUseScope {
    val singleString: InputParser<String> = singleLineParser { it }
    val singleInt: InputParser<Int> = singleLineParser { requireNotNull(it.toIntOrNull()) { "Expected line to be valid integer value, got: $it" } }
    val singleLong: InputParser<Long> = singleLineParser { requireNotNull(it.toLongOrNull()) { "Expected line to be valid long value, got: $it" } }

    val strings: InputParser<List<String>> = singleBlockParser { it }

    val intLines: InputParser<List<Int>> = uniformLinesParserIndexed { index, line ->
        requireNotNull(line.toIntOrNull()) { "Expected line (#$index) to be valid integer value, got $line" }
    }

    val longLines: InputParser<List<Long>> = uniformLinesParserIndexed { index, line ->
        requireNotNull(line.toLongOrNull()) { "Expected line (#$index) to be valid long value, got $line" }
    }
}