package isdemidoff.adventofcode.year2016.day4.entity

import isdemidoff.utility.parseUnescapedCsvInputLine
import isdemidoff.utility.strings.countCharacterStatistics

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
                this.sectorId = it.component2().toInt()
                this.encryptedName = it.component1()
                this.checksum = it.component3()
            }
    }

    fun isReal(): Boolean =
        countCharacterStatistics(this.encryptedName.replace("-", ""))
            .entries
            .sortedWith(compareByDescending<Map.Entry<Char, Int>> { it.value }.thenBy { it.key })
            .take(5)
            .map { it.key }
            .joinToString(separator = "")
            .let { it == this.checksum }

    fun decryptName(): String = this.encryptedName.parseUnescapedCsvInputLine("-") {
        it.map { ch -> 'a' + (ch - 'a' + this.sectorId) % 26 }.joinToString(separator = "")
    }.joinToString(separator = " ")
}