import org.junit.jupiter.api.Test
import kotlin.test.expect

class Day6Test {

    @Test
    fun solve1() {
        expect(5934) { Day6.solve1(readInput(6, 1)) }
    }

    @Test
    fun solve2() {
        expect(26984457539) { Day6.solve2(readInput(6, 2)) }
    }
}
