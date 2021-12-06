import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day2Test {

    @Test
    fun solve1() {
        expect("150") { Day2.solve1(readInput(2, 1)) }
    }

    @Test
    fun solve2() {
        expect("900") { Day2.solve2(readInput(2, 2)) }
    }
}
