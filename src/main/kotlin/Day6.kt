object Day6 : PuzzleSolver {
    override fun solve1(input: String): String {
        var fish = input.splitToInts().map { Fish(it) }
        for (i in 0 until 80) {
            fish = fish.flatMap { it.tic() }
        }

        return fish.size.toString()
    }

    override fun solve2(input: String): String =
        input.splitToInts().sumOf { countFish(256 - it) }.toString()

    private fun String.splitToInts(delimiter: String = ",") = split(delimiter).map { it.toInt() }

    private fun countFish(remainingDays: Int): Long =
        1L + (remainingDays - 1 downTo 0 step 7).sumOf { days -> countFish(days - 8) }

    private data class Fish(var days: Int = 8) {
        fun tic(): List<Fish> {
            if (days-- == 0) {
                days = 6
                return listOf(this, Fish())
            }
            return listOf(this)
        }
    }
}
