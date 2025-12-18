import kotlin.text.trim

fun List<List<String>>.convertToLongsListList() = map { it.convertToLongsList() }

fun List<String>.convertToLongsList() = map { it.toLong() }

fun List<String>.takeFirstChars() = map { it.first() }

fun String.parseWhitespaceDelimetedInput() = trim().split("""\s+""".toRegex())

fun List<String>.parseWhitespaceDelimetedInputList() = map { it.parseWhitespaceDelimetedInput() }