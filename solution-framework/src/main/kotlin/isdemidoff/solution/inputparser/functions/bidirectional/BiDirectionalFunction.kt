package isdemidoff.solution.inputparser.functions.bidirectional

class BiDirectionalFunction<T, R>(
    val fn: (T) -> R,
    val inv: (R) -> T,
) : (T) -> R by fn {
    fun inverse(): BiDirectionalFunction<R, T> = BiDirectionalFunction(inv, fn)
}
