object Day1 {
    fun part1(input: String) = input.lines().sumOf { line ->
        val digits = line.filter { it.isDigit() }.map { it.digitToInt() }
        digits.first() * 10 + digits.last()
    }

    val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val digitOf = words.mapIndexed { i, s -> s to i + 1 }.toMap() + (0..9).associateBy { it.toString() }
    fun part2(input: String) = input.lines().sumOf { line ->
        val digits = Regex(digitOf.keys.joinToString("|")).findAll(line).mapNotNull { digitOf[it.value] }
        digits.first() * 10 + digits.last()
    }
}
