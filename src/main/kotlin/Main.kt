fun main() {
    println("Hello Advent of Code 2021!")

    mapOf<Int, PuzzleSolver>(
        1 to Day1,
        2 to Day2,
        3 to Day3,
        4 to Day4,
//        6 to Day6,
    ).forEach { (index, solver) ->
        println("Advent of Code 2021 - Day $index")
        println("Puzzle 1: ${solver.solve1(readInput(index, 1))}")
        println("Puzzle 2: ${solver.solve2(readInput(index, 2))}")
        println("-".repeat(30))
    }
}
