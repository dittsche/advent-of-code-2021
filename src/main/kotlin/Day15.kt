object Day15 : PuzzleSolver(15) {

    override fun solve1(input: String): Number {
        val values = input.lines()
            .map { line -> line.toList().map { it.digitToInt() } }

        return Grid(values).minSumToEnd()
    }

    override fun solve2(input: String): Number {
        val values = input.lines()
            .map { line -> line.toList().map { it.digitToInt() }.toMutableList() }.toMutableList()

        return Grid(values, values.size * 5).minSumToEnd()
    }

    data class Grid(private val values: List<List<Int>>, private val size: Int = values.size) {
        private val minSums: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()

        fun minSumToEnd() = minSumToEnd(0 to 0) - values[0][0]

        private fun minSumToEnd(start: Pair<Int, Int>): Int =
            if (minSums.containsKey(start))
                minSums.getValue(start)
            else {
                with((start.first to start.second).valueAtIndex) {
                    if (start.first == maxIndex && start.second == maxIndex) {
                        this
                    } else if (start.first == maxIndex) {
                        this + minSumToEnd(start.first to start.second + 1)
                    } else if (start.second == maxIndex) {
                        this + minSumToEnd(start.first + 1 to start.second)
                    } else {
                        minOf(
                            this + minSumToEnd(start.first to start.second + 1),
                            this + minSumToEnd(start.first + 1 to start.second)
                        )
                    }
                }
                    .also { minSums[start] = it }
            }

        private val maxIndex get() = size - 1

        private val Pair<Int, Int>.valueAtIndex
            get() =
                with((values[first % values.size][second % values.size] + (first / values.size) + (second / values.size)) % 9) {
                    when (this) {
                        0 -> 9
                        else -> this
                    }
                }

        override fun toString(): String {
            return (0 until size).joinToString(System.lineSeparator()) { x ->
                (0 until size).joinToString("") { y ->
                    (x to y).valueAtIndex.toString().let { it + (if (y % values.size == values.size - 1) " " else "") }
                }.let { it + (if (x % values.size == values.size - 1) System.lineSeparator() else "") }
            }
        }
    }
}
