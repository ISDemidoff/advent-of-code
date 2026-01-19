package isdemidoff.adventofcode.year2016.day4.entity

import isdemidoff.utility.parseUnescapedCsvInputLine

class Room(
    roomDescription: String,
) {
    val sectorId: Int
    private val encryptedName: String
    private val checksum: String

    init {
        """([a-z\-]+)-([0-9]+)\[([a-z]{5})]""".toRegex().matchEntire(roomDescription)
            .let { requireNotNull(it?.destructured) { "Room description does not match expected pattern: $roomDescription" } }
            .let {
                sectorId = it.component2().toInt()
                encryptedName = it.component1()
                checksum = it.component3()
            }
    }

    fun isReal(): Boolean {
        val freqs = mutableMapOf<Char, Int>()
        encryptedName.replace("-", "").forEach {
            freqs[it] = (freqs[it] ?: 0) + 1
        }

        val checksumForCheck = freqs.entries
            .sortedWith(
            compareByDescending<Map.Entry<Char, Int>> { it.value }.thenBy { it.key }
            )
            .take(5)
            .map { it.key }
            .joinToString(separator = "")

        return checksumForCheck == checksum
    }

    fun decryptName(): String = encryptedName.parseUnescapedCsvInputLine("-") {
        it.map { ch -> 'a' + (ch - 'a' + sectorId) % 26 }.joinToString(separator = "")
    }.joinToString(separator = " ")
}