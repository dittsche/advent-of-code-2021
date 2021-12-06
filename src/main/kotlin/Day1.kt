object Day1 : PuzzleSolver {

    override fun solve1(input: String): Number {
        val depths = input.splitToInts()

        return depths.associateByPreviousValue()
            .filterIncreases()
            .size
    }

    override fun solve2(input: String): Number {
        val depths = input.splitToInts()

        val measurementWindows = (0..depths.size - 3).map { depths[it] + depths[it + 1] + depths[it + 2] }

        return measurementWindows.associateByPreviousValue()
            .filterIncreases()
            .size
    }

    private fun String.splitToInts() = lines().map { it.toInt() }

    private fun List<Int>.associateByPreviousValue() = withIndex()
        .filterNot { it.index == 0 }
        .map { this[it.index - 1] to it.value }

    private fun List<Pair<Int, Int>>.filterIncreases() = filter { it.second > it.first }
}
