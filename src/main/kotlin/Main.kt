fun main() {
    println("Hello Advent of Code 2021!")

    mapOf(
        1 to Day1,
        2 to Day2,
        3 to Day3,
        4 to Day4,
//        5 to Day5,
//        6 to Day6,
        7 to Day7,
        8 to Day8,
    ).forEach { (index, solver) ->
        println("Advent of Code 2021 - Day $index")
        println("Puzzle 1: ${solver.solve1(readInput(index))}")
        println("Puzzle 2: ${solver.solve2(readInput(index))}")
        println("-".repeat(30))
    }
}
