import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day1Test : AbstractPuzzleSolverTest(1, Day1, 7, 5)
class Day2Test : AbstractPuzzleSolverTest(2, Day2, 150, 900)
class Day3Test : AbstractPuzzleSolverTest(3, Day3, 198, 230)
class Day4Test : AbstractPuzzleSolverTest(4, Day4, 4512, 1924)
class Day5Test : AbstractPuzzleSolverTest(5, Day5, 5, 12)
// class Day6Test : AbstractPuzzleSolverTest(6, Day6, 5934, 26984457539)
class Day7Test : AbstractPuzzleSolverTest(7, Day7, 37, 168)

abstract class AbstractPuzzleSolverTest(
    private val day: Int,
    private val sut: PuzzleSolver,
    private val expected1: Number,
    private val expected2: Number
) {

    @Test
    fun solve1() {
        expect(expected1) { sut.solve1(readInput(day)) }
    }

    @Test
    fun solve2() {
        expect(expected2) { sut.solve2(readInput(day)) }
    }
}
