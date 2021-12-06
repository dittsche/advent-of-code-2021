import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day1Test {

    @Test
    fun solve1() {
        expect("7") { Day1.solve1(readInput(1, 1)) }
    }

    @Test
    fun solve2() {
        expect("5") { Day1.solve2(readInput(1, 2)) }
    }
}
