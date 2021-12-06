import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day3Test {

    @Test
    fun solve1() {
        expect(198) { Day3.solve1(readInput(3, 1)) }
    }

    @Test
    fun solve2() {
        expect(230) { Day3.solve2(readInput(3, 2)) }
    }
}
