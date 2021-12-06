fun main(args: Array<String>) {
    println("Hello Advent of Code 2021!")

    listOf<(String) -> Unit>(
        { day1(it) },
        { },
        {},
        {},
        {},
        {
            // day6(it)
        },
    ).forEachIndexed { index, function ->
        println("Advent of Code 2021 - Day ${index + 1}")
        function(args[index])
        println("-".repeat(30))
    }
}
