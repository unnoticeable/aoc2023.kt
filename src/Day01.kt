object Day01 {
    fun part1(input: String): Int {
        fun valueOf(s: String): Int {
            val digits = s.filter { it.isDigit() }.map { it.digitToInt() }
            return digits.first() * 10 + digits.last()
        }
        return input.lines().sumOf { valueOf(it) }
    }

    fun part2(input: String): Int {
        val words = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val digitOf = (words.mapIndexed { i, s -> s to i + 1 } + (0..9).map { it.toString() to it }).toMap()
        fun valueOf(s: String): Int {
            val digits = Regex(digitOf.keys.joinToString("|")).findAll(s).mapNotNull { digitOf[it.value] }
            return digits.first() * 10 + digits.last()
        }
        return input.lines().sumOf { valueOf(it) }
    }
}

fun main() {
    with(Day01) {
        val input0 = readInput(javaClass.simpleName)
        val input1 = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()
        val input2 = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()
        val input1expected1 = """
            142
        """.trimIndent()
        val input0expected1 = """
            53334
        """.trimIndent()
        val input2expected2 = """
            281
        """.trimIndent()
        val input0expected2 = """
            52834
        """.trimIndent()
        part1(input1).toString().also {
            println(it)
            check(input1expected1 == it)
        }
        part1(input0).toString().also {
            println(it)
            check(input0expected1 == it)
        }
        part2(input2).toString().also {
            println(it)
            check(input2expected2 == it)
        }
        part2(input0).toString().also {
            println(it)
            check(input0expected2 == it)
        }
    }
}
