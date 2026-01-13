package isdemidoff.utility.solution

import isdemidoff.utility.input.readBlocks

typealias BlocksContent = List<List<String>>

sealed interface DataSupplier<INNER_DATA> {
    fun getInputData(): INNER_DATA
}

class RawDataSupplier<INNER_DATA>(
    private val dataSupplier: () -> INNER_DATA,
) : DataSupplier<INNER_DATA> {
    override fun getInputData(): INNER_DATA = dataSupplier()
}

class FileDataSupplier(private val filenameFn: (Int) -> String) : DataSupplier<BlocksContent> {
    var dayIndex: Int? = null
    override fun getInputData(): BlocksContent = readBlocks(filenameFn(requireNotNull(dayIndex) { "Day is required" }))
}

fun <INNER_DATA> lazyData(dataSupplier: () -> INNER_DATA): RawDataSupplier<INNER_DATA> = RawDataSupplier(dataSupplier)
fun <INNER_DATA> raw(data: INNER_DATA): RawDataSupplier<INNER_DATA> = lazyData { data }
fun string(str: String): RawDataSupplier<BlocksContent> = raw(listOf(listOf(str)))
fun file(): FileDataSupplier = FileDataSupplier { "day$it-input.txt" }
fun file(suffix: Any): FileDataSupplier = FileDataSupplier { "day$it-input-$suffix.txt" }
fun sampleFile(): FileDataSupplier = FileDataSupplier { "day$it-sample.txt" }
fun sampleFile(suffix: Any): FileDataSupplier = FileDataSupplier { "day$it-sample-$suffix.txt" }
