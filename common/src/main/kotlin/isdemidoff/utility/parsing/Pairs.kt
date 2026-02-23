package isdemidoff.utility.parsing

fun String.keyValue(delimiter: String = ": "): Pair<String, String> =
    this.split(delimiter)
        .also { require(it.size == 2) { "Invalid input: $this" } }
        .let { it.first() to it.last() }

fun <K, V> String.keyValueBy(
    delimiter: String = ": ",
    keyTransformer: (String) -> K,
    valueTransformer: (String) -> V,
): Pair<K, V> = this.keyValue(delimiter).let { (k, v) -> keyTransformer(k) to valueTransformer(v) }

fun <K> String.keyValueBy(delimiter: String = ": ", keyTransformer: (String) -> K): Pair<K, String> =
    this.keyValueBy(delimiter, keyTransformer) { it }

fun <V> String.keyValueWith(delimiter: String = ": ", valueTransformer: (String) -> V): Pair<String, V> =
    this.keyValueBy(delimiter, { it }, valueTransformer)