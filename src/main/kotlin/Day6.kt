fun day6(input: String) {
    val initial = input.split(",").map { it.toInt() }

    var fish = initial.map { Fish(it) }
    for (i in 0 until 80) {
        fish = fish.flatMap { it.tic() }
    }
    println("80: ${fish.size}")

    val sum = initial.sumOf { countFish(256 - it) }
    println("${256}: $sum")
}

private data class Fish(var days: Int = 8) {
    fun tic(): List<Fish> {
        if (days-- == 0) {
            days = 6
            return listOf(this, Fish())
        }
        return listOf(this)
    }
}

private fun countFish(remainingDays: Int): Long =
    1L + (remainingDays - 1 downTo 0 step 7).sumOf { days -> countFish(days - 8) }
