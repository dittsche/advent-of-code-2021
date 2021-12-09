import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day1Test : AbstractPuzzleSolverTest(Day1, 7, 5)
class Day2Test : AbstractPuzzleSolverTest(Day2, 150, 900)
class Day3Test : AbstractPuzzleSolverTest(Day3, 198, 230)
class Day4Test : AbstractPuzzleSolverTest(Day4, 4512, 1924)
class Day5Test : AbstractPuzzleSolverTest(Day5, 5, 12)
// class Day6Test : AbstractPuzzleSolverTest(6, Day6, 5934, 26984457539)
class Day7Test : AbstractPuzzleSolverTest(Day7, 37, 168)
class Day8Test : AbstractPuzzleSolverTest(Day8, 26, 61229)
class Day9Test : AbstractPuzzleSolverTest(Day9, 15, 1134)

abstract class AbstractPuzzleSolverTest(
    private val sut: PuzzleSolver,
    private val expected1: Number,
    private val expected2: Number
) {

    @Test
    fun solve1() {
        expect(expected1) { sut.solve1(readInput(sut.day)) }
    }

    @Test
    fun solve2() {
        expect(expected2) { sut.solve2(readInput(sut.day)) }
    }
}
