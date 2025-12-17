fun readLines(fileName: String): List<String> = (object {}).javaClass
    .getResourceAsStream(fileName)!!
    .bufferedReader()
    .readLines()

fun readSingleLine(fileName: String): String = readLines(fileName).single()

fun readTwoBlocks(fileName: String): Pair<List<String>, List<String>> = readLines(fileName).let {
    it.takeWhile { it.isNotBlank() } to it.takeLastWhile { it.isNotBlank() }
}