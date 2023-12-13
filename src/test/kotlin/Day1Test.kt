import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    val day = Day1
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()
    val example2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            142
        """.trimIndent()
        val inputExpected1 = """
            53334
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example2Expected2 = """
            281
        """.trimIndent()
        val inputExpected2 = """
            52834
        """.trimIndent()
        assertEquals(example2Expected2, day.part2(example2).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
