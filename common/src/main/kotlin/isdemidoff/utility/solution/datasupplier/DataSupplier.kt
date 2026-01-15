package isdemidoff.utility.solution.datasupplier

typealias BlocksContent = List<List<String>>

sealed interface DataSupplier<INNER_DATA> {
    fun getInputData(): INNER_DATA
}
