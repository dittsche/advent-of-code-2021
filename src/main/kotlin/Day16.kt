import Day16.PacketType.EQ
import Day16.PacketType.GT
import Day16.PacketType.LITERAL
import Day16.PacketType.LT
import Day16.PacketType.MAX
import Day16.PacketType.MIN
import Day16.PacketType.PRODUCT
import Day16.PacketType.SUM

object Day16 : PuzzleSolver(16) {

    override fun solve1(input: String): Number {
        return input.toBinary().toPacket().first.sumVersions()
    }

    override fun solve2(input: String): Number {
        return input.toBinary().toPacket().first.value
    }

    private fun String.toBinary() =
        map {
            it.digitToInt(16).toString(2).padStart(4, '0')
        }.joinToString("") { it }

    private const val LITERAL_LENGTH = 5

    private fun String.toPacket(): Pair<Packet, String> {
        val version = extractVersion()
        val type = extractType()
        val remainder = drop(6)
        val remainderAfterSubPackets: String
        val subPackets = when (type) {
            LITERAL -> {
                val chunked = remainder.chunked(LITERAL_LENGTH)
                val literalGroups = with(chunked.takeWhile { it[0] == '1' }) {
                    this + chunked[count()]
                }.map { it.drop(1) }
                val value = literalGroups.joinToString("") { it }.toLong(2)

                remainderAfterSubPackets = remainder.drop(LITERAL_LENGTH * literalGroups.count())
                return Literal(version, value) to remainderAfterSubPackets
            }
            else -> {
                if (remainder[0] == '0') {
                    val subPacketsBits = remainder.drop(1).take(15).toInt(2)
                    var subPacketsString = remainder.drop(16).take(subPacketsBits)
                    remainderAfterSubPackets = remainder.drop(16 + subPacketsBits)

                    val packets = mutableListOf<Packet>()
                    while (subPacketsString.isNotEmpty()) {
                        val result = subPacketsString.toPacket()
                        packets.add(result.first)
                        subPacketsString = result.second
                    }
                    packets
                } else {
                    val subPacketsCount = remainder.drop(1).take(11).toInt(2)
                    var subPacketsString = remainder.drop(12)

                    val packets = mutableListOf<Packet>()
                    for (i in 0 until subPacketsCount) {
                        val result = subPacketsString.toPacket()
                        packets.add(result.first)
                        subPacketsString = result.second
                    }

                    remainderAfterSubPackets = subPacketsString
                    packets
                }
            }
        }
        return Operator(version, type, subPackets) to remainderAfterSubPackets
    }

    private abstract class Packet(open val version: Int, open val type: PacketType?) {
        abstract fun sumVersions(): Int
        abstract val value: Long
    }

    private data class Literal(override val version: Int, override val value: Long) : Packet(version, LITERAL) {
        override fun sumVersions() = version
    }

    private data class Operator(
        override val version: Int,
        override val type: PacketType,
        val subPackets: List<Packet>
    ) : Packet(version, type) {
        override fun sumVersions(): Int = version + subPackets.sumOf { it.sumVersions() }
        override val value: Long
            get() = when (type) {
                SUM -> subPackets.sumOf { it.value }
                PRODUCT -> subPackets.fold(1) { acc, curr -> acc * curr.value }
                MIN -> subPackets.minOf { it.value }
                MAX -> subPackets.maxOf { it.value }
                LITERAL -> value
                GT -> if (subPackets[0].value > subPackets[1].value) 1 else 0
                LT -> if (subPackets[0].value < subPackets[1].value) 1 else 0
                EQ -> if (subPackets[0].value == subPackets[1].value) 1 else 0
            }
    }

    private enum class PacketType(val id: Int) {
        SUM(0),
        PRODUCT(1),
        MIN(2),
        MAX(3),
        LITERAL(4),
        GT(5),
        LT(6),
        EQ(7)
    }

    private fun String.extractVersion() = take(3).toInt(2)
    private fun String.extractType() = drop(3).take(3).toPacketType()
    private fun String.toPacketType() = PacketType.values().single { it.id == this.toInt(2) }
}
