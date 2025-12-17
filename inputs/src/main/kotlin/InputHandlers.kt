fun readLines(fileName: String): List<String> = (object {}).javaClass
    .getResourceAsStream(fileName)!!
    .bufferedReader()
    .readLines()

fun readSingleLine(fileName: String): String = readLines(fileName).single()