@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.utility.input

private fun readLines(fileName: String): List<String> = (object {}).javaClass
    .getResourceAsStream("/$fileName")
    .let { requireNotNull(it) { "file $fileName not found" } }
    .bufferedReader()
    .readLines()

fun readBlocks(filename: String): List<List<String>> = readLines(filename).let { allLines ->
    val result = mutableListOf<List<String>>()
    var intermediateList = mutableListOf<String>()

    fun pushIntoResult() = intermediateList.takeUnless { it.isEmpty() }?.let { result.add(it) }

    allLines.forEach {
        if (it.isBlank()) {
            pushIntoResult()
            intermediateList = mutableListOf()
        } else {
            intermediateList.add(it)
        }
    }
    // add last block if it is not empty
    pushIntoResult()
    return@let result
}