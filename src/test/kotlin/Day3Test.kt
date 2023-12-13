import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    val day = Day3
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            4361
        """.trimIndent()
        val inputExpected1 = """
            546312
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example1Expected2 = """
            467835
        """.trimIndent()
        val inputExpected2 = """
            87449461
        """.trimIndent()
        assertEquals(example1Expected2, day.part2(example1).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
