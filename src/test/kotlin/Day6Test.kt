import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    val day = Day6
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            288
        """.trimIndent()
        val inputExpected1 = """
            227850
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example1Expected2 = """
            71503
        """.trimIndent()
        val inputExpected2 = """
            42948149
        """.trimIndent()
        assertEquals(example1Expected2, day.part2(example1).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
