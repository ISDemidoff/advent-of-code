package isdemidoff.utility.enums

import kotlin.enums.enumEntries

interface ParseableEnum {
    val text: String
}

inline fun <reified T> enumRegexMatchString(): String
where T : Enum<T>, T : ParseableEnum =
    enumEntries<T>().joinToString(separator = "|") { it.text }

inline fun <reified T> parseFromString(input: String): T
where T : Enum<T>, T : ParseableEnum =
    enumEntries<T>().first { it.text == input }
