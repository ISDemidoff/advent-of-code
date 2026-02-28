@file:Suppress("unused", "RedundantUnitReturnType")

package isdemidoff.solution.inputparser.functions.bidirectional

object StringsBiDirectionalFunctions {
    val toCharArray: BiDirectionalFunction<String, List<Char>> = BiDirectionalFunction(
        fn = { it.toList() },
        inv = { it.joinToString(separator = "") },
    )

    val charToInt: BiDirectionalFunction<Char, Int> = BiDirectionalFunction({ it.digitToInt() }, { it.digitToChar() })
    val intToChar: BiDirectionalFunction<Int, Char> = charToInt.inverse()

    val digits: BiDirectionalFunction<String, List<Int>> = toCharArray mapWith charToInt
}
