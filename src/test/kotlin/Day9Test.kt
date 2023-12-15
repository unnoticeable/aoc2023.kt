import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    val day = Day9
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            114
        """.trimIndent()
        val inputExpected1 = """
            1819125966
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example1Expected2 = """
            2
        """.trimIndent()
        val inputExpected2 = """
            1140
        """.trimIndent()
        assertEquals(example1Expected2, day.part2(example1).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
