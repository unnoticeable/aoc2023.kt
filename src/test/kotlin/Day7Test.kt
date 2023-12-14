import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    val day = Day7
    val input = javaClass.getResourceAsStream("${day.javaClass.simpleName}.txt")?.reader()?.readText()?.trim() ?: ""
    val example1 = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent()

    @Test
    fun part1() {
        val example1Expected1 = """
            6440
        """.trimIndent()
        val inputExpected1 = """
            253866470
        """.trimIndent()
        assertEquals(example1Expected1, day.part1(example1).toString())
        assertEquals(inputExpected1, day.part1(input).toString())
    }

    @Test
    fun part2() {
        val example1Expected2 = """
            5905
        """.trimIndent()
        val inputExpected2 = """
            254494947
        """.trimIndent()
        assertEquals(example1Expected2, day.part2(example1).toString())
        assertEquals(inputExpected2, day.part2(input).toString())
    }
}
