package isdemidoff.utility

fun CharSequence.keyValue(delimiter: String = ": "): Pair<String, String> =
    this.split(delimiter)
        .also { require(it.size == 2) { "Invalid input: $this" } }
        .let { it.first() to it.last() }

fun <K, V> CharSequence.keyValueBy(
    delimiter: String = ": ",
    keyTransformer: (String) -> K,
    valueTransformer: (String) -> V
): Pair<K, V> = this.keyValue(delimiter).let { (k, v) -> keyTransformer(k) to valueTransformer(v) }

fun <K> CharSequence.keyValueBy(delimiter: String = ": ", keyTransformer: (String) -> K): Pair<K, String> =
    this.keyValueBy(delimiter, keyTransformer) { it }

fun <V> CharSequence.keyValueWith(delimiter: String = ": ", valueTransformer: (String) -> V): Pair<String, V> =
    this.keyValueBy(delimiter, { it }, valueTransformer)

fun <A, B, R> Pair<A, B>.mapFirst(transform: (A) -> R): Pair<R, B> = transform(this.first) to this.second
fun <A, B, R> Pair<A, B>.mapSecond(transform: (B) -> R): Pair<A, R> = this.first to transform(this.second)
