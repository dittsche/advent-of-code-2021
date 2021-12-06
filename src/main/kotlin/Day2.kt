object Day2 : PuzzleSolver {

    override fun solve1(input: String): String {
        val commands = input.splitToCommands()

        return Submarine()
            .apply { commands.forEach { this moveBy it } }
            .whereAmI.toString()
    }

    override fun solve2(input: String): String {
        val commands = input.splitToCommands()

        return Submarine()
            .apply { commands.forEach { this moveBy2 it } }
            .whereAmI.toString()
    }

    fun String.splitToCommands() = lines()
        .map { it.split(" ") }
        .map { Command(Direction.valueOf(it[0].uppercase()), it[1].toInt()) }

    class Submarine(private val position: Position = Position(), private var aim: Int = 0) {
        infix fun moveBy(command: Command) {
            when (command.direction) {
                Direction.FORWARD -> position.horizontal += command.amount
                Direction.DOWN -> position.depth += command.amount
                Direction.UP -> position.depth -= command.amount
            }
        }

        infix fun moveBy2(command: Command) {
            when (command.direction) {
                Direction.FORWARD -> {
                    position.horizontal += command.amount
                    position.depth += command.amount * aim
                }
                Direction.DOWN -> aim += command.amount
                Direction.UP -> aim -= command.amount
            }
        }

        val whereAmI: Int get() = position.horizontal * position.depth
    }

    data class Position(var horizontal: Int = 0, var depth: Int = 0)

    data class Command(val direction: Direction, val amount: Int)

    enum class Direction { FORWARD(), DOWN, UP }
}
