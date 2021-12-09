object Day1 : PuzzleSolver {
    override val day get() = 1

    override fun solve1(input: String): Number {
        val depths = input.splitToInts()

        return depths.zipWithNext()
            .filterIncreases()
            .size
    }

    override fun solve2(input: String): Number {
        val depths = input.splitToInts()

        return depths.windowed(3) { it.sum() }
            .zipWithNext()
            .filterIncreases()
            .size
    }

    private fun String.splitToInts() = lines().map { it.toInt() }

    private fun List<Pair<Int, Int>>.filterIncreases() = filter { it.second > it.first }
}
